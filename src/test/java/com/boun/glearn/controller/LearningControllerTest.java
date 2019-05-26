package com.boun.glearn.controller;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.UserProgress;
import com.boun.glearn.model.UserProgressControl;
import com.boun.glearn.service.LearnService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class LearningControllerTest {

    @Mock
    private LearnService learnService;

    @InjectMocks
    private final LearningController learningController = new LearningController(learnService);

    @Test
    public void addProgress() {
        //prepare
        UserProgressControl userProgressControl = TestUtil.createTestUserProgressControl();
        //test
        learningController.addProgress(userProgressControl);
        //validate
        verify(learnService, times(1)).addProgress(userProgressControl);
    }

    @Test
    public void getUserStatus() {
        //test
        learningController.getUserStatus("username",1L);

        //validate
        verify(learnService, times(1)).findUserMaterialStatus("username",1L);
    }
}