package com.boun.glearn.service;

import com.boun.glearn.model.ServiceResponse;
import com.boun.glearn.model.UserProgressControl;


public interface LearnService {

    ServiceResponse addProgress(UserProgressControl userProgressControl);
    ServiceResponse isContentCompleted(UserProgressControl userProgressControl);

}
