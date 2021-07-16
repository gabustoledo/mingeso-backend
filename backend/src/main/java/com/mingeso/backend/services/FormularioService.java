package com.mingeso.backend.services;

import com.mingeso.backend.models.Formulario;
import com.mingeso.backend.repositories.FormularioRepository;

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
public class FormularioService {

    private final FormularioRepository FormularioRepository;
    FormularioService(FormularioRepository formularioRepository){
        this.FormularioRepository = formularioRepository;
    }

    @GetMapping("/Formulario")
    public List<Formulario> getAllEmergencys() {
        return FormularioRepository.getAllFormularios();
    }

    @GetMapping("/Formulario/count")
    public String countFormularios(){
        int total = FormularioRepository.countFormularios();
        return String.format("Tienes %s Formularios!!", total);
    }
    
    @PostMapping("/Formulario")
    @ResponseBody
    public Formulario createFormulario(@RequestBody Formulario Formulario){
        Formulario result = FormularioRepository.createFormulario(Formulario);
        return result;
    }

    @PutMapping(value = "/FormularioUpdate")
    @ResponseBody
    public void updateFormulario(@RequestBody Formulario Formulario){
        FormularioRepository.updateFormulario(Formulario);
    }

    @DeleteMapping(value = "/FormularioDelete/{Id}")
    public void deleteFormulario(@PathVariable("Id") Integer Id){
        FormularioRepository.deleteFormulario(Id);
    }


    
}