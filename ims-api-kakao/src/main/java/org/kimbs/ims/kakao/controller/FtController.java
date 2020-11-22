package org.kimbs.ims.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.kakao.service.FtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(AbstractImsController.V1_PATH)
@RestController
public class FtController extends AbstractImsController<ImsBizFtReq> {

    private final FtService ftService;

    public FtController(FtService ftService) {
        this.ftService = ftService;
    }

    @PostMapping("/ft/sendMessage")
    @Override
    protected ResponseEntity<ImsCommonRes<Void>> sendMessage(@PathVariable String serviceKey, @RequestBody ImsBizFtReq imsBizReq) {
        log.info("service key: {}", serviceKey);

        ftService.sendMessage(serviceKey, imsBizReq);

        return ResponseEntity.ok(ImsCommonRes.<Void>builder()
                .code(ResponseCode.SUCCESS)
                .build());
    }
}
