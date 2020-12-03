package org.kimbs.ims.api.kakao.controller;

import org.kimbs.ims.protocol.ImsCommonRes;
import reactor.core.publisher.Mono;

public abstract class AbstractImsController<R> {

    /**
     * PathVariable: serviceKey
     * RequestType: R
     * @return
     */
    protected abstract Mono<ImsCommonRes<Void>> sendMessage(String serviceKey, R request);
}
