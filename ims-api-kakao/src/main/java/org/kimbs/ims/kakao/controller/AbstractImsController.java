package org.kimbs.ims.kakao.controller;

import org.kimbs.ims.protocol.ImsCommonRes;
import org.springframework.http.ResponseEntity;

public abstract class AbstractImsController<R> {

    protected final static String V1_PATH = "/ims/v1/{serviceKey}";

    protected abstract ResponseEntity<ImsCommonRes<Void>> sendMessage(String serviceKey, R imsBizReq);
}
