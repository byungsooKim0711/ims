package org.kimbs.ims.domain;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_KAKAO_TEMPLATE")
public class KakaoTemplate extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROFILE_ID", foreignKey = @ForeignKey(name = "FK_SEND_PROFILE_KAKAO_TEMPLATE"))
    private SendProfile sendProfile;

    @Column(name = "TEMPLATE_CODE", nullable = false, length = 30)
    private String templateCode;

    @Column(name = "TEMPLATE_NAME", nullable = false, length = 128)
    private String templateName;

    @Column(name = "CUSTOM_TEMPLATE_CODE", nullable = false, length = 30)
    private String customTemplateCode;
}
