package com.mingeso.backend.repositories;

import com.mingeso.backend.models.Administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.Collections;

@Repository
public class AdministrativoRepositoryImp implements AdministrativoRepository {

    @Autowired
    private Sql2o sql2o;

    // Create a Logger
    Logger logger = Logger.getLogger(AdministrativoRepositoryImp.class.getName());

    @Override
    public int countAdministrativos() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM administrativo").executeScalar(Integer.class);
        }
        return total;
    }

    //critical smell
    private static final String ACTION_1 = "administrativoCorreo";
    // CREATE
    @Override
    public Administrativo createAdministrativo(Administrativo administrativo) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO administrativo (nombre, rut, correo, pass, rol, activo) values (:administrativoNombre,:administrativoRut,:administrativoCorreo,:administrativoContrasena,:administrativoRol,:administrativoActivo)", true)
                    .addParameter("administrativoNombre", administrativo.getNombre())
                    .addParameter("administrativoRut", administrativo.getRut())
                    .addParameter(ACTION_1, administrativo.getCorreo())
                    .addParameter("administrativoContrasena", administrativo.getContrasena())
                    .addParameter("administrativoRol", administrativo.getRol())
                    .addParameter("administrativoActivo", true)
                    .executeUpdate().getKey();
            administrativo.setId(insertedId);
            return administrativo;        
        } catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
            return null;
        }
    }

    // READ
    @Override
    public List<Administrativo> getAllAdministrativos() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from administrativo")
                    .executeAndFetch(Administrativo.class);
        } catch (Exception e) {
            logger.log(Level.WARNING, e.getMessage());
            return Collections.emptyList();
        }
    }
    
    // UPDATE
    @Override
    public void updateAdministrativo(Administrativo administrativo) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("Update administrativo Set nombre = :administrativoNombre, rut = :administrativoRut, correo = :administrativoCorreo, pass = :administrativoContrasena, rol = :administrativoRol, activo = :administrativoActivo WHERE id = :Id")
                    .addParameter("administrativoNombre", administrativo.getNombre())
                    .addParameter("administrativoRut", administrativo.getRut())
                    .addParameter(ACTION_1, administrativo.getCorreo())
                    .addParameter("administrativoContrasena", administrativo.getContrasena())
                    .addParameter("administrativoRol", administrativo.getRol())
                    .addParameter("administrativoActivo", administrativo.getActivo())
                    .addParameter("Id", administrativo.getId())
                    .executeUpdate();
                   
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    // DELETE
    @Override
    public void deleteAdministrativo(Integer id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM administrativo WHERE id = :id")
                    .addParameter("id", id)
                    .executeUpdate();
            logger.log(Level.WARNING, "Administrativo eliminada");
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        }
    }

    // LOGIN
    @Override
    public List<Administrativo> login(Administrativo administrativo) {
        int existe = 0;
        try(Connection conn = sql2o.open()){
            existe = conn.createQuery("select count(*) from administrativo where correo=:administrativoCorreo and pass=:administrativoPass;")
                .addParameter(ACTION_1, administrativo.getCorreo())
                .addParameter("administrativoPass", administrativo.getContrasena())
                .executeScalar(Integer.class);

            if(existe == 1){
                return conn.createQuery("select * from administrativo where correo=:administrativoCorreo;")
                    .addParameter(ACTION_1, administrativo.getCorreo())
                    .executeAndFetch(Administrativo.class);
            }
        }catch(Exception e){
            logger.log(Level.WARNING, e.getMessage());
        }
        return Collections.emptyList();
    }
}
