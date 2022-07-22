package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.Ventes;

public interface VentesRepository extends JpaRepository<Ventes, Integer> {
}