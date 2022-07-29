package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.CommandeClient;
import rca.risbo.gestiondestock.model.CommandeFournisseur;

import java.util.List;
import java.util.Optional;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Integer> {

    Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);
    List<CommandeClient> findAllByFournisseurId(Integer id);
}