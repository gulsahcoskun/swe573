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
    public ServiceResponse addProgress(String username, Long materialId, Long contentId, String options) {
        List<UserProgress> userMaterialProgress = userProgressRepository.getAllByUsernameAndMaterialId(username, materialId);
        Content content = contentRepository.findById(contentId).get();

        UserProgress progress = new UserProgress();
        boolean isPassed = checkAnswers(options, content);

        if (userMaterialProgress == null) {
            progress.setUsername(username);
            progress.setMaterialId(materialId);
            progress.setContentId(contentId);
            progress.setDateStarted(new Date());
            progress.setNumberOfTry(1L);
            progress.setCompleted(isPassed);
            userProgressRepository.save(progress);
        } else {
            Optional<UserProgress> userContentProgress = userMaterialProgress.stream().filter(p -> p.getContentId() == contentId).findAny();
            if (userContentProgress.isPresent()) {
                UserProgress existingProgress = userContentProgress.get();
                existingProgress.setDateUpdated(new Date());
                existingProgress.setNumberOfTry(existingProgress.getNumberOfTry() + 1);
                existingProgress.setCompleted(isPassed);
                userProgressRepository.save(existingProgress);
            } else {
                progress.setUsername(username);
                progress.setMaterialId(materialId);
                progress.setContentId(contentId);
                progress.setDateStarted(new Date());
                progress.setNumberOfTry(1L);
                progress.setCompleted(isPassed);
            }
        }

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(isPassed);
        return serviceResponse;
    }

    @Override
    public ServiceResponse isContentCompleted(String username, Long materialId, Long contentId) {
        boolean isCompleted = false;
        Set<Content> contents = materialRepository.findById(materialId).get().getContents();
        List<UserProgress> userMaterialProgress = userProgressRepository.getAllByUsernameAndMaterialId(username, materialId);
        Content content = contentRepository.findById(contentId).get();

        Optional<UserProgress> contentProgress = userMaterialProgress.stream().filter(m -> m.getContentId() == contentId).findFirst();

        if (contentProgress.isPresent()) {
            if (content.getOrder() != contents.size()) {
                isCompleted = contentProgress.get().isCompleted();
            } else {
                for (Content dependentContent : contents) {
                    isCompleted = userMaterialProgress.stream()
                            .filter(p -> p.getContentId() == dependentContent.getId())
                            .filter(c -> !c.isCompleted()).count() > 0 ? false : true;
                }
            }
        }

        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setSuccess(isCompleted);
        return serviceResponse;
    }

    private boolean checkAnswers(String options, Content content) {
        String[] selectedOptions = options.split(",");
        int count = 0;
        boolean allTrue = false;
        for (Question question : content.getQuestions()) {
            for (Option option : question.getOptions()) {
                if (option.getAnswer() && option.getOrder() == Long.valueOf(selectedOptions[count])) {
                    allTrue = true;
                } else {
                    return false;
                }
            }
            count++;
        }
        return allTrue;
    }

}
