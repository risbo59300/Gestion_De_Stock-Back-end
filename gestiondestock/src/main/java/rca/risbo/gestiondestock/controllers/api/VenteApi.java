package rca.risbo.gestiondestock.controllers.api;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;
import rca.risbo.gestiondestock.dto.VentesDto;

import java.util.List;

import static rca.risbo.gestiondestock.utils.Constants.VENTES_ENDPOINT;

@Api(VENTES_ENDPOINT)
public interface VenteApi {

    @PostMapping(VENTES_ENDPOINT + "/create")
    VentesDto save(@RequestBody VentesDto dto);

    @GetMapping(VENTES_ENDPOINT + "/{idVente}")
    VentesDto findById(@PathVariable("idVente") Integer id);

    @GetMapping(VENTES_ENDPOINT + "/{codeVente}")
    VentesDto findByCode(@PathVariable("codeVente") String code);

    @GetMapping(VENTES_ENDPOINT + "/all")
    List<VentesDto> findAll();

    @DeleteMapping(VENTES_ENDPOINT + "/delete/{idVente}")
    void delete(@PathVariable("idVente") Integer id);
}
