package com.forohub.forohub.domain.answer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnswerRepository extends JpaRepository<Answer,Long> {


    Page<Answer> findByStatusTrue(Pageable pageable);


    Optional<Answer> findByIdAndStatusTrue(Long aLong);
}
