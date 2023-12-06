package com.example.SpringTestTask.repository;

import com.example.SpringTestTask.models.quoteModels.Quote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuoteRepository extends JpaRepository<Quote,Long> {
    Optional<Quote> findById(Long id);
}
