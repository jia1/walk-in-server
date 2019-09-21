package com.jiayee.walkin.payload;

import lombok.Data;
import lombok.NonNull;

@Data
public class JwtAuthenticationResponse {

  @NonNull
  private String accessToken;

  private String tokenType = "Bearer";

}
