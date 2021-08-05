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

    private final AdministrativoRepository administrativoRepository;
    AdministrativoService(AdministrativoRepository administrativoRepository){
        this.administrativoRepository = administrativoRepository;
    }

    @GetMapping("/Administrativo")
    public List<Administrativo> getAllAdministrativos() {
        return administrativoRepository.getAllAdministrativos();
    }

    @GetMapping("/Administrativo/count")
    public String countAdministrativos(){
        int total = administrativoRepository.countAdministrativos();
        return String.format("Tienes %s Usuarios Administrativos!!", total);
    }
    
    @PostMapping("/Administrativo")
    @ResponseBody
    public Administrativo createAdministrativo(@RequestBody Administrativo administrativo){
        return administrativoRepository.createAdministrativo(administrativo);
    }

    @PutMapping(value = "/AdministrativoUpdate")
    @ResponseBody
    public void updateAdministrativo(@RequestBody Administrativo administrativo){
        administrativoRepository.updateAdministrativo(administrativo);
    }

    @DeleteMapping(value = "/AdministrativoDelete/{id}")
    public void deleteAdministrativo(@PathVariable("id") Integer id){
        administrativoRepository.deleteAdministrativo(id);
    }

    @PostMapping("/AdministrativoLogin")
    @ResponseBody
    public List<Administrativo> login(@RequestBody Administrativo administrativo){
        return administrativoRepository.login(administrativo);
    }
}