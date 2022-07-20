package rca.risbo.gestiondestock.dto;

import lombok.Builder;
import lombok.Data;
import rca.risbo.gestiondestock.model.Ventes;

import java.time.Instant;
import java.util.List;

@Data
@Builder
public class VentesDto {

  private Integer id;

  private String code;

  private Instant dateVente;

  private String commentaire;

  private List<LigneVenteDto> ligneVentes;

  private Integer idEntreprise;

  public static VentesDto fromEntity(Ventes vente) {
    if (vente == null) {
      return null;
    }
    return VentesDto.builder()
        .id(vente.getId())
        .code(vente.getCode())
        .commentaire(vente.getCommentaire())
        .idEntreprise(vente.getIdEntreprise())
        .build();
  }

  public static Ventes toEntity(VentesDto dto) {
    if (dto == null) {
      return null;
    }
    Ventes ventes = new Ventes();
    ventes.setId(dto.getId());
    ventes.setCode(ventes.getCode());
    ventes.setCommentaire(dto.getCommentaire());
    ventes.setIdEntreprise(dto.getIdEntreprise());
    return ventes;
  }
}
