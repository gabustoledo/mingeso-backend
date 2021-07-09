package com.mingeso.backend.services;

import com.mingeso.backend.models.Administrativo;
import com.mingeso.backend.repositories.AdministrativoRepository;

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
public class AdministrativoService {

    private final AdministrativoRepository AdministrativoRepository;
    AdministrativoService(AdministrativoRepository AdministrativoRepository){
        this.AdministrativoRepository = AdministrativoRepository;
    }

    @GetMapping("/Administrativo")
    public List<Administrativo> getAllAdministrativos() {
        return AdministrativoRepository.getAllAdministrativos();
    }

    @GetMapping("/Administrativo/count")
    public String countAdministrativos(){
        int total = AdministrativoRepository.countAdministrativos();
        return String.format("Tienes %s Usuarios Administrativos!!", total);
    }
    
    @PostMapping("/Administrativo")
    @ResponseBody
    public Administrativo createAdministrativo(@RequestBody Administrativo Administrativo){
        Administrativo result = AdministrativoRepository.createAdministrativo(Administrativo);
        return result;
    }

    @PutMapping(value = "/AdministrativoUpdate")
    @ResponseBody
    public void updateAdministrativo(@RequestBody Administrativo Administrativo){
        AdministrativoRepository.updateAdministrativo(Administrativo);
    }

    @DeleteMapping(value = "/AdministrativoDelete/{Id}")
    public void deleteAdministrativo(@PathVariable("Id") Integer Id){
        AdministrativoRepository.deleteAdministrativo(Id);
    }

    @PostMapping("/AdministrativoLogin")
    @ResponseBody
    public List<Administrativo> login(@RequestBody Administrativo Administrativo){
        return AdministrativoRepository.login(Administrativo);
    }
}