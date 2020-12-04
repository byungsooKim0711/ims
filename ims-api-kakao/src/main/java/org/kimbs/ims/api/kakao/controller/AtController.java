package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.controller.annotation.Version1;
import org.kimbs.ims.api.kakao.service.AtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.v1.ImsBizAtReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@Version1
@RestController
public class AtController extends AbstractImsController<ImsBizAtReq> {

    private final AtService atService;

    public AtController(AtService atService) {
        this.atService = atService;
    }

    @Override
    @PostMapping("/at/sendMessage")
    protected Mono<ImsCommonRes<Void>> sendMessage(@PathVariable String serviceKey, @RequestBody ImsBizAtReq request) {
        Mono<ImsCommonRes<Void>> response = atService.sendMessage(serviceKey, request);

        return response;
    }
}
