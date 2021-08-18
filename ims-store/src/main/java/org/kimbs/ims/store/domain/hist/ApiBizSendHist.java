package org.kimbs.ims.store.domain.hist;

import lombok.Getter;
import org.kimbs.ims.store.domain.BaseTimeEntity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "TB_API_BiZ_SEND_HIST", indexes = {

})
public class ApiBizSendHist extends BaseTimeEntity {

    // report_at, report_type, report_code
    // TODO: BIZ_SEND_REQUEST / BIZ_SEND_RESPONSE / BIZ_SEND_REPORT 나눠야 하나...

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Long customerId;
    // api_info_id
    @Column(name = "API_INFO_ID")
    private Long apiInfoId;
    // profile_id
    @Column(name = "PROFILE_ID")
    private Long profileId;
    // template_id
    @Column(name = "TEMPLATE_ID")
    private Long templateId;
    // message
    @Column(name = "MESSAGE")
    private String message;
    // title
    @Column(name = "TITLE")
    private String title;
    // header
    @Column(name = "HEADER")
    private String header;
    // attachment
    @Column(name = "ATTACHMENT")
    private String attachment;
    // supplement
    @Column(name = "SUPPLEMENT")
    private String supplement;
    // process_yn
    @Column(name = "PROCESS_YN")
    private String processYn;
    // received_at
    @Column(name = "RECEIVED_AT")
    private LocalDateTime receivedAt;
    // response_at
    @Column(name = "RESPONSE_AT")
    private LocalDateTime responseAt;
    // distribution_at
    @Column(name = "DISTRIBUTION_AT")
    private LocalDateTime distributionAt;
    // sent_at
    @Column(name = "SENT_AT")
    private LocalDateTime sentAt;
    // report_at
    @Column(name = "REPORT_AT")
    private LocalDateTime reportAt;
    // message_id
    @Column(name = "MESSAGE_ID")
    private String messageId;
    // serial_number
    @Column(name = "SERIAL_NUMBER")
    private String serialNumber;
    // send_type
    @Column(name = "SEND_TYPE")
    private String sendType;
    // country_code
    @Column(name = "COUNTRY_CODE")
    private String countryCode;
    // app_user_id
    @Column(name = "APP_USER_ID")
    private String appUserId;
    // phone_number
    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;
    // bill_code
    @Column(name = "BILL_CODE")
    private String billCode;
}
