package com.motas.blogapp.articles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticlesRepository extends JpaRepository<ArticleEntity,Long> {
    ArticleEntity findBySlug(String slug);
}
