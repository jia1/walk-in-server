package com.jiayee.walkin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiResponse {

  private Boolean isSuccess;

  private String message;

}
