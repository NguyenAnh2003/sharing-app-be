package com.example.newsfeedapi.category.dto;

import com.example.newsfeedapi.category.Category;
import org.springframework.stereotype.Service;
import java.util.function.Function;

@Service
public class EmbeddedCategoryMapper implements Function<Category, EmbeddedCategory> {
    @Override
    public EmbeddedCategory apply(Category category) {
        return new EmbeddedCategory(category.getId(),
                category.getType());
    }

    @Override
    public <V> Function<V, EmbeddedCategory> compose(Function<? super V, ? extends Category> before) {
        return Function.super.compose(before);
    }

    @Override
    public <V> Function<Category, V> andThen(Function<? super EmbeddedCategory, ? extends V> after) {
        return Function.super.andThen(after);
    }
}
