package com.jiayee.walkin.payload;

import com.jiayee.walkin.model.Location;
import com.jiayee.walkin.model.Slot;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
public class InterviewResponse {

  @NonNull
  private Long id;

  @NonNull
  private String title;

  @NonNull
  private Location location;

  private Instant expiration;

  private List<Slot> slots;

}
