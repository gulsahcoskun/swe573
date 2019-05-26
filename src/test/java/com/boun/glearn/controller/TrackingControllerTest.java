package com.boun.glearn.controller;

import com.boun.glearn.service.TrackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TrackingControllerTest {

    @Mock
    private TrackService trackService;

    @InjectMocks
    private final TrackingController trackingController = new TrackingController(trackService);

    @Test
    public void getStudentTrackData() {
        //test
        trackingController.getStudentTrackData("username");

        //verify
        verify(trackService, times(1)).getStudentTrackData("username");
    }

    @Test
    public void getTeacherTrackData() {
        //test
        trackingController.getTeacherTrackData("username");

        //verify
        verify(trackService, times(1)).getTeacherTrackData("username");
    }
}