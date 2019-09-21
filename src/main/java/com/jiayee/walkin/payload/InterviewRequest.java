package com.jiayee.walkin.payload;

import com.jiayee.walkin.model.Slot;
import java.time.Instant;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class InterviewRequest {

  @NonNull
  @NotBlank
  @Size(max = 140)
  private String title;

  @NonNull
  @NotBlank
  private String locationName;

  @NonNull
  @NotBlank
  private double locationLatitude;

  @NonNull
  @NotBlank
  private double locationLongitude;

  private Instant expiration;

  private List<Slot> slots;

}
