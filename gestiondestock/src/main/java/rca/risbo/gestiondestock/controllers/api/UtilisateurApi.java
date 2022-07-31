package rca.risbo.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import rca.risbo.gestiondestock.dto.UtilisateurDto;

import java.util.List;

import static rca.risbo.gestiondestock.utils.Constants.UTILISATEUR_ENDPOINT;

@Api(UTILISATEUR_ENDPOINT)
public interface UtilisateurApi {

    @PostMapping(UTILISATEUR_ENDPOINT +"/create")
    UtilisateurDto save(@RequestBody UtilisateurDto dto);

    @GetMapping(UTILISATEUR_ENDPOINT +"/{idUtilisateur}")
    UtilisateurDto findById(@PathVariable("idUtilisateur") Integer id);

    @GetMapping(UTILISATEUR_ENDPOINT +"/all")
    List<UtilisateurDto> findAll();

    @DeleteMapping(UTILISATEUR_ENDPOINT +"/delete/{idUtilisateur}")
    void delete(@PathVariable("idUtilisateur") Integer id);



}
