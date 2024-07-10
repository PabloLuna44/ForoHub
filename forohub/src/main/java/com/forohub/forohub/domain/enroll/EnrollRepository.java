package com.forohub.forohub.domain.enroll;

import com.forohub.forohub.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollRepository extends JpaRepository<Enroll,Long> {


    Page<Enroll> findByUser(Pageable pageable, User user);

}
