package rca.risbo.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rca.risbo.gestiondestock.dto.ArticleDto;

import java.util.List;

import static rca.risbo.gestiondestock.utils.Constants.APP_ROOT;

@Api(value =APP_ROOT + "/articles") //Annotation pour avoir de la documentation de swagger
public interface ArticleApi {

    @PostMapping(value =APP_ROOT + "/articles/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un article ", notes = "Cette méthode permet d'enrégistrer ou de modifier un article", response = ArticleDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "L'objet article cree/modifie"),
        @ApiResponse(code = 400, message = "L'objet article n'est pas valide")

    })
    ArticleDto save(@RequestBody ArticleDto articleDto);

    @GetMapping(value =APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par ID", notes = "Cette méthode permet de rechercher un article par son ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'éxiste dans la BDD avec l'ID fournit")
    })
    ArticleDto findById(@PathVariable("idArticle") Integer id);

    @GetMapping(value =APP_ROOT + "/articles/{codeArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un article par CODE", notes = "Cette méthode permet de rechercher un article par son CODE", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun article n'éxiste dans la BDD avec le CODE fournit")
    })
    ArticleDto findByCodeArticle(@PathVariable("codeArticle") String codeArticle);

    @GetMapping(value =APP_ROOT + "/articles/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des article", notes = "Cette méthode permet de rechercher et renvoyer la liste des articles dans la BDD",
            responseContainer = "List<ArticleDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des articles / Une liste vide")
    })
    List<ArticleDto> findAll();

    @DeleteMapping(value =APP_ROOT + "/articles/{idArticle}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cette methode permet de supprimer un article par ID", response = ArticleDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'article a été supprimé'")
    })
    void delete(@PathVariable("idArticle") Integer id);
}
