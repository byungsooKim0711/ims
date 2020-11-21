package org.kimbs.ims.kakao.controller;

import lombok.extern.slf4j.Slf4j;
import org.kimbs.ims.protocol.ImsResponse;
import org.kimbs.ims.protocol.code.ResponseCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
public class AtController extends AbstractImsController {

    @GetMapping("/{serviceKey}/at")
    public ResponseEntity<ImsResponse<List<String>>> at(@PathVariable String serviceKey) {
        return ResponseEntity.ok(ImsResponse.<List<String>>builder()
                .code(ResponseCode.SUCCESS)
                .data(Arrays.asList(serviceKey))
                .build());
    }
}
