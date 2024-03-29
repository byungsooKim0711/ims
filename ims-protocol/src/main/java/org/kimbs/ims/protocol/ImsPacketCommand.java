package org.kimbs.ims.protocol;

import lombok.Getter;
import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.mt.MtMessageReq;
import org.kimbs.ims.model.push.PushMessageReq;

@Getter
public enum ImsPacketCommand {

    RECEIVE_AT("", AtMessageReq.class),
    RECEIVE_FT("", FtMessageReq.class),
    RECEIVE_BT("", BtMessageReq.class),
    RECEIVE_MT("", MtMessageReq.class),
    RECEIVE_PU("", PushMessageReq.class),
    RECEIVE_EM("", EmailMessageReq.class),

    SEND_AT("", Object.class),
    SEND_FT("", Object.class),
    SEND_BT("", Object.class),
    SEND_SMS("", Object.class),
    SEND_LMS("", Object.class),
    SEND_MMS("", Object.class),
    SEND_PUSH("", Object.class),
    SEND_EM("", Object.class),

    ANALYZE_LOG("", ImsAnalyzeLog.class),
    ;

    private final String command;
    private final Class<?> commandType;

    ImsPacketCommand(String command, Class<?> commandType) {
        this.command = command;
        this.commandType = commandType;
    }
}
