package com.boun.glearn.controller;

import com.boun.glearn.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SearchingControllerTest {

    @Mock
    private SearchService searchService;

    @InjectMocks
    private final SearchingController searchingController = new SearchingController(searchService);

    @Test
    public void getMaterials() {
        //test
        searchingController.getMaterials("username");
        //verify
        verify(searchService, times(1)).getAllMaterialSummaries("username");
    }

    @Test
    public void getContents() {
        //test
        searchingController.getContents(1L);
        //verify
        verify(searchService,times(1)).getContents(1L);
    }

    @Test
    public void searchMaterials() {
        //test
        searchingController.searchMaterials("title");
        //verify
        verify(searchService,times(1)).searchMaterials("title");
    }

    @Test
    public void getCreatedMaterials() {
        //test
        searchingController.getCreatedMaterials("username");
        //verify
        verify(searchService,times(1)).getCreatedMaterials("username");
    }

    @Test
    public void getMaterialDetail() {
        //test
        searchingController.getMaterialDetail(1L);
        //verify
        verify(searchService,times(1)).getMaterialDetail(1L);
    }

    @Test
    public void getInProgressMaterials() {
        //test
        searchingController.getInProgressMaterials("username");
        //verify
        verify(searchService,times(1)).getInProgressMaterial("username");
    }

    @Test
    public void getCompletedMaterials() {
        //test
        searchingController.getCompletedMaterials("username");
        //verify
        verify(searchService,times(1)).getCompletedMaterial("username");
    }
}