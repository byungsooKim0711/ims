package org.kimbs.ims.protocol.v1.kakao.at;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class Receiver {

    // 국가번호 포함 수신자번호
    @Length(min = 1, max = 20)
    @JsonProperty("phone_number")
    private String phoneNumber;

    // 앱 유저 아이디
    @Length(min = 1, max = 20)
    @JsonProperty("app_user_id")
    private String appUserId;
}
