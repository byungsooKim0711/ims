package org.kimbs.ims.api.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.api.kakao.service.FtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.v1.ImsBizFtReq;
import org.springframework.http.HttpStatus;
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
        ImsCommonRes<Void> response = ftService.sendMessage(serviceKey, imsBizReq);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}