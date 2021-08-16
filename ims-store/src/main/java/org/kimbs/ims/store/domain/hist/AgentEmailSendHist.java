package org.kimbs.ims.store.domain.hist;

import lombok.Getter;
import org.kimbs.ims.store.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_AGENT_EMAIL_SEND_HIST", indexes = {

})
public class AgentEmailSendHist extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
