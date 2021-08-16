package org.kimbs.ims.store.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_AGENT_INFO", indexes = {
        @Index(name = "IDX_AGENT_INFO_01", columnList = "AGENT_USERNAME,AGENT_PASSWORD,ACTIVE_YN")
})
public class AgentInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "AGENT_USERNAME", nullable = false, length = 30)
    private String agentUsername;

    @Column(name = "AGENT_PASSWORD", nullable = false, length = 30)
    private String agentPassword;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'Y'")
    @Column(name = "ACTIVE_YN", nullable = false, length = 1)
    private boolean activeYn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_AGENT_INFO"))
    private Customer customer;
}
