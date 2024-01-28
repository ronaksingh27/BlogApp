package com.motas.blogapp.articles.DTOs;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Data
@Setter( AccessLevel.NONE)
public class UpdateArticleRequest
{
    @Nullable
    String title;

    @Nullable
     String body;

    @Nullable
     String subtitle;
}
