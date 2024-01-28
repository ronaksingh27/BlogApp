package com.motas.blogapp.comments;

import com.motas.blogapp.articles.ArticleEntity;
import com.motas.blogapp.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.apache.catalina.User;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "comments")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @Nullable
    private String title;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn( name = "articleId" , nullable = false )
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn( name = "authorId" , nullable = false )
    private UserEntity author;




}
