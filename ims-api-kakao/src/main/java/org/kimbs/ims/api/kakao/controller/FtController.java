package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.service.FtService;
import org.kimbs.ims.protocol.ImsApiResult;
import org.kimbs.ims.protocol.v1.kakao.ft.ImsBizFtReq;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@RestController
public class FtController extends AbstractImsController<ImsBizFtReq> {


    public FtController(FtService ftService) {
        super(ftService);
    }

    @Override
    @PostMapping("/ft/sendMessage")
    public Mono<ImsApiResult<Void>> sendMessage(@Valid @RequestBody ImsBizFtReq request) {
        Mono<ImsApiResult<Void>> response = abstractKakaoService.sendMessage(request);

        return response;
    }
}
