package com.jiayee.walkin.repository;

import com.jiayee.walkin.model.Slot;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlotRepository extends CrudRepository<Slot, Long>, JpaSpecificationExecutor<Slot> {

  Optional<Slot> findById(Long slotId);

  List<Slot> findByIdIn(List<Long> slotIds);

}
