package com.boun.glearn.service;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.*;
import com.boun.glearn.repository.ContentRepository;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LearnServiceImplTest {

    @Mock
    private UserProgressRepository userProgressRepository;

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ContentRepository contentRepository;

    @InjectMocks
    private final LearnService learnService = new LearnServiceImpl(userProgressRepository,materialRepository,contentRepository);

    @Test
    public void addProgress() {
        //prepare
        final UserProgressControl userProgressControl = TestUtil.createTestUserProgressControl();
        final List<UserProgress> userMaterialProgress = Arrays.asList(TestUtil.createTestUserProgress());
        final Content content = TestUtil.createTestContent();
        when(userProgressRepository.getAllByUsernameAndMaterialId("username",1L)).thenReturn(userMaterialProgress);
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final ServiceResponse serviceResponse = learnService.addProgress(userProgressControl);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void findUserMaterialStatus() {
        //prepare
        final List<Content> contents = Arrays.asList(TestUtil.createTestContent());
        final List<UserProgress> userProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        when(materialRepository.getContentsByMaterialId(1L)).thenReturn(contents);
        when(userProgressRepository.getUserProgressByUsername("username")).thenReturn(userProgressList);

        //test
        final UserMaterialStatus userMaterialStatus = learnService.findUserMaterialStatus("username",1L);

        //validate
        assertNotNull(userMaterialStatus);
    }
}