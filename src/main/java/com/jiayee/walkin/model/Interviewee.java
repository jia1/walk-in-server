package com.jiayee.walkin.model;

import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Builder
@Data
@Entity
@Table(name = "interviewees", uniqueConstraints = {
})
public class Interviewee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "user")
  @Fetch(FetchMode.SELECT)
  private User user;

  @ManyToMany(mappedBy = "slot")
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
