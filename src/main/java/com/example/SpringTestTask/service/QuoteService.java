package com.example.SpringTestTask.service;

import com.example.SpringTestTask.models.quoteModels.LikeQuote;
import com.example.SpringTestTask.models.quoteModels.Quote;
import com.example.SpringTestTask.repository.LikeQuoteRepository;
import com.example.SpringTestTask.repository.QuoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuoteService {
    private final QuoteRepository quoteRepository;
    private final UserService userService;
    private final LikeQuoteRepository likeQuoteRepository;
    private final JdbcTemplate jdbcTemplate;
    public Quote findById(Long id){
        return quoteRepository.findById(id).get();
    }
    public Quote save(Quote quote){
        return quoteRepository.save(quote);
    }
    public Quote addNewQuote(Long userId,Quote quote){
        quote.setDateCreate(new Date());
        quote.setUserCreater(userService.findById(userId));
        return save(quote);
    }
    public Quote updateQuote(String content,Long id){
        Quote quote = findById(id);
        quote.setContent(content);
        quote.setDateCreate(new Date());
        return save(quote);
    }
    public Quote likeQuote(Long userid,Long quoeteId){
        LikeQuote likeQuote = new LikeQuote(userService.findById(userid),quoeteId);
        likeQuoteRepository.save(likeQuote);
        Quote quote = quoteRepository.findById(quoeteId).get();
        quote.getLikeQuotes().add(likeQuote);
        return save(quote);
    }
    public Quote deleteLikeQuote(Long userid,Long quoeteId){
        LikeQuote likeQuote = likeQuoteRepository.findByIdQuoteAndUserCreater(quoeteId,userService.findById(userid)).get();
        Quote quote = findById(quoeteId);
        quote.getLikeQuotes().remove(likeQuote);
        save(quote);
        likeQuoteRepository.delete(likeQuote);
        return quote;
    }
    public Page<Quote> findAll(PageRequest pageRequest){
        return quoteRepository.findAll(pageRequest);
    }

    public void deleteQuote(Long id)  {
        quoteRepository.delete(findById(id));
        likeQuoteRepository.deleteAll(likeQuoteRepository.findAllByIdQuote(id));
    }
    public List<Quote> getTop10QuotesWithMostLikes() {
        List<Quote> sortedQuotes = quoteRepository.findAll().stream()
                .sorted(Comparator.comparingInt(quote -> quote.getLikeQuotes().size()))
                .collect(Collectors.toList());
        return sortedQuotes.subList(Math.max(0, sortedQuotes.size() - 10), sortedQuotes.size());
    }
    public List<Quote> getTop10QuotesWithLeastLikes() {
        List<Quote> sortedQuotes = quoteRepository.findAll().stream()
                .sorted(Comparator.comparingInt(quote -> quote.getLikeQuotes().size()))
                .collect(Collectors.toList());
        return sortedQuotes.subList(0, Math.min(10, sortedQuotes.size()));
    }
    public Quote getRandomQuote(List<Quote> quotes) {
        Collections.shuffle(quotes);
        return quotes.isEmpty() ? null : quotes.get(0);
    }
    public List<Quote> getAll(){
        return quoteRepository.findAll();
    }
}
