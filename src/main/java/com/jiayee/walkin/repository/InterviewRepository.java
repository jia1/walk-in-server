package com.jiayee.walkin.repository;

import com.jiayee.walkin.model.Interview;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

  Optional<Interview> findById(Long interviewId);

  long countByCreatedBy(Long userId);

  Page<Interview> findByCreatedBy(Long userId, Pageable pageable);

  List<Interview> findByIdIn(List<Long> interviewIds);

}
