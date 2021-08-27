package org.kimbs.ims.api.kakao.controller;

import org.kimbs.ims.api.kakao.service.AbstractKakaoService;
import org.kimbs.ims.protocol.ImsApiResult;
import reactor.core.publisher.Mono;

public abstract class AbstractImsController<R> {

    protected AbstractKakaoService<R, ?> abstractKakaoService;

    public AbstractImsController(AbstractKakaoService<R, ?> abstractKakaoService) {
        this.abstractKakaoService = abstractKakaoService;
    }

    /**
     * PathVariable: serviceKey
     * RequestType: R
     * @return
     */
    protected abstract Mono<ImsApiResult<Void>> sendMessage(R request);
}
