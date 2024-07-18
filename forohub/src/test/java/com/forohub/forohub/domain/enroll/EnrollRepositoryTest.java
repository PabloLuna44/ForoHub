package com.forohub.forohub.domain.enroll;

import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EnrollRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EnrollRepository enrollRepository;

    @Test
    void findByUser() {
        Long id=10L;
        Pageable pageable = PageRequest.of(0, 10);
        Optional<User> user= userRepository.findById(id);

        Page<Enroll> result=enrollRepository.findByUser(pageable,user.get());

//        System.out.println(result.stream().toList());
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(3);
    }
}