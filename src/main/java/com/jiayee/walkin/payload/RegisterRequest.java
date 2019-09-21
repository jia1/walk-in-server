package com.jiayee.walkin.payload;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class RegisterRequest {

  @NonNull
  @Size(min = 2, max = 42)
  private String firstName;

  @Size(max = 42)
  private String middleName;

  @Size(max = 42)
  private String lastName;

  @NotBlank
  @Size(min = 2, max = 32)
  private String username;

  @NotBlank
  @Size(max = 256)
  @Email
  private String email;

  @NotBlank
  @Size(min = 8, max = 128)
  private String password;

}
