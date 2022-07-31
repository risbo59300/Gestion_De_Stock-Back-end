package rca.risbo.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rca.risbo.gestiondestock.controllers.api.ArticleApi;
import rca.risbo.gestiondestock.controllers.api.UtilisateurApi;
import rca.risbo.gestiondestock.dto.ArticleDto;
import rca.risbo.gestiondestock.dto.ChangerMotDePasseUtilisateurDto;
import rca.risbo.gestiondestock.dto.UtilisateurDto;
import rca.risbo.gestiondestock.services.ArticleService;
import rca.risbo.gestiondestock.services.UtilisateurService;

import java.util.List;

@RestController
public class UtilisateurController implements UtilisateurApi {

    private UtilisateurService utilisateurService;

    @Autowired
    public UtilisateurController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @Override
    public UtilisateurDto save(UtilisateurDto utilisateurDto) {
        return utilisateurService.save(utilisateurDto);
    }

    @Override
    public UtilisateurDto findById(Integer id) {
        return utilisateurService.findById(id);
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurService.findAll();
    }

    @Override
    public void delete(Integer id) {
        utilisateurService.delete(id);
    }


}
