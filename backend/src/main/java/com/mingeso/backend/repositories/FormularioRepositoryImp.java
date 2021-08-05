package com.mingeso.backend.repositories;

import com.mingeso.backend.models.Formulario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Collections;

@Repository
public class FormularioRepositoryImp implements FormularioRepository {

    @Autowired
    private Sql2o sql2o;

    // Create a Logger
    Logger logger = Logger.getLogger(FormularioRepositoryImp.class.getName());

    @Override
    public int countFormularios() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM formulario").executeScalar(Integer.class);
        }
        return total;
    }
    
    // CREATE
    @Override
    public Formulario createFormulario(Formulario formulario) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO formulario (nombre, correo, status, id_diplomado) values (:formularioNombre,:formularioCorreo,:formularioStatus,:formularioIdDiplomado)", true)
                    .addParameter("formularioNombre", formulario.getNombre())
                    .addParameter("formularioCorreo", formulario.getCorreo())
                    .addParameter("formularioStatus", formulario.getStatus())
                    .addParameter("formularioIdDiplomado", formulario.getIdDiplomado())
                    .executeUpdate().getKey();
            formulario.setId(insertedId);
            return formulario;        
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return null;
        } 
    }

    // READ
    @Override
    public List<Formulario> getAllFormularios() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from formulario")
                    .executeAndFetch(Formulario.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // UPDATE
    @Override
    public void updateFormulario(Formulario formulario) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("Update formulario Set nombre = :formularioNombre, correo = :formularioCorreo, status = :formularioStatus, id_diplomado = :formularioIdDiplomado WHERE id = :formularioId")
                    .addParameter("formularioId", formulario.getId())
                    .addParameter("formularioNombre", formulario.getNombre())
                    .addParameter("formularioCorreo", formulario.getCorreo())
                    .addParameter("formularioStatus", formulario.getStatus())
                    .addParameter("formularioIdDiplomado", formulario.getIdDiplomado())
                    .executeUpdate();
                   
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        }  
    }

    // DELETE
    @Override
    public void deleteFormulario(Integer id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM formulario WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
            logger.log(Level.WARNING, "Formulario eliminado");
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        }  
    }
}
