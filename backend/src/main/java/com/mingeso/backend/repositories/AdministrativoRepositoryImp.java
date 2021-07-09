package com.mingeso.backend.repositories;

import com.mingeso.backend.models.Administrativo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class AdministrativoRepositoryImp implements AdministrativoRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countAdministrativos() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM administrativo").executeScalar(Integer.class);
        }
        return total;
    }
    
    // CREATE
    @Override
    public Administrativo createAdministrativo(Administrativo administrativo) {
        try(Connection conn = sql2o.open()){
            int insertedId = (int) conn.createQuery("INSERT INTO administrativo (nombre, rut, correo, pass, rol, activo) values (:administrativoNombre,:administrativoRut,:administrativoCorreo,:administrativoContrasena,:administrativoRol,:administrativoActivo)", true)
                    .addParameter("administrativoNombre", administrativo.getNombre())
                    .addParameter("administrativoRut", administrativo.getRut())
                    .addParameter("administrativoCorreo", administrativo.getCorreo())
                    .addParameter("administrativoContrasena", administrativo.getContrasena())
                    .addParameter("administrativoRol", administrativo.getRol())
                    .addParameter("administrativoActivo", administrativo.getActivo())
                    .executeUpdate().getKey();
            administrativo.setId(insertedId);
            return administrativo;        
        }catch(Exception e){
            System.out.println(e.getMessage());
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
            System.out.println(e.getMessage());
            return null;
        }
    }
    
    // UPDATE
    @Override
    public void updateAdministrativo(Administrativo administrativo) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("Update administrativo Set nombre = :administrativoNombre, rut = :administrativoRut, correo = :administrativoCorreo, pass = :administrativoContrasena, rol = :administrativoRol, activo = :administrativoActivo WHERE id = :Id")
                    .addParameter("administrativoNombre", administrativo.getNombre())
                    .addParameter("administrativoRut", administrativo.getRut())
                    .addParameter("administrativoCorreo", administrativo.getCorreo())
                    .addParameter("administrativoContrasena", administrativo.getContrasena())
                    .addParameter("administrativoRol", administrativo.getRol())
                    .addParameter("administrativoActivo", administrativo.getActivo())
                    .addParameter("Id", administrativo.getId())
                    .executeUpdate();
                   
        }catch(Exception e){
            System.out.println(e.getMessage());
        }  
    }

    // DELETE
    @Override
    public void deleteAdministrativo(Integer Id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM administrativo WHERE id = :id")
                    .addParameter("id", Id)
                    .executeUpdate();
            System.out.println("Administrativo eliminada");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }  
    }

    // LOGIN
    @Override
    public List<Administrativo> login(Administrativo administrativo) {
        int existe = 0;
        try(Connection conn = sql2o.open()){
            existe = conn.createQuery("select count(*) from administrativo where correo=:administrativoCorreo and pass=:administrativoPass;")
                .addParameter("administrativoCorreo", administrativo.getCorreo())
                .addParameter("administrativoPass", administrativo.getContrasena())
                .executeScalar(Integer.class);

            if(existe == 1){
                return conn.createQuery("select * from administrativo where correo=:administrativoCorreo;")
                    .addParameter("administrativoCorreo", administrativo.getCorreo())
                    .executeAndFetch(Administrativo.class);
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
