package com.motas.blogapp.articles.DTOs;

import com.motas.blogapp.users.UserEntity;
import com.motas.blogapp.users.UsersService;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NonNull;
import lombok.Setter;

@Data
@Setter(AccessLevel.NONE)
public class CreateArticleRequest {

    @NonNull
    String title ;

    @NonNull
    String slug;

    @NonNull
    String body;

    @NonNull
    String subtitle;

    @NonNull
    UserEntity author;

}
