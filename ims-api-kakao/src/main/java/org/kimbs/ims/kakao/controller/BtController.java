package org.kimbs.ims.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsResponse;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class BtController extends AbstractImsController {

    @GetMapping("/{serviceKey}/bt")
    public ResponseEntity<ImsResponse<String>> bt(@PathVariable String serviceKey) {
        ImsResponse<String> imsResponse = new ImsResponse<>(ResponseCode.SUCCESS, serviceKey);

        return ResponseEntity.ok(imsResponse);
    }
}
