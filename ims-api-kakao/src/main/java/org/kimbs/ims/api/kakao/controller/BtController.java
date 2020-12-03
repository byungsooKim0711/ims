package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.controller.annotation.Version1;
import org.kimbs.ims.api.kakao.service.BtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.v1.ImsBizBtReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@Version1
@RestController
public class BtController extends AbstractImsController<ImsBizBtReq> {

    private final BtService btService;

    public BtController(BtService btService) {
        this.btService = btService;
    }

    @Override
    @PostMapping("/bt/sendMessage")
    protected Mono<ImsCommonRes<Void>> sendMessage(@PathVariable String serviceKey, @RequestBody ImsBizBtReq request) {
        Mono<ImsCommonRes<Void>> response = btService.sendMessage(serviceKey, request);

        return response;
    }
}
