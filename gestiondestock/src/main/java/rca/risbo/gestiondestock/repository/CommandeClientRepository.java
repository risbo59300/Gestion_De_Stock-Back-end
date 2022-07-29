package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.CommandeClient;

import java.util.List;
import java.util.Optional;

public interface CommandeClientRepository extends JpaRepository<CommandeClient, Integer> {


    Optional<CommandeClient> findCommandeClientByCode(String code);

    List<CommandeClient> findAllByClientId(Integer id);

}