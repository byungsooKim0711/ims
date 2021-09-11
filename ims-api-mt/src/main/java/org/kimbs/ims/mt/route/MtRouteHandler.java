package org.kimbs.ims.mt.route;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.exception.ImsBadRequestException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.model.mt.MtMessageType;
import org.kimbs.ims.mt.config.ApiMtConfig;
import org.kimbs.ims.mt.service.KafkaService;
import org.kimbs.ims.protocol.ImsPacket;
import org.kimbs.ims.protocol.ImsPacketCommand;
import org.kimbs.ims.protocol.v1.mt.ImsMtReq;
import org.kimbs.ims.protocol.v1.trace.TraceInfo;
import org.kimbs.ims.util.ByteUtil;
import org.kimbs.ims.util.RoundRobinUtil;
import org.kimbs.ims.util.SerialNumberUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.Validator;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
public class MtRouteHandler extends AbstractRouteHandler<ImsMtReq, MtMessageReq> {

    public MtRouteHandler(Validator validator, ApiMtConfig apiMtConfig, KafkaService kafkaService) {
        super(validator, apiMtConfig, kafkaService);
    }

    @Override
    protected ImsPacket<MtMessageReq> convert(ImsMtReq request) {
        MtMessageType messageType = MtMessageType.fromValue(request.getMessageType());
        String messageId = request.getMessageId();

        MtMessageReq mtMessageReq = MtMessageReq.builder()
                .messageType(messageType)
                .serialNumber(SerialNumberUtil.generateSerialNumber(messageType.getType(), messageId))
                .title(request.getTitle())
                .message(request.getMessage())
                .callback(request.getCallback())
                .phoneNumber(request.getPhoneNumber())
                .attachFileList(request.getAttachFileList())
                .build();

        TraceInfo traceInfo = new TraceInfo();
        traceInfo.setMessageId(request.getMessageId());
        traceInfo.setReceivedAt(LocalDateTime.now());
        traceInfo.setMapping(request.getMapping());

        return ImsPacket.<MtMessageReq>builder()
                .command(ImsPacketCommand.RECEIVE_MT)
                .data(mtMessageReq)
                .traceInfo(traceInfo)
                .build();
    }

    @Override
    protected void checkMandatory(ImsPacket<MtMessageReq> message) {
        MtMessageReq mtMessageReq = message.getData();
        MtMessageType messageType = mtMessageReq.getMessageType();
        int byteLength = ByteUtil.getByteLength(mtMessageReq.getMessage(), ByteUtil.Charsets.EUC_KR);

        switch (messageType) {
            case SMS:
                if (byteLength > 90) {
                    throw new ImsTooLongMessageException(String.format("%s type must be less then (%s/%s) bytes.", messageType, byteLength, 90));
                }
                break;
            case LMS:
                if (byteLength > 2000) {
                    throw new ImsTooLongMessageException(String.format("%s type must be less then (%s/%s) bytes.", messageType, byteLength, 2000));
                }
                break;
            case MMS:
                if (CollectionUtils.isEmpty(mtMessageReq.getAttachFileList())) {
                    throw new ImsBadRequestException("MMS 는 attach_file_list 에 최소 1개 이상의 값이 필요합니다.");
                }
                if (byteLength > 2000) {
                    throw new ImsTooLongMessageException(String.format("%s type must be less then (%s/%s) bytes.", messageType, byteLength, 2000));
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void checkDuplicateMsgUid(ImsPacket<MtMessageReq> message) {

    }

    @Override
    protected void send(ImsPacket<MtMessageReq> message) {
        List<String> mtTopics = config.getTopics().getRecvMt();

        kafkaService.sendToKafka(RoundRobinUtil.getRoundRobinValue(RoundRobinUtil.RoundRobinKey.RECV_MT, mtTopics), message);
    }

    @Override
    protected void log(ImsPacket<MtMessageReq> message) {
        MtMessageReq data = message.getData();
        TraceInfo trace = message.getTraceInfo();

        log.info("[Log] username: {}, serialNumber: {}, callback: {}, phoneNumber: {}",
                trace.getCustomerId(), data.getSerialNumber(), data.getCallback(), data.getPhoneNumber());
    }
}
