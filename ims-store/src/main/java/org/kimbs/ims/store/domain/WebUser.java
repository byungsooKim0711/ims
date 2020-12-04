package org.kimbs.ims.store.domain;

import lombok.Getter;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "TB_WEB_USER", indexes = {
        @Index(name = "idx_web_user_01", columnList = "LOGIN_ID", unique = true)
})
public class WebUser extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "LOGIN_ID", unique = true, nullable = false, length = 45)
    private String loginId;

    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;

    @Convert(converter = BooleanYNConverter.class)
    @Column(name = "ACTIVE_YN", columnDefinition = "VARCHAR(1) DEFAULT 'Y'", nullable = false)
    private boolean activeYn = true;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "webUser")
    private List<ServiceKey> serviceKeyList = new ArrayList<>();

    public void addApiKey(List<ServiceKey> serviceKeyList) {
        serviceKeyList.forEach(this::addServiceKey);
    }

    public void addServiceKey(ServiceKey serviceKey) {
        this.serviceKeyList.add(serviceKey);
        serviceKey.setWebUser(this);
    }
}
