package org.kimbs.ims.api.kakao.controller;

import org.kimbs.ims.protocol.ImsCommonRes;
import org.springframework.http.ResponseEntity;

public abstract class AbstractImsController<R> {

    /**
     * PathVariable: serviceKey
     * RequestType: R
     */
    protected abstract ResponseEntity<ImsCommonRes<Void>> sendMessage(String serviceKey, R request);

//    protected abstract void process(R request);
}
