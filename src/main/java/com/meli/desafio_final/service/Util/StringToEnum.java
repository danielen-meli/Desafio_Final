package com.meli.desafio_final.service.Util;

import com.meli.desafio_final.model.enums.Category;
import org.springframework.core.convert.converter.Converter;

import java.util.Locale;

public class StringToEnum implements Converter<String, Category>{

    @Override
    public Category convert(String source) {
        return Category.valueOf(source.toUpperCase(Locale.ROOT));
    }
}
