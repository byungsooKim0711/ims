package org.kimbs.ims.store.domain;

import lombok.Getter;
import lombok.Setter;
import org.kimbs.ims.store.domain.converter.LocalDateTimeConverter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @Column(name = "created_at", length = 19)
    @CreatedDate
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime createdAt;

    @Column(name = "modified_at", length = 19)
    @LastModifiedDate
    @Convert(converter = LocalDateTimeConverter.class)
    private LocalDateTime modifiedAt;
}
