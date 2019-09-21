package com.jiayee.walkin.payload;

import com.jiayee.walkin.model.Interview;
import com.jiayee.walkin.model.Interviewee;
import com.jiayee.walkin.model.Recurrence;
import java.time.Instant;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@AllArgsConstructor
@Data
public class SlotResponse {

  @NonNull
  private Long id;

  @NonNull
  private Instant start;

  private Instant end;

  @NonNull
  private Recurrence recurrence;

  @NonNull
  private Interview interview;

  private List<Interviewee> interviewees;

}
