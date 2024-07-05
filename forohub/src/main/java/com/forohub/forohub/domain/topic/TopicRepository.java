package com.forohub.forohub.domain.topic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicRepository extends JpaRepository<Topic,Long> {

    Page<Topic> findByStatusTrue(Pageable pageable);


    Optional<Topic> findByIdAndStatusTrue(Long aLong);
}
