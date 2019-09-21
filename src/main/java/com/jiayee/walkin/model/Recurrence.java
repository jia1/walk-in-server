package com.jiayee.walkin.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Recurrence {

  NONE(-1),
  WEEKLY(604800);

  @Getter
  private final int interval;

}
