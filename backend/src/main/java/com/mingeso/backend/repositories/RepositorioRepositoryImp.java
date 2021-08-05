package com.mingeso.backend.repositories;

import com.mingeso.backend.models.Repositorio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class RepositorioRepositoryImp implements RepositorioRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countRepositorios() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM repositorio").executeScalar(Integer.class);
        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }finally {
			conn.close();
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
            System.out.println(e.getMessage());
            return null;
        }finally {
			conn.close();
        }
    }

    // READ
    @Override
    public List<Repositorio> getAllRepositorios() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from repositorio")
                    .executeAndFetch(Repositorio.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
			conn.close();
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
            System.out.println(e.getMessage());
        } finally {
			conn.close();
        }
    }

    // DELETE
    @Override
    public void deleteRepositorio(Integer Id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM repositorio WHERE id = :id")
                    .addParameter("id", Id)
                    .executeUpdate();
            System.out.println("Repositorio eliminado");
        }catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
			conn.close();
        }
    }
}
