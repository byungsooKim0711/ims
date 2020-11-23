package org.kimbs.ims.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.kakao.service.BtService;
import org.kimbs.ims.protocol.ImsCommonRes;
import org.kimbs.ims.protocol.v1.ImsBizBtReq;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping(AbstractImsController.V1_PATH)
@RestController
public class BtController extends AbstractImsController<ImsBizBtReq> {

    private final BtService btService;

    public BtController(BtService btService) {
        this.btService = btService;
    }

    @Override
    @PostMapping("/bt/sendMessage")
    protected ResponseEntity<ImsCommonRes<Void>> sendMessage(@PathVariable String serviceKey, @RequestBody ImsBizBtReq imsBizReq) {
        ImsCommonRes<Void> response = btService.sendMessage(serviceKey, imsBizReq);

        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
