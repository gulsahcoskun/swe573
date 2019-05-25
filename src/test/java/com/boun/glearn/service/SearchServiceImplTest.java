package com.boun.glearn.service;

import com.boun.glearn.TestUtil;
import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.MaterialSummary;
import com.boun.glearn.model.UserProgress;
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
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SearchServiceImplTest {

    @Mock
    private UserProgressRepository userProgressRepository;

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private final SearchService searchService = new SearchServiceImpl(materialRepository,userProgressRepository);



    @Test
    public void getAllMaterialSummaries() {
        //prepare
        final List<Material> materials = Arrays.asList(TestUtil.createTestMaterial());
        when(materialRepository.findAll()).thenReturn(materials);

        //test
        List<MaterialSummary> materialSummaryList = searchService.getAllMaterialSummaries("username");

        //validate
        assertNotNull(materialSummaryList);
    }

    @Test
    public void getContents() {
        //prepare
        final List<Content> contents = Arrays.asList(TestUtil.createTestContent());
        when(materialRepository.getContentsByMaterialId(1L)).thenReturn(contents);

        //test
        List<Content> foundContents = searchService.getContents(1L);

        //validate
        assertEquals(contents.get(0).getTitle(), foundContents.get(0).getTitle());
    }

    @Test
    public void searchMaterials() {
        //prepare
        final List<Material> materials = Arrays.asList(TestUtil.createTestMaterial());
        when(materialRepository.findAll()).thenReturn(materials);

        //test
        List<MaterialSummary> foundMaterials = searchService.searchMaterials("title");

        //validate
        assertNotNull(foundMaterials);
    }

    @Test
    public void getMaterialDetail() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        Material foundMaterial = searchService.getMaterialDetail(1L);

        //validate
        assertEquals(material.getTitle(),foundMaterial.getTitle());
    }

    @Test
    public void getCreatedMaterials() {
        //prepare
        final List<Material> materials = Arrays.asList(TestUtil.createTestMaterial());
        when(materialRepository.getMaterialByCreatedBy("username")).thenReturn(materials);

        //test
        List<Material> foundMaterials = searchService.getCreatedMaterials("username");

        //validate
        assertEquals(materials.get(0).getTitle(), foundMaterials.get(0).getTitle());
    }

    @Test
    public void getInProgressMaterial() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        final List<UserProgress> userProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        final List<Content> contentList = Arrays.asList(TestUtil.createTestContent());
        when(userProgressRepository.getUserProgressByUsername("username")).thenReturn(userProgressList);
        when(materialRepository.getContentsByMaterialId(1L)).thenReturn(contentList);
        //when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        List<MaterialSummary> foundMaterials = searchService.getInProgressMaterial("username");

        //validate
        assertNotNull(foundMaterials);
    }

    @Test
    public void getCompletedMaterial() {
        //prepare
        final Material material = TestUtil.createTestMaterial();
        final List<UserProgress> userProgressList = Arrays.asList(TestUtil.createTestUserProgress());
        final List<Content> contentList = Arrays.asList(TestUtil.createTestContent());
        when(userProgressRepository.getUserProgressByUsername("username")).thenReturn(userProgressList);
        when(materialRepository.getContentsByMaterialId(1L)).thenReturn(contentList);
        when(materialRepository.findById(1L)).thenReturn(Optional.of(material));

        //test
        List<MaterialSummary> foundMaterials = searchService.getCompletedMaterial("username");

        //validate
        assertNotNull(foundMaterials);
    }


}