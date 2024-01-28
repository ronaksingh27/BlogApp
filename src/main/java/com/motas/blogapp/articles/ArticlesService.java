package com.motas.blogapp.articles;

import com.motas.blogapp.articles.DTOs.CreateArticleRequest;
import com.motas.blogapp.articles.DTOs.UpdateArticleRequest;
import com.motas.blogapp.users.UsersRepository;
import com.motas.blogapp.users.UsersService;
import org.springframework.stereotype.Service;

@Service
public class ArticlesService {

    private final ArticlesRepository articlesRepository;
    private final UsersRepository usersRepository;

    public ArticlesService(ArticlesRepository articlesRepository, UsersRepository usersRepository) {
        this.articlesRepository = articlesRepository;
        this.usersRepository = usersRepository;
    }

    //get all articles
    Iterable<ArticleEntity> getAllArticles()
    {
        return articlesRepository.findAll();
    }

    //get article by slug
    public ArticleEntity getArticleBySlug(String slug )
    {
        var article = articlesRepository.findBySlug(slug);
        if( article == null ) throw new ArticleNotFoundException(slug);

        return article;
    }


    //createArticle
    public ArticleEntity createArticle(CreateArticleRequest a, Long authorId  )
    {
        //GET
        var author = usersRepository.findById(authorId).orElseThrow(()-> new UsersService.UserNotFoundException(authorId));

        //SET
        ArticleEntity article = ArticleEntity.builder()
                .title(a.getTitle())
                .slug(a.getTitle().toLowerCase().replaceAll("\\s+" ,"-"))//TO DO PROPER SLUGGIFICATOIN
                .body(a.getBody())
                .subtitle(a.getSubtitle())
                .author(author)
                .build();

        //SAVE
        return articlesRepository.save(article);

    }

    //updateArticle
    public ArticleEntity updateArticle(Long articleId , UpdateArticleRequest a )
    {
        //GET
        var article = articlesRepository.findById(articleId).orElseThrow(()-> new ArticleNotFoundException(articleId));


        //SET
        if( a.getTitle() != null )
        {
            article.setTitle(a.getTitle());
            article.setSlug(a.getTitle().toLowerCase().replaceAll("\\s+", "-"));
        }

        if (a.getBody() != null) {
            article.setBody(a.getBody());
        }

        if (a.getSubtitle() != null) {
            article.setSubtitle(a.getSubtitle());
        }

        //SAVE
        return articlesRepository.save(article);





    }


    //Exception Handling
    static class ArticleNotFoundException extends IllegalArgumentException
    {
        public ArticleNotFoundException(String slug )
        {
            super("slug : " +  slug + "not found");
        }

        public ArticleNotFoundException(Long id) {
            super("Article with id: " + id + " not found");
        }
    }

}
