package org.kimbs.ims.domain.hist;

import lombok.Getter;
import org.kimbs.ims.domain.BaseTimeEntity;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "TB_API_MT_SEND_HIST", indexes = {

})
public class ApiMtSendHist extends BaseTimeEntity {

    // report_at, report_type, report_code

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // customer_id
    // agent_id
    // template_id
    // message
    // title
    // process_yn
    // received_at
    // response_at
    // distribution_at
    // sent_at
    // report_at
    // message_id
    // serial_number
    // message_type
    // country_code
    // callback
    // phone_number
    // bill_code

    // created_at, modified_at
}
