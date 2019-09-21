package com.jiayee.walkin.repository;

import com.jiayee.walkin.model.Slot;
import java.time.Instant;
import org.springframework.data.jpa.domain.Specification;

public class SlotSpecification {

  public static Specification<Slot> hasStartOfLessThan(Instant start) {
    return (root, query, builder) -> builder.lessThan(root.get("start"), start);
  }

}
