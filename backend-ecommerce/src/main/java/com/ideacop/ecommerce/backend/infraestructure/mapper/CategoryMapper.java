package com.ideacop.ecommerce.backend.infraestructure.mapper;

import com.ideacop.ecommerce.backend.domain.model.Category;
import com.ideacop.ecommerce.backend.domain.model.User;
import com.ideacop.ecommerce.backend.infraestructure.entity.CategoryEntity;
import com.ideacop.ecommerce.backend.infraestructure.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "dateCreated", target = "dateCreated"),
            @Mapping(source = "dateUpdated", target = "dateUpdated"),
    })
    Category toCategory(CategoryEntity categoryEntity);

    Iterable<Category> toCategories(Iterable<CategoryEntity> categoryEntities);

    @InheritInverseConfiguration
    CategoryEntity toCategoryEntity(Category category);
}
