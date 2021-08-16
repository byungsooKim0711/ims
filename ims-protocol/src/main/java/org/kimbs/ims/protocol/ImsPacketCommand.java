package org.kimbs.ims.protocol;

import lombok.Getter;
import org.kimbs.ims.model.email.EmailMessageReq;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageReq;
import org.kimbs.ims.model.push.PushMessageReq;

@Getter
public enum ImsPacketCommand {

    RECEIVE_AT("", AtMessageReq.class),
    RECEIVE_FT("", FtMessageReq.class),
    RECEIVE_BT("", BtMessageReq.class),
    RECEIVE_MT("", Object.class),
    RECEIVE_PU("", PushMessageReq.class),
    RECEIVE_EM("", EmailMessageReq.class),
    ;

    private final String command;
    private final Class<?> commandType;

    ImsPacketCommand(String command, Class<?> commandType) {
        this.command = command;
        this.commandType = commandType;
    }
}
