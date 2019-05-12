package com.boun.glearn.service;

import com.boun.glearn.model.Content;
import com.boun.glearn.model.Material;
import com.boun.glearn.model.MaterialSummary;
import com.boun.glearn.repository.ContentRepository;
import com.boun.glearn.repository.MaterialRepository;
import com.boun.glearn.repository.UserProgressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@CrossOrigin(origins = "*", maxAge = 3600)
public class SearchServiceImpl implements SearchService {

    @Autowired
    private MaterialRepository repository;

    @Autowired
    private UserProgressRepository userProgressRepository;

    @Override
    public List<MaterialSummary> getAllMaterialSummaries() {
        List<Material> foundMaterials = repository.findAll().stream().collect(Collectors.toList());
        return materialToMaterialSummary(foundMaterials);
    }

    @Override
    public List<Content> getContents(Long materialId) {
        return repository.getContentsByMaterialId(materialId);
    }

    @Override
    public List<MaterialSummary> searchMaterials(String keyword) {
        List<Material> all = repository.findAll();
        List<Material> foundByName = all.stream().filter(m -> m.getTitle().contains(keyword)).collect(Collectors.toList());
        List<Material> foundByContents = all.stream().filter(m -> m.getContents().stream().anyMatch(t -> t.getTitle().contains(keyword))).collect(Collectors.toList());
        List<Material> foundByKeywords = all.stream().filter(m -> m.getContents().stream()
                .flatMap(k -> k.getKeywords().stream())
                .anyMatch(k -> k.getLabel().contains(keyword))).collect(Collectors.toList());

        List<Material> materialList = new ArrayList<>();
        materialList.addAll(foundByName);
        materialList.addAll(foundByContents);
        materialList.addAll(foundByKeywords);

        return materialToMaterialSummary(materialList);
    }


    @Override
    public Material getMaterialDetail(Long materialId) {
        Optional<Material> found = repository.findById(materialId);
        if (found.isPresent()) {
            return found.get();
        }
        return null;
    }

    @Override
    public List<MaterialSummary> getCreatedMaterials(String createdBy) {
        List<Material> materials = repository.getMaterialByCreatedBy(createdBy);
        return materialToMaterialSummary(materials);
    }

    @Override
    public List<MaterialSummary> getInProgressMaterial(String username) {
        Set<Long> materialIds = userProgressRepository.getUserProgressByUsername(username).stream()
                .filter(p -> !p.isCompleted())
                .map(m -> m.getMaterialId())
                .collect(Collectors.toSet());

        List<Material> foundMaterials = new ArrayList<>();

        for(Long id: materialIds){
            foundMaterials.add(repository.findById(id).get());
        }

        return materialToMaterialSummary(foundMaterials);
    }

    @Override
    public List<MaterialSummary> getCompletedMaterial(String username) {
        Set<Long> materialIds = userProgressRepository.getUserProgressByUsername(username).stream()
                .filter(p -> p.isCompleted())
                .map(m -> m.getMaterialId())
                .collect(Collectors.toSet());

        List<Material> foundMaterials = new ArrayList<>();

        for(Long id: materialIds){
            foundMaterials.add(repository.findById(id).get());
        }

        return materialToMaterialSummary(foundMaterials);
    }


    private List<MaterialSummary> materialToMaterialSummary(List<Material> materialList) {
        return materialList.stream().map(m ->
                new MaterialSummary(m.getId(), m.getImage(), m.getTitle(),
                        m.getContents().stream()
                                .flatMap(c -> c.getKeywords().stream())
                                .map(k -> k.getLabel())
                                .collect(Collectors.toList()), m.getDescription(), m.getCreatedBy()))
                .collect(Collectors.toList());
    }



}
