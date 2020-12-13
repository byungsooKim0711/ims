package org.kimbs.ims.store.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_TEMPLATE_CODE")
public class Template extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "PROFILE_ID", nullable = false)
    private long profileId;

    @Column(name = "TEMPLATE_CODE", nullable = false, length = 30)
    private String templateCode;

    @Column(name = "CUSTOM_TEMPLATE_CODE", nullable = false, length = 30)
    private String customTemplateCode;

    @Column(name = "TEMPLATE_NAME", nullable = false, length = 128)
    private String templateName;

    @Column(name = "TEMPLATE_CONTENT", nullable = false, length = 3000)
    private String templateContent;

    @Column(name = "INSPECTION_STATUS", nullable = false, length = 3)
    private String inspectionStatus;

    @Column(name = "STATUS", nullable = false, length = 1)
    private String status;

    @Column(name = "COMMENTS")
    private String comments;

    @Column(name = "ATTACHMENT_JSON", length = 4000)
    private String attachmentJson;

    @Column(name = "SUPPLEMENT_JSON", length = 4000)
    private String supplementtJson;

    @Column(name = "TIMEOUT", columnDefinition = "INT(5) DEFAULT 10", nullable = false)
    private int timeout = 10;
}
