package org.kimbs.ims.protocol;

import lombok.Getter;
import org.kimbs.ims.model.kakao.AtMessageReq;
import org.kimbs.ims.model.kakao.BtMessageReq;
import org.kimbs.ims.model.kakao.FtMessageReq;

@Getter
public enum ImsPacketCommand {

    RECEIVE_AT(AtMessageReq.class),
    RECEIVE_FT(FtMessageReq.class),
    RECEIVE_BT(BtMessageReq.class),
    RECEIVE_MT(Object.class),
    RECEIVE_PU(Object.class),
    RECEIVE_EM(Object.class),
    ;

    private final Class<?> commandType;

    ImsPacketCommand(Class<?> commandType) {
        this.commandType = commandType;
    }
}
