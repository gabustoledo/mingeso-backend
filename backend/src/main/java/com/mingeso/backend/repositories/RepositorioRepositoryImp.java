package com.mingeso.backend.repositories;

import com.mingeso.backend.models.Repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;

@Repository
public class RepositorioRepositoryImp implements RepositorioRepository {

    @Autowired
    private Sql2o sql2o;

    // Create a Logger
    Logger logger = Logger.getLogger(RepositorioRepositoryImp.class.getName());

    @Override
    public int countRepositorios() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM repositorio").executeScalar(Integer.class);
        }
        return total;
    }
    
    // CREATE
    @Override
    public Repositorio createRepositorio(Repositorio repositorio) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO repositorio (correo, postulacion, status) values (:repositorioCorreo,:repositorioPostulacion,:repositorioStatus)", true)
                    .addParameter("repositorioCorreo", repositorio.getCorreo())
                    .addParameter("repositorioPostulacion", repositorio.getPostulacion())
                    .addParameter("repositorioStatus", repositorio.getStatus())
                    .executeUpdate().getKey();
            repositorio.setId(insertedId);
            return repositorio;        
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    // READ
    @Override
    public List<Repositorio> getAllRepositorios() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from repositorio")
                    .executeAndFetch(Repositorio.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // UPDATE
    @Override
    public void updateRepositorio(Repositorio repositorio) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("Update repositorio Set correo = :repositorioCorreo, Postulacion = :repositorioPostulacion, status = :repositorioStatus WHERE id = :repositorioId")
                    .addParameter("repositorioId", repositorio.getId())
                    .addParameter("repositorioCorreo", repositorio.getCorreo())
                    .addParameter("repositorioPostulacion", repositorio.getPostulacion())
                    .addParameter("repositorioStatus", repositorio.getStatus())
                    .executeUpdate();
                   
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        } 
    }

    // DELETE
    @Override
    public void deleteRepositorio(Integer id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM repositorio WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
                    logger.log(Level.WARNING, "Repositorio eliminado");
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        } 
    }
}
