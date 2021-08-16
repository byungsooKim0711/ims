package org.kimbs.ims.store.domain;

import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Entity
@Table(name = "TB_SEND_PROFILE")
public class SendProfile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "SENDER_KEY")
    private String senderKey;

    @Column(name = "SENDER_KEY_GROUP")
    private String senderKeyGroup;

    @Column(name = "UUID")
    private String uuid;

    @Column(name = "NAME")
    private String name;

    @Column(name = "STATUS")
    private String status;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "BLOCK", nullable = false, length = 1)
    private boolean block;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "DORMANT", nullable = false, length = 1)
    private boolean dormant;

    @Column(name = "PROFILE_STATUS")
    private String profileStatus;

    @Column(name = "CATEGORY_CODE")
    private String categoryCode;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "ALIMTALK", nullable = false, length = 1)
    private boolean alimtalk;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "BIZCHAT", nullable = false, length = 1)
    private boolean bizchat;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'N'")
    @Column(name = "BRANDTALK", nullable = false, length = 1)
    private boolean brandtalk;

    @Column(name = "COMMITAL_COMPANY_NAME")
    private String commitalCompanyName;

    @Column(name = "CHANNEL_KEY")
    private String channelKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID", foreignKey = @ForeignKey(name = "FK_CUSTOMER_SEND_PROFILE"))
    private Customer customer;
}
