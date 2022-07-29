package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rca.risbo.gestiondestock.model.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    //Exemple de Requête JPQL
    @Query("select article from Article article where article.codeArticle = :code and  article.designation = :designation")
    List<Article> findByCustomQuery(@Param("code") String c, @Param("designation") String d);

    // Exemple de Requête native
    @Query(value = "select article  from Article article where article.codeArticle = :code", nativeQuery = true)
    List<Article> findByCustomnativeQuery(@Param("code") String c);

    List<Article> findByCodeArticleIgnoreCaseAndDesignation(String codeArticle, String designation);

    Optional<Article> findArticleByCodeArticle(String codeArticle);

    List<Article> findAllByCategoryId(Integer id);
}