package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}