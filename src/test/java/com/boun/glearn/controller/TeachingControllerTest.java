package com.boun.glearn.controller;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.Question;
import com.boun.glearn.service.TeachService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TeachingControllerTest {

    @Mock
    private TeachService teachService;

    @InjectMocks
    private final TeachingController teachingController = new TeachingController(teachService);


    @Test
    public void searchWiki() {
        //test
        teachingController.searchWiki("keyword");
        //verify
        verify(teachService,times(1)).searchWiki("keyword");
    }

    @Test
    public void createMaterial() {
        //prepare
        Material material = TestUtil.createTestMaterial();
        //test
        teachingController.createMaterial(material);
        //verify
        verify(teachService,times(1)).addMaterial(material);
    }

    @Test
    public void updateMaterial() {
        //prepare
        Material material = TestUtil.createTestMaterial();
        //test
        teachingController.updateMaterial(material,1L);
        //verify
        verify(teachService,times(1)).updateMaterial(material,1L);
    }

    @Test
    public void deleteMaterial() {
        //test
        teachingController.deleteMaterial(1L);
        //verify
        verify(teachService,times(1)).deleteMaterial(1L);
    }

    @Test
    public void createContent() {
        //prepare
        Content content = TestUtil.createTestContent();
        //test
        teachingController.createContent(content,1L);
        //verify
        verify(teachService,times(1)).addContent(content,1L);
    }

    @Test
    public void updateContent() {
        //prepare
        Content content = TestUtil.createTestContent();
        //test
        teachingController.updateContent(content,1L);
        //verify
        verify(teachService,times(1)).updateContent(content,1L);
    }

    @Test
    public void deleteContent() {
        //test
        teachingController.deleteContent(1L);
        //verify
        verify(teachService,times(1)).deleteContent(1L);
    }

    @Test
    public void createQuestion() {
        //prepare
        Question question = TestUtil.createTestQuestion();
        //test
        teachingController.createQuestion(question,1L);
        //verify
        verify(teachService,times(1)).addQuestion(question,1L);
    }

    @Test
    public void updateQuestion() {
        //prepare
        Question question = TestUtil.createTestQuestion();
        //test
        teachingController.updateQuestion(question,1L);
        //verify
        verify(teachService,times(1)).updateQuestion(question,1L);
    }

    @Test
    public void deleteQuestion() {
        //test
        teachingController.deleteQuestion(1L);
        //verify
        verify(teachService,times(1)).deleteQuestion(1L);
    }

    @Test
    public void getAllQuestions() {
        //test
        teachingController.getAllQuestions(1L);
        //verify
        verify(teachService,times(1)).getAllQuestions(1L);
    }
}