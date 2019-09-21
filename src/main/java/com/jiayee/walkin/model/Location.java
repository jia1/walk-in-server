package com.jiayee.walkin.model;

import lombok.Data;
import lombok.NonNull;

@Data
class Location {

  @NonNull
  private final String name;

  @NonNull
  private final double latitude;

  @NonNull
  private final double longitude;

}
