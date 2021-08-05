package com.mingeso.backend.services;

import com.mingeso.backend.models.AdministrativoDiplomado;
import com.mingeso.backend.repositories.AdministrativoDiplomadoRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class AdministrativoDiplomadoService {

    private final AdministrativoDiplomadoRepository administrativoDiplomadoRepository;
    AdministrativoDiplomadoService(AdministrativoDiplomadoRepository administrativoDiplomadoRepository){
        this.administrativoDiplomadoRepository = administrativoDiplomadoRepository;
    }

    @GetMapping("/AdministrativoDiplomado")
    public List<AdministrativoDiplomado> getAllEmergencys() {
        return administrativoDiplomadoRepository.getAllAdministrativoDiplomados();
    }

    @GetMapping("/AdministrativoDiplomado/count")
    public String countAdministrativoDiplomados(){
        int total = administrativoDiplomadoRepository.countAdministrativoDiplomados();
        return String.format("Tienes %s Administrativos Diplomados!!", total);
    }
    
    @PostMapping("/AdministrativoDiplomado")
    @ResponseBody
    public AdministrativoDiplomado createAdministrativoDiplomado(@RequestBody AdministrativoDiplomado administrativoDiplomado){
        return administrativoDiplomadoRepository.createAdministrativoDiplomado(administrativoDiplomado);
    }

    @PutMapping(value = "/AdministrativoDiplomadoUpdate")
    @ResponseBody
    public void updateAdministrativoDiplomado(@RequestBody AdministrativoDiplomado administrativoDiplomado){
        administrativoDiplomadoRepository.updateAdministrativoDiplomado(administrativoDiplomado);
    }

    @DeleteMapping(value = "/AdministrativoDiplomadoDelete/{id}")
    public void deleteAdministrativoDiplomado(@PathVariable("id") Integer id){
        administrativoDiplomadoRepository.deleteAdministrativoDiplomado(id);
    }


    
}