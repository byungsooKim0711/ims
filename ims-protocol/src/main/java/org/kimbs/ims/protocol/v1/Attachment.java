package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.kimbs.ims.protocol.code.ButtonType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Attachment implements Serializable {

    private static final long serialVersionUID = -6543589345541122081L;

    // 버튼 정보 리스트
    @JsonProperty("button")
    private List<Button> buttonList = new ArrayList<>();

    // 이미지 정보
    @JsonProperty("image")
    private Image image;

    /**
     * 버튼 정보
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Button implements Serializable {

        private static final long serialVersionUID = 2752902888555603099L;

        // 버튼명
        @JsonProperty("name")
        private final String name;

        // 버튼 타입
        @JsonProperty("type")
        private final ButtonType buttonType;

        // pc 환경에서 버튼 클릭 시 이동할 url
        @JsonProperty("url_pc")
        private String urlPc;

        // mobile 환경에서 버튼 클릭 시 이동할 url
        @JsonProperty("url_mobile")
        private String urlMobile;

        // mobile ios 환경에서 버튼 클릭 시 실행할 application custom scheme
        @JsonProperty("scheme_ios")
        private String schemeIos;

        // mobile android 환경에서 버튼 클릭 시 실행할 application custom scheme
        @JsonProperty("scheme_android")
        private String schemeAndroid;

        // target 속성. "target":"out" 속성 추가시 아웃링크
        @JsonProperty("target")
        private String target;

        // extra
        @JsonProperty("chat_extra")
        private String chatExtra;

        public Button(@JsonProperty("name") String name, @JsonProperty("type") ButtonType buttonType) {
            this.name = name;
            this.buttonType = buttonType;
        }
    }

    /**
     * 이미지 정보
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Image implements Serializable {

        private static final long serialVersionUID = 6313831688791825778L;

        // 노출할 이미지
        @JsonProperty("img_url")
        private final String imgUrl;

        // 이미지 클릭시 이동할 url
        @JsonProperty("img_link")
        private String imgLink;

        public Image(@JsonProperty("img_url") String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}