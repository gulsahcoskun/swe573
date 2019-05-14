package com.boun.glearn.service;


import com.boun.glearn.model.*;
import com.boun.glearn.repository.ContentRepository;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
public class LearnServiceImpl implements LearnService {

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private ContentRepository contentRepository;


    @Override
    public ServiceResponse addProgress(UserProgressControl userProgressControl) {
        List<UserProgress> userMaterialProgress = userProgressRepository
                .getAllByUsernameAndMaterialId(userProgressControl.getUsername(), userProgressControl.getMaterialId());
        Content content = contentRepository.findById(userProgressControl.getContentId()).get();

        UserProgress progress = new UserProgress();

        boolean isPassed = false;
        if(content.getQuestions() == null || content.getQuestions().isEmpty()){
            isPassed = true;
        } else {
            isPassed = checkAnswers(userProgressControl.getAnswerList(), content);
        }

        if (userMaterialProgress == null) {
            progress.setUsername(userProgressControl.getUsername());
            progress.setMaterialId(userProgressControl.getMaterialId());
            progress.setContentId(userProgressControl.getMaterialId());
            progress.setDateStarted(new Date());
            progress.setNumberOfTry(1L);
            progress.setCompleted(isPassed);
            userProgressRepository.save(progress);
        } else {
            Optional<UserProgress> userContentProgress = userMaterialProgress.stream()
                    .filter(p -> p.getContentId().equals(userProgressControl.getContentId())).findAny();
            if (userContentProgress.isPresent()) {
                UserProgress existingProgress = userContentProgress.get();
                existingProgress.setDateUpdated(new Date());
                existingProgress.setNumberOfTry(existingProgress.getNumberOfTry() + 1);
                existingProgress.setCompleted(isPassed);
                userProgressRepository.save(existingProgress);
            } else {
                progress.setUsername(userProgressControl.getUsername());
                progress.setMaterialId(userProgressControl.getMaterialId());
                progress.setContentId(userProgressControl.getContentId());
                progress.setDateStarted(new Date());
                progress.setNumberOfTry(1L);
                progress.setCompleted(isPassed);
                userProgressRepository.save(progress);
            }
        }

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(isPassed);
        return serviceResponse;
    }

    @Override
    public ServiceResponse isContentCompleted(UserProgressControl userProgressControl) {
        boolean isCompleted = false;
        Set<Content> contents = materialRepository.findById(userProgressControl.getMaterialId()).get().getContents();
        List<UserProgress> userMaterialProgress = userProgressRepository
                .getAllByUsernameAndMaterialId(userProgressControl.getUsername(), userProgressControl.getMaterialId());
        Content content = contentRepository.findById(userProgressControl.getContentId()).get();

        Optional<UserProgress> contentProgress = userMaterialProgress.stream()
                .filter(m -> m.getContentId().equals(userProgressControl.getContentId())).findFirst();
        ServiceResponse serviceResponse = new ServiceResponse();

        if (contentProgress.isPresent()) {
            if (content.getOrder() != contents.size()) {
                isCompleted = contentProgress.get().isCompleted();
            } else {
                for (Content dependentContent : contents) {
                    isCompleted = userMaterialProgress.stream()
                            .filter(p -> p.getContentId() == dependentContent.getId())
                            .filter(c -> !c.isCompleted()).count() > 0 ? false : true;
                    if(isCompleted){
                        serviceResponse.setMessage("You finished the material ! ");
                    }
                }
            }
        }


        serviceResponse.setSuccess(isCompleted);
        return serviceResponse;
    }

    private boolean checkAnswers(List<Answer> answerList, Content content) {
        boolean allTrue = false;

        if(answerList == null){
            return false;
        } else if (content.getQuestions().size() != answerList.size()) {
            return false;
        } else {
            for (Question question : content.getQuestions()) {
                Option correctOption =  question.getOptions().stream().filter(o -> o.getIsAnswer() == true).findFirst().get();
                for(Answer answer : answerList){
                    if(answer.getQuestionId().equals(question.getId())){
                        if(answer.getOptionId().equals(correctOption.getId())){
                            allTrue = true;
                        } else {
                            return false;
                        }
                    }
                }
            }
        }

        return allTrue;
    }

}
