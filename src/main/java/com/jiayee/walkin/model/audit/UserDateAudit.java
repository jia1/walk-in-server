package com.jiayee.walkin.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

@Data
@MappedSuperclass
@JsonIgnoreProperties(
    value = {
        "createdBy",
        "updatedBy"
    },
    allowGetters = true
)
public abstract class UserDateAudit extends DateAudit {

  @CreatedBy
  @Column(updatable = false)
  private Long createdBy;

  @LastModifiedBy
  private Long updatedBy;

}
