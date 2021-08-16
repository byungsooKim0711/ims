package org.kimbs.ims.store.domain.stat;

import lombok.Getter;
import org.kimbs.ims.store.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_AGENT_EMAIL_SEND_HIST", indexes = {

})
public class AgentEmailSendStat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
