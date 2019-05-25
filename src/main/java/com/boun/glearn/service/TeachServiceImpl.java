package com.boun.glearn.service;

import com.boun.glearn.model.*;
import com.boun.glearn.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.wikidata.wdtk.datamodel.interfaces.PropertyIdValue;
import org.wikidata.wdtk.wikibaseapi.WbSearchEntitiesResult;
import org.wikidata.wdtk.wikibaseapi.WikibaseDataFetcher;
import org.wikidata.wdtk.wikibaseapi.apierrors.MediaWikiApiErrorException;

import java.util.*;

@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class TeachServiceImpl implements TeachService {

    private static final Logger logger = LoggerFactory.getLogger(TeachServiceImpl.class);
    private static final String MATERIAL_EXISTS = "Material already exists.";
    private static final String MATERIAL_NOT_EXIST = "Material does not exist.";
    private static final String CONTENT_NOT_EXIST = "Content does not exist.";
    private static final String QUESTION_NOT_EXIST = "Question does not exist.";
    private static final String SUCCESS_MESSAGE = "Successfully completed.";
    private static final String OPTION_EMPTY = "Options cannot be empty.";
    private static final String ONE_ANSWER = "Only one true answer should be entered";


    private MaterialRepository materialRepository;

    private ContentRepository contentRepository;

    private KeywordRepository keywordRepository;

    private QuestionRepository questionRepository;

    private OptionRepository optionRepository;

    public TeachServiceImpl(MaterialRepository materialRepository, ContentRepository contentRepository,
                            KeywordRepository keywordRepository, QuestionRepository questionRepository,
                            OptionRepository optionRepository){
        this.materialRepository = materialRepository;
        this.contentRepository = contentRepository;
        this.keywordRepository = keywordRepository;
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }


    @Override
    public List<Keyword> searchWiki(String keyword) {
        List<Keyword> keywordList = new ArrayList<>();
        final WikibaseDataFetcher wbdf = WikibaseDataFetcher.getWikidataDataFetcher();
        wbdf.getFilter().setSiteLinkFilter(Collections.singleton("enWiki"));
        wbdf.getFilter().setLanguageFilter(Collections.singleton("en"));
        wbdf.getFilter().setPropertyFilter(Collections.<PropertyIdValue>emptySet());
        final List<WbSearchEntitiesResult> entitiesResults;

        try {
            entitiesResults = wbdf.searchEntities(keyword, 20L);

            for (WbSearchEntitiesResult result : entitiesResults) {
                keywordList.add(new Keyword(result.getTitle(), result.getLabel(),
                        result.getUrl(), result.getDescription()));
            }
        } catch (MediaWikiApiErrorException e) {
            logger.error(e.getErrorMessage());
        }


        return keywordList;
    }

    @Override
    public List<Material> getAllMaterials() {
        return materialRepository.findAll();
    }

    @Override
    public Set<Content> getAllContents(Long materialId) {
        Optional<Material> foundMaterial = materialRepository.findById(materialId);
        if (foundMaterial.isPresent()) {
            return foundMaterial.get().getContents();
        }
        return null;
    }

    @Override
    public Set<Question> getAllQuestions(Long contentId) {
        Optional<Content> foundContent = contentRepository.findById(contentId);
        if (foundContent.isPresent()) {
            return foundContent.get().getQuestions();
        }
        return null;
    }

    @Override
    public Set<Keyword> getAllKeywords(Long contentId) {
        Optional<Content> foundContent = contentRepository.findById(contentId);
        if (foundContent.isPresent()) {
            return foundContent.get().getKeywords();
        }
        return null;
    }


    @Override
    public ServiceResponse addMaterial(Material material) {
        ServiceResponse serviceResponse = new ServiceResponse();
        if (material.getId() != null && materialRepository.findById(material.getId()).isPresent()) {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(MATERIAL_EXISTS);
        } else {
            material.setDateCreated(new Date());
            materialRepository.save(material);
            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse updateMaterial(Material material, Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Material> foundMaterial = materialRepository.findById(material.getId());
        if (foundMaterial.isPresent()) {
            Material newMaterial = foundMaterial.get();
            newMaterial.setTitle(material.getTitle());
            newMaterial.setImage(material.getImage());
            newMaterial.setDescription(material.getDescription());
            newMaterial.setDateUpdated(new Date());
            materialRepository.save(newMaterial);

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(MATERIAL_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse deleteMaterial(Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Material> material = materialRepository.findById(id);
        if (material.isPresent()) {
            Material foundMaterial = material.get();

            for (Content content : foundMaterial.getContents()) {
                Set<Question> questions = content.getQuestions();
                Set<Keyword> keywords = content.getKeywords();

                questions.stream().forEach(q -> questionRepository.deleteById(q.getId()));
                keywords.stream().forEach(k -> keywordRepository.deleteById(k.getId()));
                contentRepository.deleteById(content.getId());
            }

            materialRepository.deleteById(id);
            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);

        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(MATERIAL_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse addContent(Content content, Long materialId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Material> material = materialRepository.findById(materialId);
        if (material.isPresent()) {
            long count = material.get().getContents().size() + 1L;

            Content newContent = new Content();
            newContent.setTitle(content.getTitle());
            newContent.setExplanation(content.getExplanation());
            newContent.setImage(content.getImage());
            newContent.setStatus(content.getStatus());
            newContent.setOrder(count);
            newContent.setStatus(content.getStatus());
            newContent.setMaterial(material.get());

            contentRepository.save(newContent);


            for (Keyword keyword : content.getKeywords()) {
                if (keywordRepository.findByTitle(keyword.getTitle()) == null) {
                    Keyword newKeyword = new Keyword();
                    newKeyword.setLabel(keyword.getLabel());
                    newKeyword.setContent(newContent);
                    newKeyword.setDescription(keyword.getDescription());
                    newKeyword.setTitle(keyword.getTitle());
                    newKeyword.setUrl(keyword.getUrl());

                    keywordRepository.save(newKeyword);
                }

            }

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(MATERIAL_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse updateContent(Content content, Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Content> foundContent = contentRepository.findById(id);
        if (foundContent.isPresent()) {
            Content newContent = foundContent.get();
            newContent.setTitle(content.getTitle());
            newContent.setImage(content.getImage());
            newContent.setExplanation(content.getExplanation());
            contentRepository.save(newContent);

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(CONTENT_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse deleteContent(Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Content> foundContent = contentRepository.findById(id);
        if (foundContent.isPresent()) {
            Content content = foundContent.get();
            Set<Question> questions = content.getQuestions();
            Set<Keyword> keywords = content.getKeywords();

            questions.stream().forEach(q -> questionRepository.deleteById(q.getId()));
            keywords.stream().forEach(k -> keywordRepository.deleteById(k.getId()));
            contentRepository.deleteById(content.getId());

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(CONTENT_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse addQuestion(Question question, Long contentId) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Content> content = contentRepository.findById(contentId);
        if (content.isPresent()) {

            if (question.getOptions().isEmpty()) {
                serviceResponse.setIsSuccess(false);
                serviceResponse.setMessage(OPTION_EMPTY);
                return serviceResponse;
            } else if (question.getOptions().stream().filter(o -> o.getIsAnswer() == true).count() > 1) {
                serviceResponse.setIsSuccess(false);
                serviceResponse.setMessage(ONE_ANSWER);
                return serviceResponse;
            }

            long count = content.get().getQuestions().size() + 1L;

            Question newQuestion = new Question();
            newQuestion.setContent(content.get());
            newQuestion.setStatus(question.getStatus());
            newQuestion.setOrder(count);
            newQuestion.setStatus(1L);
            newQuestion.setQuestionText(question.getQuestionText());

            questionRepository.save(newQuestion);

            long optionOrder = 1;

            for (Option option : question.getOptions()) {
                Option newOption = new Option();
                newOption.setOptionText(option.getOptionText());
                newOption.setIsAnswer(option.getIsAnswer());
                newOption.setOrder(optionOrder);
                newOption.setQuestion(newQuestion);
                newOption.setStatus(1L);

                optionRepository.save(newOption);
                optionOrder++;
            }

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(CONTENT_NOT_EXIST);
        }

        return serviceResponse;
    }

    @Override
    public ServiceResponse updateQuestion(Question question, Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Question> foundQuestion = questionRepository.findById(id);
        if (foundQuestion.isPresent()) {
            Question newQuestion = foundQuestion.get();
            newQuestion.setQuestionText(question.getQuestionText());
            questionRepository.save(newQuestion);

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(QUESTION_NOT_EXIST);
        }
        return serviceResponse;
    }

    @Override
    public ServiceResponse deleteQuestion(Long id) {
        ServiceResponse serviceResponse = new ServiceResponse();
        Optional<Question> foundQuestion = questionRepository.findById(id);
        if (foundQuestion.isPresent()) {
            Question question = foundQuestion.get();
            Set<Option> options = question.getOptions();

            options.stream().forEach(q -> optionRepository.deleteById(q.getId()));

            questionRepository.deleteById(id);

            serviceResponse.setIsSuccess(true);
            serviceResponse.setMessage(SUCCESS_MESSAGE);
        } else {
            serviceResponse.setIsSuccess(false);
            serviceResponse.setMessage(QUESTION_NOT_EXIST);
        }
        return serviceResponse;
    }

}
