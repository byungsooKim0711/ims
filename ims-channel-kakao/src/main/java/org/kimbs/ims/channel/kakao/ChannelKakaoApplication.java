package org.kimbs.ims.channel.kakao;

import org.kimbs.ims.model.kakao.AtMessageRes;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableScheduling
@SpringBootApplication
public class ChannelKakaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChannelKakaoApplication.class, args);
    }

    @RestController
    public static class TT {

        @GetMapping("/sendMmessage")
        public AtMessageRes test() {
            AtMessageRes res = new AtMessageRes();
            res.setCode("0000");

            return res;
        }
    }

}
