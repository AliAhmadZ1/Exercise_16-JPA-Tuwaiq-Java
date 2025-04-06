package com.example.capstone01_tuwaiqjavabootcamp.Service;

import com.example.capstone01_tuwaiqjavabootcamp.Model.Category;
import com.example.capstone01_tuwaiqjavabootcamp.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    ArrayList<Category> categories = new ArrayList<>();

    private final CategoryRepository categoryRepository;

    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public Boolean updateCategory(Integer id, Category category) {
        Category oldCategory = categoryRepository.getById(id);
        if (oldCategory==null)
            return false;

        oldCategory.setName(category.getName());

        categoryRepository.save(oldCategory);
        return true;
    }

    public boolean deleteCategory(Integer id) {
        Category oldCategory = categoryRepository.getById(id);
        if (oldCategory==null)
            return false;

        categoryRepository.delete(oldCategory);
        return true;
    }


}
