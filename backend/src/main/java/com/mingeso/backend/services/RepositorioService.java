package com.mingeso.backend.services;

import com.mingeso.backend.models.Repositorio;
import com.mingeso.backend.repositories.RepositorioRepository;

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
public class RepositorioService {

    private final RepositorioRepository repositorioRepository;
    RepositorioService(RepositorioRepository repositorioRepository){
        this.repositorioRepository = repositorioRepository;
    }

    @GetMapping("/Repositorio")
    public List<Repositorio> getAllRepositorios() {
        return repositorioRepository.getAllRepositorios();
    }

    @GetMapping("/Repositorio/count")
    public String countRepositorios(){
        int total = repositorioRepository.countRepositorios();
        return String.format("Tienes %s Repositorios!!", total);
    }
    
    @PostMapping("/Repositorio")
    @ResponseBody
    public Repositorio createRepositorio(@RequestBody Repositorio repositorio){
			return repositorioRepository.createRepositorio(repositorio);
    }

    @PutMapping(value = "/RepositorioUpdate")
    @ResponseBody
    public void updateRepositorio(@RequestBody Repositorio repositorio){
			repositorioRepository.updateRepositorio(repositorio);
    }

    @DeleteMapping(value = "/RepositorioDelete/{id}")
    public void deleteRepositorio(@PathVariable("id") Integer id){
			repositorioRepository.deleteRepositorio(id);
    }


    
}