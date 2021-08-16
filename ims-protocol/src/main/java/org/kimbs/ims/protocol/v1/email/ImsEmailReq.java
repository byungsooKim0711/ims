package org.kimbs.ims.protocol.v1.email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

@Getter
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsEmailReq extends AbstractMessage {

    private static final long serialVersionUID = 8143825282940512760L;

    @JsonProperty("msg_uid")
    private String msgUid;


}
