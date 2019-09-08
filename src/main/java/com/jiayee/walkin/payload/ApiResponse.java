package com.jiayee.walkin.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ApiResponse {

  private Boolean isSuccess;

  private String message;

}
