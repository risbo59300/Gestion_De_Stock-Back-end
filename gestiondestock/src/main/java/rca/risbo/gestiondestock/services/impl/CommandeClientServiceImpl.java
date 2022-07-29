package rca.risbo.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rca.risbo.gestiondestock.dto.CommandeClientDto;
import rca.risbo.gestiondestock.dto.LigneCommandeClientDto;
import rca.risbo.gestiondestock.exception.EntityNotFoundException;
import rca.risbo.gestiondestock.exception.ErrorCodes;
import rca.risbo.gestiondestock.exception.InvalidEntityException;
import rca.risbo.gestiondestock.exception.InvalidOperationException;
import rca.risbo.gestiondestock.model.*;
import rca.risbo.gestiondestock.repository.ArticleRepository;
import rca.risbo.gestiondestock.repository.ClientRepository;
import rca.risbo.gestiondestock.repository.CommandeClientRepository;
import rca.risbo.gestiondestock.repository.LigneCommandeClientRepository;
import rca.risbo.gestiondestock.services.CommandeClientService;
import rca.risbo.gestiondestock.validators.CommandeClientValidator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CommandeClientServiceImpl implements CommandeClientService {

    private CommandeClientRepository commandeClientRepository;
    private LigneCommandeClientRepository ligneCommandeClientRepository;
    private ClientRepository clientRepository;
    private ArticleRepository articleRepository;

    public CommandeClientServiceImpl(CommandeClientRepository commandeClientRepository,
             ClientRepository clientRepository, ArticleRepository articleRepository,
            LigneCommandeClientRepository ligneCommandeClientRepository) {
        this.commandeClientRepository = commandeClientRepository;
        this.clientRepository = clientRepository;
        this.articleRepository = articleRepository;
        this.ligneCommandeClientRepository = ligneCommandeClientRepository;
    }

    @Override
    public CommandeClientDto save(CommandeClientDto commandeClientDto) {

        List<String> errors = CommandeClientValidator.validate(commandeClientDto);
        if (!errors.isEmpty()) {
            log.error("La commande Client n'est pas valide");
            throw new InvalidEntityException("La commande Client n'est pas valide", ErrorCodes.COMMANDE_CLIENT_NOT_VALID, errors);
        }

        Optional<Client> client = clientRepository.findById(commandeClientDto.getClient().getId());
        if (client.isPresent()) {
            log.warn("Client with ID {} was not found in the DB", commandeClientDto.getClient().getId());
            throw new EntityNotFoundException("Aucun client avec l'ID = " + commandeClientDto.getClient().getId() + " n'a été trouvé dans la BDD", ErrorCodes.CLIENT_NOT_FOUND);
        }

        List<String> articleErrors = new ArrayList<>();

        if(commandeClientDto.getLigneCommandeClients() != null) {
            commandeClientDto.getLigneCommandeClients().forEach(linCmdClt -> {
                if(linCmdClt.getArticle() != null){
                Optional<Article> article = articleRepository.findById(linCmdClt.getArticle().getId());
                    if (article.isEmpty()){
                        articleErrors.add("L'article avec l'ID = " + linCmdClt.getArticle().getId() + "n'existe pas");
                    }
                } else {
                    articleErrors.add("Impossible d'enregistrer une commande avec un article NULL");
                }
            });
        }

        if (!articleErrors.isEmpty()) {
            log.warn("");
            throw new InvalidEntityException("Article n'existe pas dans la BDD", ErrorCodes.ARTICLE_NOT_FOUND , articleErrors);
        }

        CommandeClient saveCmdClt = commandeClientRepository.save(CommandeClientDto.toEntity(commandeClientDto));

        if (commandeClientDto.getLigneCommandeClients() != null){
            commandeClientDto.getLigneCommandeClients().forEach(ligCmdClt -> {
                LigneCommandeClient ligneCommandeClient = LigneCommandeClientDto.toEntity(ligCmdClt);
                ligneCommandeClient.setCommandeClient(saveCmdClt);
                ligneCommandeClientRepository.save(ligneCommandeClient);
            });
        }

        return CommandeClientDto.fromEntity(saveCmdClt);
    }

    @Override
    public CommandeClientDto findById(Integer id) {
        if (id == null) {
            log.error("La commande Client ID is NULL");
            return null;
        }
        return commandeClientRepository.findById(id)
            .map(CommandeClientDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                    "Aucune commande client n'a été trouvé avec l'ID = " + id , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
            ));
    }

    @Override
    public CommandeClientDto findByCode(String code) {
        if (code == null) {
            log.error("La commande Client CODE is NULL");
            return null;
        }
        return commandeClientRepository.findCommandeClientByCode(code)
                .map(CommandeClientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Aucune commande client n'a été trouvé avec le code = " + code , ErrorCodes.COMMANDE_CLIENT_NOT_FOUND
                ));
    }

    @Override
    public List<CommandeClientDto> findAll() {
        return commandeClientRepository.findAll().stream()
            .map(CommandeClientDto::fromEntity)
            .collect(Collectors.toList());

    }

    @Override
    public CommandeClientDto updateEtatCommande(Integer idCommande, EtatCommande etatCommande) {
        return null;
    }

    @Override
    public CommandeClientDto updateQuantiteCommande(Integer idCommande, Integer idLigneCommande, BigDecimal quantite) {
        return null;
    }

    @Override
    public CommandeClientDto updateClient(Integer idCommande, Integer idClient) {
        return null;
    }

    @Override
    public CommandeClientDto updateArticle(Integer idCommande, Integer idLigneCommande, Integer newIdArticle) {
        return null;
    }

    @Override
    public CommandeClientDto deleteArticle(Integer idCommande, Integer idLigneCommande) {
        return null;
    }


    @Override
    public List<LigneCommandeClientDto> findAllLignesCommandesClientByCommandeClientId(Integer idCommande) {
        return null;
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("La commande Client ID is NULL");
            return ;
        }
        commandeClientRepository.deleteById(id);
    }
}
