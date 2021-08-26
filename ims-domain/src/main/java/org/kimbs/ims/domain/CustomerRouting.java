package org.kimbs.ims.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_CUSTOMER_ROUTING", indexes = {

})
public class CustomerRouting extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_ROUTING_CUSTOMER"))
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTING_GROUP_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_ROUTING_ROUTING_GROUP"))
    private RoutingGroup routingGroup;

    // description
    @Column(name = "description", length = 255)
    private String description;
}
