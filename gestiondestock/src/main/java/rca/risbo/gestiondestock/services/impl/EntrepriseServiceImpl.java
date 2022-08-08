package rca.risbo.gestiondestock.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rca.risbo.gestiondestock.dto.EntrepriseDto;
import rca.risbo.gestiondestock.dto.RolesDto;
import rca.risbo.gestiondestock.dto.UtilisateurDto;
import rca.risbo.gestiondestock.exception.EntityNotFoundException;
import rca.risbo.gestiondestock.exception.ErrorCodes;
import rca.risbo.gestiondestock.exception.InvalidEntityException;
import rca.risbo.gestiondestock.repository.EntrepriseRepository;
import rca.risbo.gestiondestock.repository.RolesRepository;
import rca.risbo.gestiondestock.services.EntrepriseService;
import rca.risbo.gestiondestock.services.UtilisateurService;
import rca.risbo.gestiondestock.validators.EntrepriseValidator;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EntrepriseServiceImpl implements EntrepriseService {

    private EntrepriseRepository entrepriseRepository;
    private UtilisateurService utilisateurService;
    private RolesRepository rolesRepository;

    @Autowired
    public EntrepriseServiceImpl(EntrepriseRepository entrepriseRepository, UtilisateurService utilisateurService, RolesRepository rolesRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.utilisateurService = utilisateurService;
        this.rolesRepository = rolesRepository;
    }

    @Override
    public EntrepriseDto save(EntrepriseDto entrepriseDto) {
        List<String> errors = EntrepriseValidator.validate(entrepriseDto);
        if (!errors.isEmpty()) {
            log.error("Entreprise is not valid {} ", entrepriseDto);
            throw new InvalidEntityException("L'entreprise n'est pas valide ", ErrorCodes.ENTREPRISE_NOT_VALID, errors);
        }
        EntrepriseDto saveEntreprise = EntrepriseDto.fromEntity(
            entrepriseRepository.save(EntrepriseDto.toEntity(entrepriseDto))
        );

        UtilisateurDto utilisateur = fromEntreprise(saveEntreprise);

        UtilisateurDto saveUser = utilisateurService.save(utilisateur);

        RolesDto rolesDto = RolesDto.builder()
            .roleName("ADMIN")
            .utilisateur(saveUser)
            .build();

        rolesRepository.save(RolesDto.toEntity(rolesDto));

        return saveEntreprise;
    }

    private UtilisateurDto fromEntreprise(EntrepriseDto dto) {
        return UtilisateurDto.builder()
            .adresse(dto.getAdresse())
            .nom(dto.getNom())
            .prenom(dto.getCodeFiscal())
            .email(dto.getEmail())
            .moteDePasse(generateRandomPassword())
            .entreprise(dto)
            .dateDeNaissance(Instant.now())
            .photo(dto.getPhoto())
            .build();
    }

    private String generateRandomPassword() {
        return "som3R@nd0mP@$$word";
    }

    @Override
    public EntrepriseDto findById(Integer id) {
        if (id == null){
            log.error("EntrepriseDto ID is null");
            return null;
        }
        return entrepriseRepository.findById(id)
            .map(EntrepriseDto::fromEntity)
            .orElseThrow(()-> new EntityNotFoundException(
                "Aucune entreprise avec l'ID = " + id + " n'a été trouvé dans la BDD",
                ErrorCodes.ENTREPRISE_NOT_FOUND)
            );
    }

    @Override
    public List<EntrepriseDto> findAll() {
        return entrepriseRepository.findAll().stream()
            .map(EntrepriseDto::fromEntity)
            .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("EntrepriseDto ID is null");
            return;
        }
        entrepriseRepository.deleteById(id);
    }
}
