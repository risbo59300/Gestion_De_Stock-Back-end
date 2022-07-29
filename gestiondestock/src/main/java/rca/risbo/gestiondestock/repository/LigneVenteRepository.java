package rca.risbo.gestiondestock.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rca.risbo.gestiondestock.model.LigneVente;

import java.util.List;

public interface LigneVenteRepository extends JpaRepository<LigneVente, Integer> {

    List<LigneVente> findAllByArticleId(Integer idArticle);

    List<LigneVente> findAllByVenteId(Integer id);
}