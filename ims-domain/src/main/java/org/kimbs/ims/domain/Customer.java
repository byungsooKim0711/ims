package org.kimbs.ims.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_CUSTOMER", indexes = {
        @Index(name = "IDX_CUSTOMER_01", columnList = "USERNAME,PASSWORD,ACTIVE_YN", unique = true)
})
public class Customer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "USERNAME", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "PASSWORD", nullable = false, length = 128)
    private String password;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'Y'")
    @Column(name = "ACTIVE_YN", nullable = false, length = 1)
    private boolean activeYn = true;

    @Column(name = "SECRET_KEY", length = 20, nullable = false)
    private String secretKey;
}
