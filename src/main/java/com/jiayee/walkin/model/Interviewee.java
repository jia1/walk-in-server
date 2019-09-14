package com.jiayee.walkin.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
