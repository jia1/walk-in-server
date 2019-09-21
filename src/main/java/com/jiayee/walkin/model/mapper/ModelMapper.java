package com.jiayee.walkin.model.mapper;

import com.jiayee.walkin.exception.InvalidInterviewIdException;
import com.jiayee.walkin.model.Interview;
import com.jiayee.walkin.model.Location;
import com.jiayee.walkin.model.Slot;
import com.jiayee.walkin.payload.InterviewRequest;
import com.jiayee.walkin.payload.InterviewResponse;
import com.jiayee.walkin.payload.SlotRequest;
import com.jiayee.walkin.payload.SlotResponse;
import com.jiayee.walkin.repository.InterviewRepository;
import java.util.ArrayList;
import java.util.Optional;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;

public class ModelMapper {

  @NonNull
  private final InterviewRepository interviewRepository;

  @Autowired
  public ModelMapper(
      InterviewRepository interviewRepository
  ) {
    this.interviewRepository = interviewRepository;
  }

  public Interview interviewRequestToInterview(InterviewRequest request) {
    return Interview
        .builder()
        .location(Location
            .builder()
            .name(request.getLocationName())
            .latitude(request.getLocationLatitude())
            .longitude(request.getLocationLongitude())
            .build()
        )
        .expiration(request.getExpiration())
        .slots(
            Optional
                .ofNullable(request.getSlots())
                .orElse(new ArrayList<>())
        )
        .build();
  }

  public InterviewResponse interviewToInterviewResponse(Interview interview) {
    return InterviewResponse
        .builder()
        .id(interview.getId())
        .title(interview.getTitle())
        .location(interview.getLocation())
        .expiration(interview.getExpiration())
        .slots(interview.getSlots())
        .build();
  }

  public Slot slotRequestToSlot(SlotRequest request) throws InvalidInterviewIdException {
    Interview interview = interviewRepository
        .findById(request.getInterviewId())
        .orElseThrow(() -> new InvalidInterviewIdException(
            "Interview with ID: %s does not exist in the repository."
        ));
    return Slot
        .builder()
        .start(request.getStart())
        .end(request.getEnd())
        .recurrence(request.getRecurrence())
        .interview(
            interviewRepository
                .findById(request.getInterviewId())
                .orElse(null)
        )
        .build();
  }

  public SlotResponse slotToSlotResponse(Slot slot) {
    return SlotResponse
        .builder()
        .id(slot.getId())
        .start(slot.getStart())
        .end(slot.getEnd())
        .recurrence(slot.getRecurrence())
        .interview(slot.getInterview())
        .interviewees(slot.getInterviewees())
        .build();
  }

}
