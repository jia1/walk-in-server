package com.jiayee.walkin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.NaturalId;

@Builder
@Data
@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NaturalId
  @Enumerated(EnumType.STRING)
  @Column(length = 64)
  private RoleName name;

  public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER
  }

}
