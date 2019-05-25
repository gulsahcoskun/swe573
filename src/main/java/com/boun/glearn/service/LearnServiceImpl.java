package com.boun.glearn.service;


import com.boun.glearn.model.*;
import com.boun.glearn.repository.ContentRepository;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


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

        Optional<Content> content = contentRepository.findById(userProgressControl.getContentId());
        boolean isPassed = false;

        if(content.isPresent()){
            Content foundContent = content.get();
            if (foundContent.getQuestions() == null || foundContent.getQuestions().isEmpty()) {
                isPassed = true;
            } else {
                isPassed = checkAnswers(userProgressControl.getAnswerList(), foundContent);
            }
        }

        UserProgress progress = new UserProgress();



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
        serviceResponse.setIsSuccess(isPassed);
        return serviceResponse;
    }


    private boolean checkAnswers(List<Answer> answerList, Content content) {
        boolean allTrue = false;

        if (answerList == null) {
            allTrue = false;
        } else if (content.getQuestions().size() != answerList.size()) {
            allTrue = false;
        } else {
            for (Question question : content.getQuestions()) {
                Optional<Option> correctOption = question.getOptions().stream().filter(o -> o.getIsAnswer() == true).findFirst();

                if(correctOption.isPresent()){
                    for (Answer answer : answerList) {
                        if (answer.getQuestionId().equals(question.getId())) {
                            if (answer.getOptionId().equals(correctOption.getId())) {
                                allTrue = true;
                            } else {
                                return false;
                            }
                        }
                    }
                }
            }
        }

        return allTrue;
    }


    @Override
    public UserMaterialStatus findUserMaterialStatus(String username, Long materialId) {
        List<Content> contents = materialRepository.getContentsByMaterialId(materialId);
        List<UserProgress> userProgressList = userProgressRepository.getUserProgressByUsername(username);

        UserMaterialStatus materialStatus = new UserMaterialStatus();
        materialStatus.setMaterialId(materialId);
        List<UserContentStatus> contentStatusList = new ArrayList<>();
        int successCount = 0;

        for (Content content : contents) {
            UserContentStatus contentStatus = new UserContentStatus();
            contentStatus.setContentId(content.getId());

            Boolean isContentCompleted = false;

           for(UserProgress userProgress: userProgressList){
               if(userProgress.getContentId().equals(content.getId())){
                   isContentCompleted = userProgress.isCompleted();
                   break;
               }
           }

            if (isContentCompleted) {
                successCount++;
            }

            contentStatus.setIsCompleted(isContentCompleted);
            contentStatusList.add(contentStatus);
        }

        materialStatus.setUserContentStatusList(contentStatusList);
        materialStatus.setIsCompleted(successCount == contents.size());
        return materialStatus;
    }

}
