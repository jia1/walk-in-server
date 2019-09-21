package com.jiayee.walkin.model;

import com.jiayee.walkin.model.audit.UserDateAudit;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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

  @NonNull
  @NotBlank
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
  private List<Slot> slots = new ArrayList<Slot>() {
    public boolean add(Slot slot) {
      int index = Collections.binarySearch(this, slot);
      if (index >= 0) {
        super.add(index, slot);
      } else {
        super.add(-(index + 1), slot);
      }
      return true;
    }
  };

  public void addSlot(Slot slot) {
    slots.add(slot);
  }

  public void removeSlot(Slot slot) {
    slots.remove(slot);
  }

}
