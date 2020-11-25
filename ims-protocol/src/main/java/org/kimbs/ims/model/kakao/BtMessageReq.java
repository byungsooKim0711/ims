package org.kimbs.ims.model.kakao;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.kimbs.ims.protocol.AbstractMessage;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class BtMessageReq extends AbstractMessage implements Serializable {

    private static final long serialVersionUID = -5943369529462819244L;

}
