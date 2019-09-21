package com.jiayee.walkin.payload;

import com.jiayee.walkin.model.Slot;
import java.time.Instant;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class InterviewRequest {

  @NotNull
  @NotBlank
  private String title;

  @NotNull
  @NotBlank
  private String locationName;

  @NotNull
  @NotBlank
  private double locationLatitude;

  @NotNull
  @NotBlank
  private double locationLongitude;

  private Instant expiration;

  private List<Slot> slots;

}
