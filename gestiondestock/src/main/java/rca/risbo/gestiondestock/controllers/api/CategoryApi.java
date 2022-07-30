package rca.risbo.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rca.risbo.gestiondestock.dto.ArticleDto;
import rca.risbo.gestiondestock.dto.CategoryDto;

import java.util.List;

import static rca.risbo.gestiondestock.utils.Constants.APP_ROOT;

@Api(value =APP_ROOT + "/categories")
public interface CategoryApi {

    @PostMapping(value =APP_ROOT + "/categories/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un categorie ", notes = "Cette méthode permet d'enrégistrer ou de modifier un categorie", response = CategoryDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "L'objet categorie cree/modifie"),
        @ApiResponse(code = 400, message = "L'objet categorie n'est pas valide")

    })
    CategoryDto save(@RequestBody CategoryDto categoryDto);

    @GetMapping(value =APP_ROOT + "/categories/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un categorie par ID", notes = "Cette méthode permet de rechercher un categorie par son ID", response = CategoryDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La categorie a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucune categorie n'éxiste dans la BDD avec l'ID fournit")
    })
    CategoryDto findById(@PathVariable("idCategory") Integer idCategory);

    @GetMapping(value =APP_ROOT + "/categories/{codeCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un categorie par CODE", notes = "Cette méthode permet de rechercher une categorie par son CODE", response = CategoryDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La categorie a été trouvé dans la BDD"),
        @ApiResponse(code = 404, message = "Aucun categorie n'éxiste dans la BDD avec le CODE fournit")
    })
    CategoryDto findByCode(@PathVariable("codeCategory") String codeCategory);

    @GetMapping(value =APP_ROOT + "/categories/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des categorie", notes = "Cette méthode permet de rechercher et renvoyer la liste des categories dans la BDD",
        responseContainer = "List<CategoryDto>")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La liste des categories / Une liste vide")
    })
    List<CategoryDto> findAll();

    @DeleteMapping(value =APP_ROOT + "/articles/{idCategory}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cette methode permet de supprimer une categorie par ID", response = CategoryDto.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "La categorie a été supprimé'")
    })
    void delete(@PathVariable("idCategory") Integer idCategory);

}
