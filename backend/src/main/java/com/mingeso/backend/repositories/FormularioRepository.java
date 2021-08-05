package com.mingeso.backend.repositories;
import java.util.List;
import com.mingeso.backend.models.Formulario;

public interface FormularioRepository {
    public int countFormularios();
    public List<Formulario> getAllFormularios();
    public Formulario createFormulario(Formulario formulario);
    public void updateFormulario(Formulario formulario);
    public void deleteFormulario(Integer id);
}