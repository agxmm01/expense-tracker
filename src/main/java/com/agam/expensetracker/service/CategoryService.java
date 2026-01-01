package com.agam.expensetracker.service;

import com.agam.expensetracker.dto.CategoryDTO;
import com.agam.expensetracker.entity.CategoryEntity;
import com.agam.expensetracker.entity.ProfileEntity;
import com.agam.expensetracker.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final ProfileService profileService;
    private final CategoryRepository categoryRepository;

    // save category
    public CategoryDTO saveCategory(CategoryDTO categoryDTO) {
        ProfileEntity profile = profileService.getCurrentProfile();
        if(categoryRepository.existsByNameAndProfileId(categoryDTO.getName(), profile.getId())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Category with this name already exists");
        }
        CategoryEntity newCategory = toEntity(categoryDTO, profile);
        newCategory = categoryRepository.save(newCategory);
        return toDTO(newCategory);
    }

    // Get Categories by ProfileId
    public List<CategoryDTO> getCategoriesForCurrentUser(){
       ProfileEntity currentUser = profileService.getCurrentProfile();
       List<CategoryEntity> categories = categoryRepository.findByProfileId(String.valueOf(currentUser.getId()));
       return categories.stream().map(this::toDTO).toList();
    }

    // get categories by type for current user
    public List<CategoryDTO> getAllCategoriesByTypeForCurrentUser(String type) {
        ProfileEntity currentUser = profileService.getCurrentProfile();
        List<CategoryEntity> entities = categoryRepository.findByTypeAndProfileId(type,currentUser.getId());
        return entities.stream().map(this::toDTO).toList();
    }

    // Update category for current user
    public CategoryDTO updateCategory(Long categoryId , CategoryDTO categoryDTO) {
        ProfileEntity profile = profileService.getCurrentProfile();
        CategoryEntity existingCategory = categoryRepository.findByIdAndProfileId(categoryId, profile.getId())
                .orElseThrow(() -> new RuntimeException("Category not found or not Accessible"));
        existingCategory.setName(categoryDTO.getName());
        existingCategory.setIcon(categoryDTO.getIcon());
        existingCategory.setType(categoryDTO.getType());
        categoryRepository.save(existingCategory);
        return toDTO(existingCategory);
    }
    //Helper Methods
    private CategoryEntity toEntity(CategoryDTO categoryDTO, ProfileEntity profileEntity) {
        return CategoryEntity.builder()
                .name(categoryDTO.getName())
                .icon(categoryDTO.getIcon())
                .profile(profileEntity)
                .type(categoryDTO.getType())
                .build();
    }

    private CategoryDTO toDTO(CategoryEntity categoryEntity) {
        return CategoryDTO.builder()
                .id(categoryEntity.getId())
                .profileId(categoryEntity.getProfile() != null ? categoryEntity.getProfile().getId() : null)
                .name(categoryEntity.getName())
                .icon(categoryEntity.getIcon())
                .createdAt(categoryEntity.getCreatedAt())
                .updatedAt(categoryEntity.getUpdatedAt())
                .type(categoryEntity.getType())
                .build();
    }
}
