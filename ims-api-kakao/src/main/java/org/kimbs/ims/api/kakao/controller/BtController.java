package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.service.BtService;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.v1.kakao.bt.ImsBizBtReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
public class BtController extends AbstractImsController<ImsBizBtReq> {

    private final BtService btService;

    public BtController(BtService btService) {
        this.btService = btService;
    }

    @Override
    @PostMapping("/bt/sendMessage")
    public Mono<ImsApiResult<Void>> sendMessage(@Valid @RequestBody ImsBizBtReq request) {
        Mono<ImsApiResult<Void>> response = btService.sendMessage(request);

        return response;
    }
}
