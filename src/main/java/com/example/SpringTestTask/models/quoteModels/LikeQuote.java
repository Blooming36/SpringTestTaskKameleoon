package com.example.SpringTestTask.models.quoteModels;

import com.example.SpringTestTask.models.userModels.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LikeQuote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userLikes_id")
    private User userCreater;
    private Long idQuote;

    public LikeQuote(User userCreater, Long idQuote) {
        this.userCreater = userCreater;
        this.idQuote = idQuote;
    }
}
