package rca.risbo.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import rca.risbo.gestiondestock.dto.CommandeFournisseurDto;
import rca.risbo.gestiondestock.dto.LigneCommandeFournisseurDto;
import rca.risbo.gestiondestock.dto.LigneVenteDto;
import rca.risbo.gestiondestock.dto.VentesDto;
import rca.risbo.gestiondestock.exception.EntityNotFoundException;
import rca.risbo.gestiondestock.exception.ErrorCodes;
import rca.risbo.gestiondestock.exception.InvalidEntityException;
import rca.risbo.gestiondestock.model.*;
import rca.risbo.gestiondestock.repository.*;
import rca.risbo.gestiondestock.services.VentesService;
import rca.risbo.gestiondestock.validators.CommandeFournisseurValidator;
import rca.risbo.gestiondestock.validators.VentesValidator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class VenteServiceImpl implements VentesService {

    private VentesRepository ventesRepository;
    private LigneVenteRepository ligneVenteRepository;
    private ArticleRepository articleRepository;

    public VenteServiceImpl(VentesRepository ventesRepository,
                            LigneVenteRepository ligneVenteRepository,
                            ArticleRepository articleRepository) {
        this.ventesRepository = ventesRepository;
        this.ligneVenteRepository = ligneVenteRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        List<String> errors = VentesValidator.validate(ventesDto);
        if (!errors.isEmpty()) {
            log.error("Vente n'est pas valide");
            throw new InvalidEntityException("L'objet vente n'est pas valide", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        List<String> articleErrors = new ArrayList<>();

        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            Optional<Article> article = articleRepository.findById(ligneVenteDto.getArticle().getId());
            if (article.isEmpty()) {
                articleErrors.add("Aucun article avec l'ID " + ligneVenteDto.getArticle().getId() + " n'a été trouvé dans la BDD");
            }
        });

        if (!articleErrors.isEmpty()) {
            log.error("One or more article were not found in the DB, {}", errors);
            throw new InvalidEntityException("Un ou plusieurs articles n'ont été trouvé dans la BDD", ErrorCodes.VENTE_NOT_VALID, errors);
        }

        Ventes saveVente = ventesRepository.save(VentesDto.toEntity(ventesDto));

        ventesDto.getLigneVentes().forEach(ligneVenteDto -> {
            LigneVente ligneVente = LigneVenteDto.toEntity(ligneVenteDto);
            ligneVente.setVente(saveVente);
            ligneVenteRepository.save(ligneVente);
        });

        return VentesDto.fromEntity(saveVente);
    }

    @Override
    public VentesDto findById(Integer id) {
        if (id == null) {
            log.warn("Vente ID is NULL");
        return null;
        }

        return ventesRepository.findById(id)
            .map(VentesDto::fromEntity)
            .orElseThrow(() -> new EntityNotFoundException(
                "Aucune vente n'a été trouvé dans la BDD", ErrorCodes.VENTE_NOT_FOUND
            ));
    }

    @Override
    public VentesDto findByCode(String code) {
        if (code == null) {
            log.warn("Vente CODE is NULL");
            return null;
        }

        return ventesRepository.findVentesByCode(code)
                .map(VentesDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(
                  "Aucune vente avec le CODE" + code + " n'a été trouvé dans la BDD", ErrorCodes.VENTE_NOT_VALID
                ));
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesRepository.findAll().stream()
            .map(VentesDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Vente ID is NULL");
            return ;
        }
        ventesRepository.deleteById(id);
    }
}
