package org.kimbs.ims.api.kakao.controller;

import org.kimbs.ims.protocol.ImsApiResult;
import reactor.core.publisher.Mono;

public abstract class AbstractImsController<R> {

    /**
     * PathVariable: serviceKey
     * RequestType: R
     * @return
     */
    protected abstract Mono<ImsApiResult<Void>> sendMessage(R request);
}
