package com.example.SpringTestTask.service;

import com.example.SpringTestTask.repository.LikeQuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LikeQuoteService {
    private final LikeQuoteRepository likeQuoteRepository;
}
