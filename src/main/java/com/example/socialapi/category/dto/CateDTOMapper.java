package com.example.socialapi.category.dto;

import com.example.socialapi.category.Category;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class CateDTOMapper implements Function<Category, CateDTO> {

    @Override
    public CateDTO apply(Category category) {
        return new CateDTO(category.getId(), category.getType());
    }

    @Override
    public <V> Function<V, CateDTO> compose(Function<? super V, ? extends Category> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Category, V> andThen(Function<? super CateDTO, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
