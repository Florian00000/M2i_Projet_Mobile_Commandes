package org.example.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.example.dto.category.CategoryDtoGet;
import org.example.dto.category.CategoryDtoPost;
import org.example.entity.Category;
import org.example.repository.CategoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CategoryService {

    @Inject
    CategoryRepository categoryRepository;

    public List<CategoryDtoGet> getAllCategories() {
        List<Category> categories = categoryRepository.listAll();
        return categories.stream().map(CategoryDtoGet::new).toList();
    }

    public CategoryDtoGet getCategoryById(long id) {
        return new CategoryDtoGet(categoryRepository.findById(id));
    }

    @Transactional
    public CategoryDtoGet createCategory(CategoryDtoPost categoryDtoPost) {
        Category category = categoryDtoPost.toCategory();
        categoryRepository.persist(category);
        return new CategoryDtoGet(category);
    }

    @Transactional
    public CategoryDtoGet updateCategory(CategoryDtoPost categoryDtoPost, long id) {
        Optional <Category> category = categoryRepository.findByIdOptional(id);
        if (category.isPresent()) {
            category.get().setName(categoryDtoPost.getName());
            categoryRepository.persist(category.get());
            return new CategoryDtoGet(category.get());
        }else {
            return null;
        }
    }

    @Transactional
    public boolean deleteCategory(long id) {
        return categoryRepository.deleteById(id);
    }
}
