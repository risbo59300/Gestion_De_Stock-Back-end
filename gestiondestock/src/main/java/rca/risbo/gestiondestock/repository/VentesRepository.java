package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.Ventes;

import java.util.Optional;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {

    Optional<Ventes> findVentesByCode(String code);
}