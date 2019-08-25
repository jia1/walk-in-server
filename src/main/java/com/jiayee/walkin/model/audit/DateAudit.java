package com.jiayee.walkin.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
    value = {
        "createdAt",
        "updatedAt"
    },
    allowGetters = true
)
public class DateAudit implements Serializable {

  @CreatedDate
  @Column(
      nullable = false,
      updatable = false
  )
  @Getter
  @Setter
  private Instant createdAt;

  @LastModifiedDate
  @Column(
      nullable = false
  )
  @Getter
  @Setter
  private Instant updatedAt;

}
