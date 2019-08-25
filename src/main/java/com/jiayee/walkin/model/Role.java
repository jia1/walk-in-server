package com.jiayee.walkin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "roles")
public class Role {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @NaturalId
  @Enumerated(EnumType.STRING)
  @Column(length = 128)
  @Getter
  @Setter
  private RoleName name;

  public enum RoleName {
    ROLE_ADMIN,
    ROLE_USER
  }

}
