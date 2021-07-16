package com.mingeso.backend.repositories;
import java.util.List;
import com.mingeso.backend.models.AdministrativoDiplomado;

public interface AdministrativoDiplomadoRepository {
    public int countAdministrativoDiplomados();
    public List<AdministrativoDiplomado> getAllAdministrativoDiplomados();
    public AdministrativoDiplomado createAdministrativoDiplomado(AdministrativoDiplomado administrativoDiplomado);
    public void updateAdministrativoDiplomado(AdministrativoDiplomado administrativoDiplomado);
    public void deleteAdministrativoDiplomado(Integer Id);
}