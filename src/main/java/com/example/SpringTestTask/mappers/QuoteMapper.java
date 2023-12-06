package com.example.SpringTestTask.mappers;

import com.example.SpringTestTask.dtos.QuoteDto;
import com.example.SpringTestTask.models.quoteModels.Quote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface QuoteMapper {
    @Mapping(source = "userCreater", target = "userCreater")
    @Mapping(source = "likeQuotes", target = "likeQuotes")
    QuoteDto toDTO(Quote quote);
}
