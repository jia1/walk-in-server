package com.jiayee.walkin.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.NonNull;
import lombok.Value;

@Builder
@Value
@Entity
@Table(name = "locations")
public class Location {

  @NonNull
  @NotBlank
  @Size(min = 2, max = 128)
  private final String name;

  @NonNull
  @NotBlank
  private final double latitude;

  @NonNull
  @NotBlank
  private final double longitude;

}
