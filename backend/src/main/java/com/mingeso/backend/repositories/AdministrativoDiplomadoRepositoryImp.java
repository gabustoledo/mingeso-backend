package com.mingeso.backend.repositories;

import com.mingeso.backend.models.AdministrativoDiplomado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Collections;

@Repository
public class AdministrativoDiplomadoRepositoryImp implements AdministrativoDiplomadoRepository {

    @Autowired
    private Sql2o sql2o;

    // Create a Logger
    Logger logger = Logger.getLogger(AdministrativoDiplomadoRepositoryImp.class.getName());

    @Override
    public int countAdministrativoDiplomados() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM administrativo_diplomado").executeScalar(Integer.class);
            return total;
        }
    }
    
    // CREATE
    @Override
    public AdministrativoDiplomado createAdministrativoDiplomado(AdministrativoDiplomado administrativoDiplomado) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO administrativo_diplomado (id_administrativo, id_diplomado) VALUES (:idAdministrativo,:idDiplomado)", true)
                    .addParameter("idAdministrativo", administrativoDiplomado.getIdAdministrativo())
					.addParameter("idDiplomado", administrativoDiplomado.getIdDiplomado())
                    .executeUpdate().getKey();
					administrativoDiplomado.setId(insertedId);
            return administrativoDiplomado;
        } catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return null;
        } 
    }

    // READ
    @Override
    public List<AdministrativoDiplomado> getAllAdministrativoDiplomados() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from administrativo_diplomado")
                    .executeAndFetch(AdministrativoDiplomado.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // UPDATE
    @Override
    public void updateAdministrativoDiplomado(AdministrativoDiplomado administrativoDiplomado) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("Update administrativo_diplomado Set id_administrativo = :idAdministrativo, id_diplomado = :idDiplomado WHERE id = :id")
										.addParameter("id", administrativoDiplomado.getId())
										.addParameter("idAdministrativo", administrativoDiplomado.getIdAdministrativo())
										.addParameter("idDiplomado", administrativoDiplomado.getIdDiplomado())
                    .executeUpdate();
                   
        } catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        } 
    }
    // DELETE
    @Override
    public void deleteAdministrativoDiplomado(@PathVariable Integer id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM administrativo_diplomado WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
            logger.log(Level.WARNING, "AdministrativoDiplomado eliminado");
        } catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        } 
    }
}
