package org.kimbs.ims.store.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_SEND_PROFILE")
public class SendProfile extends BaseTimeEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
