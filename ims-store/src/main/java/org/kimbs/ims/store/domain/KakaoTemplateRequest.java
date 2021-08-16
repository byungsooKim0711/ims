package org.kimbs.ims.store.domain;

import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;
import org.kimbs.ims.store.domain.converter.BooleanYNConverter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_KAKAO_TEMPLATE_REQUEST")
public class KakaoTemplateRequest extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID", foreignKey = @ForeignKey(name = "FK_SEND_PROFILE_KAKAO_TEMPLATE_REQUEST"))
    private SendProfile sendProfile;

    @Column(name = "TEMPLATE_CODE", nullable = false, length = 30)
    private String templateCode;

    @Column(name = "TEMPLATE_NAME", nullable = false, length = 128)
    private String templateName;

    @Column(name = "CUSTOM_TEMPLATE_CODE", nullable = false, length = 30)
    private String customTemplateCode;

    @Column(name = "TEMPLATE_MESSAGE_TYPE")
    private String templateMessageType;

    @Column(name = "TEMPLATE_EMPHASIZE_TYPE")
    private String templateEmphasizeType;

    @Column(name = "TEMPLATE_CONTENT", nullable = false, length = 3000)
    private String templateContent;

    @Column(name = "TEMPLATE_EXTRA")
    private String templateExtra;

    @Column(name = "TEMPLATE_AD")
    private String templateAd;

    @Column(name = "TEMPLATE_IMAGE_NAME")
    private String templateImageName;

    @Column(name = "TEMPLATE_IMAGE_URL")
    private String templateImageUrl;

    @Column(name = "TEMPLATE_TITLE")
    private String templateTitle;

    @Column(name = "TEMPLATE_SUBTITLE")
    private String templateSubtitle;

    @Column(name = "TEMPLATE_HEADER")
    private String templateHeader;

    @Column(name = "TEMPLATE_ITEM_HIGHLIGHT")
    private String templateItemHighlight;

    @Column(name = "TEMPLATE_ITEM")
    private String templateItem;

    @Column(name = "CATEGORY_CODE")
    private String categoryCode;

    @Convert(converter = BooleanYNConverter.class)
    @ColumnDefault(value = "'Y'")
    @Column(name = "SECURITY_FLAG", nullable = false, length = 1)
    private boolean securityFlag;

    @Column(name = "BUTTONS", length = 4000)
    private String buttons;

    @Column(name = "QUICK_REPLIES", length = 4000)
    private String quickReplies;

    @Column(name = "INSPECTION_STATUS", nullable = false, length = 3)
    private String inspectionStatus;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "COMMENTS")
    private String comments;
}
