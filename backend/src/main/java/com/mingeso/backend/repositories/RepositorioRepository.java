package com.mingeso.backend.repositories;
import java.util.List;
import com.mingeso.backend.models.Repositorio;

public interface RepositorioRepository {
    public int countRepositorios();
    public List<Repositorio> getAllRepositorios();
    public Repositorio createRepositorio(Repositorio repositorio);
    public void updateRepositorio(Repositorio repositorio);
    public void deleteRepositorio(Integer id);
}