package com.jiayee.walkin.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.NaturalId;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
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
  private Long id;

  @NonNull
  @NotBlank
  @Size(min = 2, max = 128)
  private String name;

  @NonNull
  @NotBlank
  @Size(min = 2, max = 32)
  private String username;

  @NaturalId
  @NonNull
  @NotBlank
  @Size(max = 256)
  @Email
  private String email;

  @NonNull
  @NotBlank
  @Size(min = 8, max = 128)
  private String password;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(
      name = "user_interviewees",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "interviewee_id", referencedColumnName = "id")
  )
  private Interviewee interviewee;

  @ManyToMany
  @JoinTable(
      name = "user_roles",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
  )
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
