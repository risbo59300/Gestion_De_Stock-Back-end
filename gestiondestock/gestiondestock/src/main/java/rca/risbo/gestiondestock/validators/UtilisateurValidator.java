package rca.risbo.gestiondestock.validators;

import org.springframework.util.StringUtils;
import rca.risbo.gestiondestock.dto.UtilisateurDto;

import java.util.ArrayList;
import java.util.List;

public class UtilisateurValidator {

    public static List<String> validate(UtilisateurDto utilisateurDto) {

        List<String> errors = new ArrayList<>();

        if( utilisateurDto == null) {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
            errors.add("Veuillez renseigner le mail de l'utilisateur");
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
            return errors;
        }

        if( !StringUtils.hasLength(utilisateurDto.getNom())) {
            errors.add("Veuillez renseigner le nom de l'utilisateur");
        }
        if( !StringUtils.hasLength(utilisateurDto.getPrenom())) {
            errors.add("Veuillez renseigner le prenom de l'utilisateur");
        }
        if( !StringUtils.hasLength(utilisateurDto.getEmail())) {
            errors.add("Veuillez renseigner le mail de l'utilisateur");
        }
        if( !StringUtils.hasLength(utilisateurDto.getMoteDePasse())) {
            errors.add("Veuillez renseigner le mot de passe de l'utilisateur");
        }
        if( utilisateurDto.getDateDeNaissance() == null) {
            errors.add("Veuillez renseigner la date de naissance de l'utilisateur");
        }

        if( utilisateurDto.getAdresse() == null) {
            errors.add("Veuillez renseigner l'adresse de l'utilisateur");
        } else {

            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getAdresse1())) {
                errors.add("Le champs 'Adresse 1' est obligatoire");
            }
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getVille())) {
                errors.add("Le champs 'ville' est obligatoire");
            }
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getCodePostale())) {
                errors.add("Le champs 'code postale' est obligatoire");
            }
            if( !StringUtils.hasLength(utilisateurDto.getAdresse().getPays())) {
                errors.add("Le champs 'Pays' est obligatoire");
            }
        }
        return errors;

    }
}
