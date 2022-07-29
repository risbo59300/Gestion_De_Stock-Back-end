package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Integer> {
}