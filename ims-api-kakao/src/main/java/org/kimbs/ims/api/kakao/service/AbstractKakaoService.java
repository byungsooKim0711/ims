package org.kimbs.ims.api.kakao.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.config.ApiKakaoConfig;
import org.kimbs.ims.exception.ImsDuplicateMsgUidException;
import org.kimbs.ims.exception.ImsMandatoryException;
import org.kimbs.ims.exception.ImsServiceKeyException;
import org.kimbs.ims.exception.ImsTooLongMessageException;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

/**
 * R: Request Message
 * M: Convert Message
 */
@Slf4j
public abstract class AbstractKakaoService<R, M> {

    @Autowired
    protected ObjectMapper mapper;

    @Autowired
    protected ApiKakaoConfig config;

    @Autowired
    protected KafkaService kafkaService;

    public Mono<ImsApiResult<Void>> sendMessage(R request) {

        // logic, validation, auth, duplicate_key, etc...
        try {
            // convert message
            M message = convert(request);

            // check validation
            checkSenderKeyAndTemplate(message);
            checkMandatory(message);
            checkDuplicateMsgUid(message);

            // send recv topic
            send(message);
            log(message);
        } catch (ImsServiceKeyException e) {
            onException(request, e);
            throw e;
        } catch (ImsMandatoryException e) {
            onException(request, e);
            throw e;
        } catch (ImsTooLongMessageException e) {
            onException(request, e);
            throw e;
        } catch (ImsDuplicateMsgUidException e) {
            onException(request, e);
            throw e;
        } finally {

        }

        // success
        return Mono.just(ImsApiResult.succeed(ResponseCode.SUCCESS));
    }

    protected abstract M convert(R request);
    protected abstract void checkSenderKeyAndTemplate(M message);
    protected abstract void checkMandatory(M message) throws ImsMandatoryException;
    protected abstract void checkDuplicateMsgUid(M message);
    protected abstract void send(M message);
    protected abstract void onException(R request, Exception e);
    protected abstract void log(M message);
}
