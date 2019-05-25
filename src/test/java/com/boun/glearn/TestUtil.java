package com.boun.glearn;

import com.boun.glearn.model.*;

import java.util.*;


public class TestUtil {

    public static User createTestUser(){
        final User user = new User();
        user.setName("name");
        user.setUsername("username");
        user.setEmail("user@gmail.com");
        user.setId(1L);
        user.setPassword("password");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(new Role(RoleName.ROLE_USER));
        user.setRoles(roleSet);
        return user;
    }


    public static Material createTestMaterial(){
        Material material = new Material();
        material.setTitle("material");
        material.setImage("image");
        material.setDateCreated(new Date());
        material.setDescription("description");
        material.setStatus(1L);
        material.setCreatedBy("username");
        material.setId(1L);
        Set<Content> contents = new HashSet<>();
        contents.add(createTestContent());
        material.setContents(contents);
        return material;
    }

    public static MaterialSummary createTestMaterialSummary(){
        MaterialSummary material = new MaterialSummary();
        material.setMaterialName("material");
        material.setCreatedBy("username");
        material.setDescription("description");
        material.setDetail("detail");
        material.setImage("image");
        material.setMaterialId(1L);
        material.setKeywordList(Arrays.asList("keyword"));
        return material;
    }

    public static Content createTestContent(){
        Content content = new Content();
        content.setId(1L);
        content.setTitle("content");
        content.setExplanation("explanation");
        content.setStatus(1L);
        content.setOrder(1L);
        content.setImage("image");
        //content.setMaterial(createTestMaterial());
        Set<Question> questions = new HashSet<>();
        questions.add(createTestQuestion());
        Set<Keyword> keywords = new HashSet<>();
        keywords.add(createTestKeyword());
        content.setKeywords(keywords);
        content.setQuestions(questions);
        return content;
    }

    public static Question createTestQuestion(){
        Question question = new Question();
        question.setQuestionText("question");
        question.setStatus(1L);
        question.setOrder(1L);
        question.setId(1L);
        //question.setContent(createTestContent());
        Set<Option> options = new HashSet<>();
        options.add(createTestOption());
        question.setOptions(options);
        return question;
    }


    public static Option createTestOption(){
        Option option = new Option();
        option.setStatus(1L);
        //option.setQuestion(createTestQuestion());
        option.setOrder(1L);
        option.setOptionText("option");
        option.setIsAnswer(true);
        option.setId(1L);
        return option;
    }

    public static Keyword createTestKeyword(){
        Keyword keyword = new Keyword();
        keyword.setTitle("keyword");
        keyword.setUrl("url");
        keyword.setDescription("description");
        keyword.setLabel("label");
        keyword.setId(1L);
        //keyword.setContent(createTestContent());
        return keyword;
    }

    public static ServiceResponse createTestServiceResponse(){
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setIsSuccess(true);
        serviceResponse.setMessage("Successfully completed.");
        return serviceResponse;
    }

    public static StudentTrackData createTestStudentTrackData(){
        StudentTrackData studentTrackData = new StudentTrackData();
        studentTrackData.setMaterialName("material");
        studentTrackData.setCompletionRatio(100);
        List<DetailedTrackData> detailedTrackDataList = new ArrayList<>();
        detailedTrackDataList.add(createTestDetailedTrackData());
        studentTrackData.setDetailedTrackDataList(detailedTrackDataList);
        return studentTrackData;
    }

    public static TeacherTrackData createTestTeacherTrackData(){
        TeacherTrackData teacherTrackData = new TeacherTrackData();
        teacherTrackData.setMaterialName("material");
        teacherTrackData.setNumberOfStudents(1);
        List<DetailedTrackData> detailedTrackDataList = new ArrayList<>();
        detailedTrackDataList.add(createTestDetailedTrackData());
        teacherTrackData.setDetailedTrackDataList(detailedTrackDataList);
        return teacherTrackData;
    }

    public static UserContentStatus createTestUserContentStatus(){
        UserContentStatus userContentStatus = new UserContentStatus();
        userContentStatus.setIsCompleted(true);
        userContentStatus.setContentId(1L);
        return userContentStatus;
    }


    public static UserMaterialStatus createTestUserMaterialStatus(){
        UserMaterialStatus userMaterialStatus = new UserMaterialStatus();
        userMaterialStatus.setIsCompleted(true);
        userMaterialStatus.setMaterialId(1L);
        userMaterialStatus.setUserContentStatusList(Arrays.asList(createTestUserContentStatus()));
        return userMaterialStatus;
    }

    public static UserProgress createTestUserProgress(){
        UserProgress userProgress = new UserProgress();
        userProgress.setId(1L);
        userProgress.setDateStarted(new Date());
        userProgress.setMaterialId(1L);
        userProgress.setContentId(1L);
        userProgress.setCompleted(true);
        userProgress.setNumberOfTry(1L);
        userProgress.setUsername("username");
        return userProgress;
    }

    public static UserProgressControl createTestUserProgressControl(){
        UserProgressControl userProgressControl = new UserProgressControl();
        userProgressControl.setUsername("username");
        userProgressControl.setContentId(1L);
        userProgressControl.setMaterialId(1L);
        userProgressControl.setAnswerList(Arrays.asList(createTestAnswer()));
        return userProgressControl;
    }

    public static DetailedTrackData createTestDetailedTrackData(){
        DetailedTrackData detailedTrackData = new DetailedTrackData();
        detailedTrackData.setContentName("content");
        detailedTrackData.setNumberOfTry(1);
        return detailedTrackData;
    }

    public static Answer createTestAnswer(){
        Answer answer = new Answer();
        answer.setOptionId(1L);
        answer.setQuestionId(1L);
        return answer;
    }

}
