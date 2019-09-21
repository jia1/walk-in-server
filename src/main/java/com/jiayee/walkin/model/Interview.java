package com.jiayee.walkin.model;

import com.jiayee.walkin.model.audit.UserDateAudit;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Builder
@Data
@Entity
@Table(name = "interviews")
public class Interview extends UserDateAudit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NonNull
  @NotBlank
  @Size(max = 140)
  private String title;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinTable(
      name = "interview_locations",
      joinColumns = @JoinColumn(name = "interview_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "location_id", referencedColumnName = "id")
  )
  private Location location;

  @NonNull
  private Instant expiration;

  @OneToMany(
      mappedBy = "interview",
      cascade = CascadeType.ALL,
      fetch = FetchType.EAGER,
      orphanRemoval = true
  )
  @Fetch(FetchMode.SELECT)
  @BatchSize(size = 30)
  private List<Slot> slots;

  public boolean addSlot(Slot slot) {
    int index = Collections.binarySearch(slots, slot);
    if (index >= 0) {
      slots.add(index, slot);
    } else {
      slots.add(-(index + 1), slot);
    }
    return true;
  }

  public boolean removeSlot(Slot slot) {
    return slots.remove(slot);
  }

}
