package org.kimbs.ims.model.push;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PushMessageReq extends AbstractMessage {

    private static final long serialVersionUID = -4115581551999736346L;

    @JsonProperty("serial_number")
    private String serialNumber;

}
