package com.example.SpringTestTask.models.quoteModels;

import com.example.SpringTestTask.models.userModels.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userCreater_id")
    private User userCreater;
    private Date dateCreate;
    @OneToMany
    private List<LikeQuote> likeQuotes;
    private String content;
}
