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
public class FtController extends AbstractImsController {

    @GetMapping("/{serviceKey}/ft")
    public ResponseEntity<ImsResponse<String>> ft(@PathVariable String serviceKey) {
        ImsResponse<String> imsResponse = new ImsResponse<>(ResponseCode.SUCCESS, serviceKey);

        return ResponseEntity.ok(imsResponse);
    }
}
