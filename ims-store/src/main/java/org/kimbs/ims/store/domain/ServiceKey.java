package org.kimbs.ims.store.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.store.domain.code.ServiceKeyType;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "TB_SERVICE_KEY")
@Entity
public class ServiceKey extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "API_KEY", length = 32)
    private String apiKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "KEY_TYPE", length = 5)
    private ServiceKeyType keyType;

    @Convert(converter = BooleanYNConverter.class)
    @Column(name = "USE_YN", length = 1)
    @ColumnDefault("'Y'")
    private boolean useYn = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "WEB_USER_ID", foreignKey = @ForeignKey(name = "FK_WEB_USER_SERVICE_KEY"))
    private WebUser webUser;

    @Builder
    public ServiceKey(String apiKey, ServiceKeyType keyType, boolean useYn, WebUser webUser) {
        this.apiKey = apiKey;
        this.keyType = keyType;
        this.useYn = useYn;
        this.webUser = webUser;
    }

    public void setWebUser(WebUser webUser) {
        this.webUser = webUser;
    }
}
