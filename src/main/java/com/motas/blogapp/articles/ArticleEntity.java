package com.motas.blogapp.articles;

import com.motas.blogapp.users.UserEntity;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;

@Entity(name = "articles")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(nullable = false)
    private Long id;

    @NonNull
    private String title;

    @NonNull
    @Column( unique = true)
    private String slug;//should be unique

    @Nullable
    private String subtitle;

    @NonNull
    private String body;

    @CreatedDate
    private Date createdAt;

    @ManyToOne
    @JoinColumn( name = "authorID" , nullable = false)
    private UserEntity author;




    //TODO : ADD TAGS









}
