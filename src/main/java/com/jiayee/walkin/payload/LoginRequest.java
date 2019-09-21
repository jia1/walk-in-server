package com.jiayee.walkin.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
public class LoginRequest {

  @NonNull
  @NotBlank
  @Size(min = 2, max = 256)
  private String usernameOrEmail;

  @NonNull
  @NotBlank
  @Size(min = 8, max = 128)
  private String password;

}
