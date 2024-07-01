package com.forohub.forohub.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository extends JpaRepository<Long,User> {

  UserDetails findByEmail(String email);
}
