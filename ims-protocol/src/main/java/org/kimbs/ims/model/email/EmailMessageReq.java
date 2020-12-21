package org.kimbs.ims.model.email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Getter
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EmailMessageReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -3864481206123352813L;

    @JsonProperty("serial_number")
    private String serialNumber;
}
