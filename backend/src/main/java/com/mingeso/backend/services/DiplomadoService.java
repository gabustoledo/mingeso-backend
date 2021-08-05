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

    private final DiplomadoRepository DiplomadoRepository;
    DiplomadoService(DiplomadoRepository diplomadoRepository){
        this.DiplomadoRepository = diplomadoRepository;
    }

    @GetMapping("/Diplomado")
    public List<Diplomado> getAllDiplomados() {
        return DiplomadoRepository.getAllDiplomados();
    }

    @GetMapping("/Diplomado/count")
    public String countDiplomados(){
        int total = DiplomadoRepository.countDiplomados();
        return String.format("Tienes %s Diplomados!!", total);
    }
    
    @PostMapping("/Diplomado")
    @ResponseBody
    public Diplomado createDiplomado(@RequestBody Diplomado Diplomado){
        Diplomado result = DiplomadoRepository.createDiplomado(Diplomado);
        return result;
    }

    @PutMapping(value = "/DiplomadoUpdate")
    @ResponseBody
    public void updateDiplomado(@RequestBody Diplomado Diplomado){
        DiplomadoRepository.updateDiplomado(Diplomado);
    }

    @DeleteMapping(value = "/DiplomadoDelete/{id}")
    public void deleteDiplomado(@PathVariable("id") Integer id){
        DiplomadoRepository.deleteDiplomado(id);
    }


    
}