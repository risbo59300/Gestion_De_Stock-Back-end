package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.MvtStk;

public interface MvtStkRepository extends JpaRepository<MvtStk, Integer> {
}