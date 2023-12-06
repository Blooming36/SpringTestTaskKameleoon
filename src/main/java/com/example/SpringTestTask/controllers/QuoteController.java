package com.example.SpringTestTask.controllers;

import com.example.SpringTestTask.dtos.QuoteDto;
import com.example.SpringTestTask.mappers.QuoteMapper;
import com.example.SpringTestTask.models.quoteModels.Quote;
import com.example.SpringTestTask.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;
    private final QuoteMapper quoteMapper;

    @PostMapping("/addNewQuote/{userId}")
    public Quote addNewQute(@PathVariable("userId") Long userId, @RequestBody Quote quote){
        return quoteService.addNewQuote(userId,quote);
    }
    @PostMapping("/updateQuote/{quoteId}")
    public Quote updateQuote(@PathVariable("quoteId") Long quoteId,@RequestParam("content") String content){
        return quoteService.updateQuote(content,quoteId);
    }
    @DeleteMapping("/deleteQuote/{quoteId}")
    public ResponseEntity<?> deleteQuote(@PathVariable("quoteId") Long quoteId){
        quoteService.deleteQuote(quoteId);
        return ResponseEntity.ok("Запись удалена");
    }
    @PostMapping("/addLike/{quoteId}/{userId}")
    public Quote addLikeQuest(@PathVariable("userId") Long userId,@PathVariable("quoteId") Long quoteId){
        return quoteService.likeQuote(userId,quoteId);
    }
    @DeleteMapping("/deleteLikeQuote/{userId}/{quoteId}")
    public Quote deleteLikeQuest(@PathVariable("userId") Long userId,@PathVariable("quoteId") Long quoteId){
        return quoteService.deleteLikeQuote(userId,quoteId);
    }
    @GetMapping("/GetAllQuote")
    public List<QuoteDto> getAllQute(@RequestParam("offset") Integer offset,
                                     @RequestParam("limit") Integer limit){
        List<QuoteDto> quoteDtos = quoteService.findAll(PageRequest.of(offset,limit)).stream().map(el->quoteMapper.toDTO(el)).collect(Collectors.toList());
        quoteDtos.stream().forEach(quoteDto -> {
            quoteDto.setCounterLikes(quoteDto.getLikeQuotes().size());
        });
        return quoteDtos;
    }
    @GetMapping("/GetAllTopQuote")
    public List<QuoteDto> getAllTopQuote(){
        List<QuoteDto> quoteDtos = quoteService.getTop10QuotesWithMostLikes().stream().map(el->quoteMapper.toDTO(el)).collect(Collectors.toList());
         quoteDtos.forEach(quote -> {
            quote.setCounterLikes(quote.getLikeQuotes().size());
        });
        return quoteDtos;
    }
    @GetMapping("/GetAllBadQuote")
    public List<QuoteDto> getAllBadQuote(){
        List<QuoteDto> quoteDtos = quoteService.getTop10QuotesWithLeastLikes().stream().map(el->quoteMapper.toDTO(el)).collect(Collectors.toList());
        quoteDtos.forEach(quote -> {
            quote.setCounterLikes(quote.getLikeQuotes().size());
        });
        return quoteDtos;
    }
    @GetMapping("/GerRandomQuote")
    public QuoteDto getRandomQuote(){
        QuoteDto quoteDto = quoteMapper.toDTO(quoteService.getRandomQuote(quoteService.getAll()));
        quoteDto.setCounterLikes(quoteDto.getLikeQuotes().size());
        return quoteDto;
    }
    
}
