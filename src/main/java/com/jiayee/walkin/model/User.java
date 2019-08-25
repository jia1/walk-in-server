package com.jiayee.walkin.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = {
    @UniqueConstraint(columnNames = {
        "username"
    }),
    @UniqueConstraint(columnNames = {
        "email"
    })
})
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Getter
  @Setter
  private Long id;

  @NotBlank
  @Size(max = 128)
  @Getter
  @Setter
  private String name;

  @NotBlank
  @Size(max = 32)
  @Getter
  @Setter
  private String username;

  @NaturalId
  @NotBlank
  @Size(max = 256)
  @Email
  @Getter
  @Setter
  private String email;

  @NotBlank
  @Size(max = 128)
  @Getter
  @Setter
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id"),
      inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  @Getter
  @Setter
  private Set<Role> roles = new HashSet<>();

  public User(
      String firstName,
      String middleName,
      String lastName,
      String username,
      String email,
      String password
  ) {
    this.name = new Name(firstName, middleName, lastName).serialize();
    this.username = username;
    this.email = email;
    this.password = password;
  }

}
