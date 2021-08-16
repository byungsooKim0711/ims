package org.kimbs.ims.store.domain;


import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_ROUTING_GROUP", indexes = {
})
public class RoutingGroup extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // AT/FT/MT/PU/EM
    @Column(name = "CHANNEL_TYPE", length = 2)
    private String channelType;

    @Column(name = "GROUP_NAME", length = 100)
    private String groupName;

    @Column(name = "DESCRIPTION", length = 100)
    private String description;
}
