package org.kimbs.ims.protocol.v1;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ImsEmailReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = 8143825282940512760L;

    @JsonProperty("msg_uid")
    private String msgUid;


}
