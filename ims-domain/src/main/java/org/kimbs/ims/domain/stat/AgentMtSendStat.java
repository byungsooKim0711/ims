package org.kimbs.ims.domain.stat;

import lombok.Getter;
import org.kimbs.ims.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_AGENT_MT_SEND_STAT", indexes = {

})
public class AgentMtSendStat extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
