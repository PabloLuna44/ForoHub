package com.forohub.forohub.domain.answer;

import com.forohub.forohub.domain.topic.*;
import com.forohub.forohub.domain.user.User;
import com.forohub.forohub.domain.user.UserRepository;
import com.forohub.forohub.infra.errors.IntegrityValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AnswerService {


    @Autowired
    AnswerRepository answerRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TopicRepository topicRepository;

    public AnswerResponseDTO save(AnswerNewDTO answerNew){

        Optional<Topic> topic = topicRepository.findByIdAndStatusTrue(answerNew.topicId());
        if(topic.isEmpty()){
            throw new IntegrityValidation("Topic Not Found");
        }
        Optional<User> user =userRepository.findById(answerNew.userId());

        if(user.isEmpty()){
            throw new IntegrityValidation("User Not Found");
        }

        Answer answer=new Answer(answerNew.comment(),user.get(),topic.get());
        AnswerResponseDTO answerResponseDTO=new AnswerResponseDTO(answerRepository.save(answer));

        return answerResponseDTO;
    }

    public Page<AnswerResponseDTO> findAll(Pageable pageable){

        return answerRepository.findByStatusTrue(pageable).map(AnswerResponseDTO::new);

    }

    public Page<AnswerResponseDTO> findByTopic(Long id,Pageable pageable){

        Optional<Topic> topic=topicRepository.findById(id);

        if(topic.isEmpty()){
            throw new IntegrityValidation("Topic Not Found");
        }


        return answerRepository.findByTopicAndStatusTrue(topic.get(),pageable).map(AnswerResponseDTO::new);
    }

    public AnswerResponseDTO findById(Long id){
        Optional<Answer> answer= answerRepository.findByIdAndStatusTrue(id);

        if(answer.isEmpty()){
            throw new IntegrityValidation("Answer Not Found");
        }
        AnswerResponseDTO answerResponseDTO=new AnswerResponseDTO(answer.get());
        return answerResponseDTO;
    }

    public AnswerResponseDTO edit(AnswerUpdatedDTO answerUpdatedDTO){

        Answer answer=answerRepository.getReferenceById(answerUpdatedDTO.id());

        if(answer==null){
            throw new IntegrityValidation("Answer Not Found");
        }

        answer.update(answerUpdatedDTO);
        AnswerResponseDTO answerResponseDTO=new AnswerResponseDTO(answer);

        return answerResponseDTO;
    }

    //    Soft Delete
    public void delete(Long id){
        Answer answer=answerRepository.getReferenceById(id);
        if(answer==null){
            throw new IntegrityValidation("Answer Not Found");
        }

        answer.setStatus(false);

    }

//Borrado de la base de datos
//    public void delete(Long id){
//
//        Topic topic=topicRepository.getReferenceById(id);
//        topicRepository.delete(topic);
//
//    }
}
