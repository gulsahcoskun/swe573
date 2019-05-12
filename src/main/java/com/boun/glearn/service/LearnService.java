package com.boun.glearn.service;

import com.boun.glearn.model.MaterialSummary;
import com.boun.glearn.model.ServiceResponse;

import java.util.List;

public interface LearnService {

    ServiceResponse addProgress(String username, Long materialId, Long contentId, String options);
    ServiceResponse isContentCompleted(String username, Long materialId, Long contentId);

}
