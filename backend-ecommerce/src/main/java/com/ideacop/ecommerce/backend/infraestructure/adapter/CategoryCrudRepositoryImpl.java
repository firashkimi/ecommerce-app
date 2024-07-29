package com.ideacop.ecommerce.backend.infraestructure.adapter;

import com.ideacop.ecommerce.backend.domain.model.Category;
import com.ideacop.ecommerce.backend.domain.port.ICategoryRepository;
import com.ideacop.ecommerce.backend.infraestructure.mapper.CategoryMapper;
import org.springframework.stereotype.Repository;

@Repository
public class CategoryCrudRepositoryImpl implements ICategoryRepository {

    private final ICategoryCrudRepository iCategoryCrudRepository;
    private final CategoryMapper categoryMapper;

    public CategoryCrudRepositoryImpl(ICategoryCrudRepository iCategoryCrudRepository, CategoryMapper categoryMapper) {
        this.iCategoryCrudRepository = iCategoryCrudRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        return categoryMapper.toCategory(iCategoryCrudRepository.save(categoryMapper.toCategoryEntity(category)));
    }

    @Override
    public Iterable<Category> findAll() {
        return categoryMapper.toCategories(iCategoryCrudRepository.findAll());
    }

    @Override
    public Category findById(Integer id) {
        return categoryMapper.toCategory(iCategoryCrudRepository.findById(id).get());
    }

    @Override
    public void deleteById(Integer id) {
        iCategoryCrudRepository.deleteById(id);
    }
}
