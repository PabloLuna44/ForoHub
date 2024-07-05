package com.forohub.forohub.domain.topic.Validations;

import com.forohub.forohub.domain.topic.Topic;
import com.forohub.forohub.domain.topic.TopicUpdateDTO;

public interface TopicValidator {
    void validate(TopicUpdateDTO topicUpdateDTO);
}
