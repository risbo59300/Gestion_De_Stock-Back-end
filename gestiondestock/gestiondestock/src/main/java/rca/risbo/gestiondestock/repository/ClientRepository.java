package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.Client;

public interface ClientRepository extends JpaRepository<Client, Integer> {
}