package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.controller.annotation.Version1;
import org.kimbs.ims.api.kakao.service.FtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@Version1
@RestController
public class FtController extends AbstractImsController<ImsBizFtReq> {

    private final FtService ftService;

    public FtController(FtService ftService) {
        this.ftService = ftService;
    }

    @PostMapping("/ft/sendMessage")
    @Override
    protected Mono<ImsCommonRes<Void>> sendMessage(@PathVariable String serviceKey, @RequestBody ImsBizFtReq request) {
        Mono<ImsCommonRes<Void>> response = ftService.sendMessage(serviceKey, request);

        return response;
    }
}
