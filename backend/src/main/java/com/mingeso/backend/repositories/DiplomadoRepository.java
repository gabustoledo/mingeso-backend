package com.mingeso.backend.repositories;
import java.util.List;
import com.mingeso.backend.models.Diplomado;

public interface DiplomadoRepository {
    public int countDiplomados();
    public List<Diplomado> getAllDiplomados();
    public Diplomado createDiplomado(Diplomado diplomado);
    public void updateDiplomado(Diplomado diplomado);
    public void deleteDiplomado(Integer id);
}