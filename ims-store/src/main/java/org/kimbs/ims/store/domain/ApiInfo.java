package org.kimbs.ims.store.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_API_INFO", indexes = {
        @Index(name = "IDX_API_INFO_01", columnList = "API_KEY,ACTIVE_YN")
})
public class ApiInfo extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "API_KEY", nullable = false, length = 30)
    private String apiKey;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "ACTIVE_YN", nullable = false, length = 1)
    private boolean activeYn = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_API_INFO"))
    private Customer customer;

    // TODO: 라우팅 정보는 어떻게 해야할까?
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "ROUTING_GROUP_ID", foreignKey = @ForeignKey(name = "FK_ROUTING_GROUP_API_INFO"))
//    private RoutingGroup routingGroup;
}
