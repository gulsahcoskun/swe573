package com.boun.glearn.service;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.MaterialSummary;
import com.boun.glearn.model.UserProgress;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.*;
import java.util.stream.Collectors;

@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class SearchServiceImpl implements SearchService {

    private MaterialRepository repository;

    private UserProgressRepository userProgressRepository;

    public SearchServiceImpl(MaterialRepository materialRepository, UserProgressRepository userProgressRepository){
        repository = materialRepository;
        this.userProgressRepository = userProgressRepository;
    }

    @Override
    public List<MaterialSummary> getAllMaterialSummaries(String username) {
        List<Material> foundMaterials = repository.findAll().stream().collect(Collectors.toList());
        if(username != null && !username.isEmpty()){
            foundMaterials = foundMaterials.stream()
                    .filter(m -> !m.getCreatedBy().equalsIgnoreCase(username))
                    .collect(Collectors.toList());
        }

        return materialToMaterialSummary(foundMaterials);
    }

    @Override
    public List<Content> getContents(Long materialId) {
        List<Content> contentList = repository.getContentsByMaterialId(materialId);
        return contentList.stream().sorted(Comparator.comparing(Content::getOrder)).collect(Collectors.toList());
    }

    @Override
    public List<MaterialSummary> searchMaterials(String keyword) {
        List<Material> all = repository.findAll();
        List<Material> foundByName = all.stream().filter(m -> m.getTitle().toUpperCase().contains(keyword.toUpperCase())).collect(Collectors.toList());
        List<Material> foundByContents = all.stream().filter(m -> m.getContents().stream().anyMatch(t -> t.getTitle().toUpperCase().contains(keyword.toUpperCase()))).collect(Collectors.toList());
        /*List<Material> foundByKeywords = all.stream().filter(m -> m.getContents().stream()
                .flatMap(k -> k.getKeywords().stream())
                .anyMatch(k -> k.getLabel().contains(keyword))).collect(Collectors.toList());*/

        Set<Material> materialList = new HashSet<>();
        materialList.addAll(foundByName);
        materialList.addAll(foundByContents);
        //materialList.addAll(foundByKeywords);

        return materialToMaterialSummary(new ArrayList(Arrays.asList(materialList.toArray())));
    }


    @Override
    public Material getMaterialDetail(Long materialId) {
        Optional<Material> found = repository.findById(materialId);
        if (found.isPresent()) {
            Material foundMaterial = found.get();
            foundMaterial.getContents().stream().sorted(Comparator.comparing(Content::getOrder));
            return foundMaterial;
        }
        return null;
    }

    @Override
    public List<Material> getCreatedMaterials(String createdBy) {
        return repository.getMaterialByCreatedBy(createdBy);
    }

    @Override
    public List<MaterialSummary> getInProgressMaterial(String username) {
        List<UserProgress> allProgress = userProgressRepository.getUserProgressByUsername(username);

        Set<Long> materialIds = allProgress.stream()
                .map(m -> m.getMaterialId()).collect(Collectors.toSet());

        List<Material> foundMaterials = new ArrayList<>();

        for(Long id: materialIds){
            List<Content> contents = repository.getContentsByMaterialId(id);
            List<UserProgress> materialProgress = allProgress.stream().filter(p -> p.getMaterialId().equals(id)).collect(Collectors.toList());
            int successCount = 0;

            for (Content content : contents) {
                Boolean isContentCompleted = false;

                for(UserProgress userProgress: materialProgress){
                    if(userProgress.getContentId().equals(content.getId())){
                        isContentCompleted = userProgress.isCompleted();
                        break;
                    }
                }

                if (isContentCompleted) {
                    successCount++;
                }
            }

            if( !contents.isEmpty() && (successCount != contents.size())){
                Optional<Material> foundMaterial = repository.findById(id);
                if(foundMaterial.isPresent()){
                    foundMaterials.add(foundMaterial.get());
                }
            }

        }

        return materialToMaterialSummary(foundMaterials);
    }



    @Override
    public List<MaterialSummary> getCompletedMaterial(String username) {
        List<UserProgress> allProgress = userProgressRepository.getUserProgressByUsername(username);

        Set<Long> materialIds = allProgress.stream()
                .map(m -> m.getMaterialId()).collect(Collectors.toSet());

        List<Material> foundMaterials = new ArrayList<>();

        for(Long id: materialIds){
            List<Content> contents = repository.getContentsByMaterialId(id);
            List<UserProgress> materialProgress = allProgress.stream().filter(p -> p.getMaterialId().equals(id)).collect(Collectors.toList());
            int successCount = 0;

            for (Content content : contents) {
                Boolean isContentCompleted = false;

                for(UserProgress userProgress: materialProgress){
                    if(userProgress.getContentId().equals(content.getId())){
                        isContentCompleted = userProgress.isCompleted();
                        break;
                    }
                }

                if (isContentCompleted) {
                    successCount++;
                }
            }

            if(!contents.isEmpty() && (successCount == contents.size())){
                Optional<Material> foundMaterial = repository.findById(id);
                if(foundMaterial.isPresent()){
                    foundMaterials.add(foundMaterial.get());
                }
            }

        }

        return materialToMaterialSummary(foundMaterials);
    }


    private List<MaterialSummary> materialToMaterialSummary(List<Material> materialList) {
        return materialList.stream().map(m ->
                new MaterialSummary(m.getId(),
                        m.getImage(),
                        m.getTitle(),
                        m.getContents().stream()
                                .flatMap(c -> c.getKeywords().stream())
                                .map(k -> k.getLabel())
                                .collect(Collectors.toList()),
                        m.getDescription(),
                        m.getCreatedBy(),
                        m.getContents().size() + " contents, "
                                + m.getContents().stream()
                                .mapToInt(c -> c.getQuestions().size()).sum() + " questions"))
                .collect(Collectors.toList());
    }



}
