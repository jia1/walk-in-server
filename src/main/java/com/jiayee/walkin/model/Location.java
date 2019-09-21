package com.jiayee.walkin.model;

import lombok.NonNull;
import lombok.Value;

@Value
public class Location {

  @NonNull
  private final String name;

  @NonNull
  private final double latitude;

  @NonNull
  private final double longitude;

}
