package org.kimbs.ims.domain.hist;

import lombok.Getter;
import org.kimbs.ims.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_AGENT_MT_SEND_HIST", indexes = {

})
public class AgentMtSendHist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
