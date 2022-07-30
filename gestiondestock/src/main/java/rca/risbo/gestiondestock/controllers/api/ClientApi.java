package rca.risbo.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import rca.risbo.gestiondestock.dto.ArticleDto;
import rca.risbo.gestiondestock.dto.ClientDto;

import java.util.List;

import static rca.risbo.gestiondestock.utils.Constants.APP_ROOT;

@Api(value =APP_ROOT + "/clients")
public interface ClientApi {

    @PostMapping(value =APP_ROOT + "/clients/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Enregistrer un client ", notes = "Cette méthode permet d'enrégistrer ou de modifier un client", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'objet client cree/modifie"),
            @ApiResponse(code = 400, message = "L'objet client n'est pas valide")

    })
    ClientDto save(@RequestBody ClientDto clientDto);

    @GetMapping(value =APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un client par ID", notes = "Cette méthode permet de rechercher un client par son ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a été trouvé dans la BDD"),
            @ApiResponse(code = 404, message = "Aucun client n'éxiste dans la BDD avec l'ID fournit")
    })
    ClientDto findById(@PathVariable("idClient") Integer id);

    @GetMapping(value =APP_ROOT + "/clients/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des clients", notes = "Cette méthode permet de rechercher et renvoyer la liste des clients dans la BDD",
            responseContainer = "List<ClientDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des clients / Une liste vide")
    })
    List<ClientDto> findAll();

    @DeleteMapping(value =APP_ROOT + "/clients/{idClient}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Cette methode permet de supprimer un client par ID", response = ClientDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le client a été supprimé'")
    })
    void delete(@PathVariable("idClient") Integer id);

}
