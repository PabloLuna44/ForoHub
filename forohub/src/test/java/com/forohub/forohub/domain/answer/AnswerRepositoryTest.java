package com.forohub.forohub.domain.answer;

import com.forohub.forohub.domain.topic.Topic;
import com.forohub.forohub.domain.topic.TopicRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AnswerRepositoryTest {

    @Autowired
    private AnswerRepository answerRepository;

     @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return all answers with status true")
    void findByStatusTrue() {

        // Given
        Pageable pageable = PageRequest.of(0, 10);


        Page<Answer> result = answerRepository.findByStatusTrue(pageable);


        // Then
        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(7);
        assertThat(result.getContent().get(0).getComment()).isEqualTo("Update Answer");

    }

    @Test
    @DisplayName("It should return the answer with that id and  status true")
    void findByIdAndStatusTrue() {

        Long id=4L;
        Optional<Answer> answer=answerRepository.findByIdAndStatusTrue(id);

        assertThat(answer).isNotEmpty();

    }

    @Test
    @DisplayName("It should return all answers by topic")
    void findByTopicAndStatusTrue() {

        Long id=10L;

        Optional<Topic> topic=topicRepository.findByIdAndStatusTrue(id);

        Pageable pageable = PageRequest.of(0, 10);

        Page<Answer> result = answerRepository.findByTopicAndStatusTrue(topic.get(),pageable);


        assertThat(result).isNotNull();
        assertThat(result.getTotalElements()).isEqualTo(4);
        assertThat(result.getContent().get(0).getComment()).isEqualTo("Update Answer");

    }
}