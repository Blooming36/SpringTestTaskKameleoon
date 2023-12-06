package com.example.SpringTestTask.repository;

import com.example.SpringTestTask.models.quoteModels.LikeQuote;
import com.example.SpringTestTask.models.userModels.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LikeQuoteRepository extends JpaRepository<LikeQuote,Long> {
    List<LikeQuote> findAllByIdQuote(Long id);
    Optional<LikeQuote> findByIdQuoteAndUserCreater(Long quoteId, User user);
    void deleteAllByIdQuote(Long id);
}
