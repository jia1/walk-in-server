package com.jiayee.walkin.model;

import com.jiayee.walkin.model.audit.UserDateAudit;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;

@Data
@Entity
@Table(name = "slots")
public class Slot extends UserDateAudit implements Comparable<Slot> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @NotBlank
  private Instant start;

  private Instant end;

  @NonNull
  @NotBlank
  private Recurrence recurrence;

  @ManyToOne(optional = false)
  @JoinColumn(
      name = "interview_id",
      nullable = false
  )
  private Interview interview;

  @ManyToMany
  @JoinTable(
      name = "slot_interviewees",
      joinColumns = @JoinColumn(name = "slot_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "interviewee_id", referencedColumnName = "id")
  )
  private List<Interviewee> interviewees;

  @Override
  public int compareTo(Slot slot) {
    return Comparator
        .comparing(Slot::getStart)
        .thenComparing(Slot::getEnd)
        .thenComparing(Slot::getId)
        .compare(this, slot);
  }

  @Override
  public boolean equals(Object o) {
    return getClass() == o.getClass()
        && getId().equals(((Slot) o).getId());
  }

  @AllArgsConstructor
  enum Recurrence {

    NONE(-1),
    WEEKLY(604800);

    @Getter
    private final int interval;

  }

}
