package com.mingeso.backend.services;

import com.mingeso.backend.models.Diplomado;
import com.mingeso.backend.repositories.DiplomadoRepository;

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
public class DiplomadoService {

    private final DiplomadoRepository diplomadoRepository;

    DiplomadoService(DiplomadoRepository diplomadoRepository){
        this.diplomadoRepository = diplomadoRepository;
    }

    @GetMapping("/Diplomado")
    public List<Diplomado> getAllDiplomados() {
        return diplomadoRepository.getAllDiplomados();
    }

    @GetMapping("/Diplomado/count")
    public String countDiplomados(){
        int total = diplomadoRepository.countDiplomados();
        return String.format("Tienes %s Diplomados!!", total);
    }
    
    @PostMapping("/Diplomado")
    @ResponseBody
    public Diplomado createDiplomado(@RequestBody Diplomado diplomado){
        Diplomado result = diplomadoRepository.createDiplomado(diplomado);
        return result;
    }

    @PutMapping(value = "/DiplomadoUpdate")
    @ResponseBody
    public void updateDiplomado(@RequestBody Diplomado diplomado){
        diplomadoRepository.updateDiplomado(diplomado);
    }

    @DeleteMapping(value = "/DiplomadoDelete/{id}")
    public void deleteDiplomado(@PathVariable("id") Integer id){
        diplomadoRepository.deleteDiplomado(id);
    }


    
}