package org.kimbs.ims.store.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_ROUTING_TOPIC", indexes = {
})
public class RoutingTopic extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOPIC", length = 100)
    private String topic;

    @Column(name = "PARTITION")
    private Integer partition;

    @Column(name = "REPLICATION_FACTOR")
    private Integer replicationFactor;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROUTING_GROUP_ID", foreignKey = @ForeignKey(name = "FK_ROUTING_TOPIC_ROUTING_GROUP"))
    private RoutingGroup routingGroup;
}
