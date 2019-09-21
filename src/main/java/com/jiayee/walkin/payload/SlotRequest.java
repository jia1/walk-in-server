package com.jiayee.walkin.payload;

import com.jiayee.walkin.model.Recurrence;
import java.time.Instant;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SlotRequest {

  @NotNull
  @NotBlank
  private final Instant start;

  private Instant end;

  @NonNull
  @NotBlank
  private Recurrence recurrence;

  @NonNull
  @NotBlank
  private Long interviewId;

}
