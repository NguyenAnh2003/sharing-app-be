package com.example.socialapi.category.dto;

import com.example.socialapi.category.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CategoryDTOMapper implements Function<Category, CategoryDTO> {

    @Override
    public CategoryDTO apply(Category category) {
        return new CategoryDTO(category.getId(), category.getType());
    }

    @Override
    public <V> Function<V, CategoryDTO> compose(Function<? super V, ? extends Category> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Category, V> andThen(Function<? super CategoryDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
