package com.example.SpringTestTask.dtos;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class QuoteDto {
    private Date dateCreate;
    private String content;
    private UserDto userCreater;
    private List<QuoteLikeDto> likeQuotes;
    private Integer counterLikes;
}
