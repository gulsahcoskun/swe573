package com.boun.glearn.service;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.StudentTrackData;
import com.boun.glearn.model.TeacherTrackData;
import com.boun.glearn.model.UserProgress;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.constraints.AssertTrue;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrackServiceImplTest {

    @Mock
    private UserProgressRepository userProgressRepository;

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    final TrackService trackService = new TrackServiceImpl(userProgressRepository,materialRepository);

    @Test
    public void getStudentTrackData() {
        //prepare
        List<UserProgress> userProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        List<UserProgress> allUserMaterialProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        Material material = TestUtil.createTestMaterial();
        when(userProgressRepository.getUserProgressByUsername("username")).thenReturn(userProgressList);
        when(userProgressRepository.getAllByUsernameAndMaterialId("username",1L)).thenReturn(allUserMaterialProgressList);
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        List<StudentTrackData> studentTrackDataList = trackService.getStudentTrackData("username");

        //validate
        assertTrue(!studentTrackDataList.isEmpty());
    }

    @Test
    public void getTeacherTrackData() {
        //prepare
        List<UserProgress> allMaterialProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        List<Material> materials = Arrays.asList(TestUtil.createTestMaterial());
        when(userProgressRepository.getAllByMaterialId(1L)).thenReturn(allMaterialProgressList);
        when(materialRepository.getMaterialByCreatedBy("username")).thenReturn(materials);

        //test
        List<TeacherTrackData> teacherTrackDataList = trackService.getTeacherTrackData("username");

        //validate
        assertTrue(!teacherTrackDataList.isEmpty());
    }
}