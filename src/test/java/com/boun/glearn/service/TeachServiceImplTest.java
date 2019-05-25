package com.boun.glearn.service;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.*;
import com.boun.glearn.repository.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TeachServiceImplTest {

    @Mock
    private MaterialRepository materialRepository;

    @Mock
    private ContentRepository contentRepository;

    @Mock
    private KeywordRepository keywordRepository;

    @Mock
    private QuestionRepository questionRepository;

    @Mock
    private OptionRepository optionRepository;

    @InjectMocks
    private final TeachService teachService = new TeachServiceImpl(materialRepository,contentRepository,keywordRepository,
            questionRepository,optionRepository);



    @Test
    public void getAllMaterials() {
        //prepare
        List<Material> materialList = Arrays.asList(TestUtil.createTestMaterial());
        when(materialRepository.findAll()).thenReturn(materialList);

        //test
        final List<Material> foundMaterials = teachService.getAllMaterials();

        //validate
        assertEquals(foundMaterials.get(0).getTitle(),materialList.get(0).getTitle());
    }

    @Test
    public void getAllContents() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        final Set<Content> foundContents = teachService.getAllContents(1L);

        //validate
        assertTrue(!foundContents.isEmpty());
    }

    @Test
    public void getAllQuestions() {
        //prepare
        final Content content = TestUtil.createTestContent();
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final Set<Question> foundQuestions = teachService.getAllQuestions(1L);

        //validate
        assertTrue(!foundQuestions.isEmpty());
    }

    @Test
    public void getAllKeywords() {
        //prepare
        final Content content = TestUtil.createTestContent();
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final Set<Keyword> foundKeywords = teachService.getAllKeywords(1L);

        //validate
        assertTrue(!foundKeywords.isEmpty());
    }

    @Test
    public void addMaterial() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        when(materialRepository.save(material)).thenReturn(material);

        //test
        final ServiceResponse serviceResponse = teachService.addMaterial(material);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void updateMaterial() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(materialRepository.save(material)).thenReturn(material);

        //test
        final ServiceResponse serviceResponse = teachService.updateMaterial(material,1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void deleteMaterial() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        final ServiceResponse serviceResponse = teachService.deleteMaterial(1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void addContent() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        final Content content = TestUtil.createTestContent();
        //when(contentRepository.save(content)).thenReturn(content);
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        final ServiceResponse serviceResponse = teachService.addContent(content,1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void updateContent() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        final Content content = TestUtil.createTestContent();
        when(contentRepository.save(content)).thenReturn(content);
        //when(materialRepository.findById(1L)).thenReturn(Optional.of(material));
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final ServiceResponse serviceResponse = teachService.updateContent(content,1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void deleteContent() {
        //prepare
        final Content content = TestUtil.createTestContent();
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final ServiceResponse serviceResponse = teachService.deleteContent(1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void addQuestion() {
        //prepare
        final Question question = TestUtil.createTestQuestion();
        final Content content = TestUtil.createTestContent();
        //when(questionRepository.save(question)).thenReturn(question);
        when(contentRepository.findById(1L)).thenReturn(Optional.of(content));

        //test
        final ServiceResponse serviceResponse = teachService.addQuestion(question,1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void updateQuestion() {
        //prepare
        final Question question = TestUtil.createTestQuestion();
        when(questionRepository.save(question)).thenReturn(question);
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        //test
        final ServiceResponse serviceResponse = teachService.updateQuestion(question,1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }

    @Test
    public void deleteQuestion() {
        //prepare
        final Question question = TestUtil.createTestQuestion();
        when(questionRepository.findById(1L)).thenReturn(Optional.of(question));

        //test
        final ServiceResponse serviceResponse = teachService.deleteQuestion(1L);

        //validate
        assertTrue(serviceResponse.isSuccess());
    }
}