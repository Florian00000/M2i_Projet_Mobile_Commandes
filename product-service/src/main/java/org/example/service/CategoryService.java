package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.dto.category.CategoryDtoGet;
import org.example.dto.category.CategoryDtoPost;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    @Transactional
    public CategoryDtoGet createCategory(CategoryDtoPost categoryDtoPost) {
        Category category = categoryDtoPost.toCategory();
        categoryRepository.persist(category);
        return new CategoryDtoGet(category);
    }
}
