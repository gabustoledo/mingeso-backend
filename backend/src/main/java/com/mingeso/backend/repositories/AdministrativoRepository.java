package com.mingeso.backend.repositories;
import java.util.List;
import com.mingeso.backend.models.Administrativo;

public interface AdministrativoRepository {
    public int countAdministrativos();
    public List<Administrativo> getAllAdministrativos();
    public Administrativo createAdministrativo(Administrativo administrativo);
    public void updateAdministrativo(Administrativo administrativo);
    public void deleteAdministrativo(Integer id);
    public List<Administrativo> login(Administrativo administrativo);
}