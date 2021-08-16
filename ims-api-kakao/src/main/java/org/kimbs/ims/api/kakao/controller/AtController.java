package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.service.AtService;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.v1.kakao.at.ImsBizAtReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
public class AtController extends AbstractImsController<ImsBizAtReq> {

    private final AtService atService;

    public AtController(AtService atService) {
        this.atService = atService;
    }

    @Override
    @PostMapping("/at/sendMessage")
    public Mono<ImsApiResult<Void>> sendMessage(@Valid @RequestBody ImsBizAtReq request) {
        Mono<ImsApiResult<Void>> response = atService.sendMessage(request);

        return response;
    }
}
