package rca.risbo.gestiondestock.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import rca.risbo.gestiondestock.controllers.api.VenteApi;
import rca.risbo.gestiondestock.dto.VentesDto;
import rca.risbo.gestiondestock.services.VentesService;

import java.util.List;

@RestController
public class VenteController implements VenteApi {

    private VentesService ventesService;

    @Autowired
    public VenteController(VentesService ventesService) {
        this.ventesService = ventesService;
    }

    @Override
    public VentesDto save(VentesDto ventesDto) {
        return ventesService.save(ventesDto);
    }

    @Override
    public VentesDto findById(Integer id) {
        return ventesService.findById(id);
    }

    @Override
    public VentesDto findByCode(String code) {
        return ventesService.findByCode(code);
    }

    @Override
    public List<VentesDto> findAll() {
        return ventesService.findAll();
    }

    @Override
    public void delete(Integer id) {
        ventesService.delete(id);
    }
}
