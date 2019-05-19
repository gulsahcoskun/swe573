package com.boun.glearn.service;

import com.boun.glearn.model.ServiceResponse;
import com.boun.glearn.model.UserMaterialStatus;
import com.boun.glearn.model.UserProgressControl;



public interface LearnService {

    ServiceResponse addProgress(UserProgressControl userProgressControl);
    UserMaterialStatus findUserMaterialStatus(String username, Long materialId);

}
