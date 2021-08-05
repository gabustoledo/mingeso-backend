package com.mingeso.backend.repositories;

import com.mingeso.backend.models.AdministrativoDiplomado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.sql2o.Connection;
import org.sql2o.Connection.close;
import org.sql2o.Sql2o;

import java.util.List;

@Repository
public class AdministrativoDiplomadoRepositoryImp implements AdministrativoDiplomadoRepository {

    @Autowired
    private Sql2o sql2o;

    @Override
    public int countAdministrativoDiplomados() {
        int total = 0;
        try(Connection conn = sql2o.open()){
            total = conn.createQuery("SELECT COUNT(*) FROM administrativo_diplomado").executeScalar(Integer.class);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        } 
        finally{
            conn.close();
        } 
        return total;
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
            System.out.println(e.getMessage());
            return null;
        } finally {
			conn.close();
        }
    }

    // READ
    @Override
    public List<AdministrativoDiplomado> getAllAdministrativoDiplomados() {
        try(Connection conn = sql2o.open()){
            return conn.createQuery("select * from administrativo_diplomado")
                    .executeAndFetch(AdministrativoDiplomado.class);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        } finally {
			conn.close();
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
            System.out.println(e.getMessage());
        } finally {
			conn.close();
        }
    }
    // DELETE
    @Override
    public void deleteAdministrativoDiplomado(@PathVariable Integer Id) {
        try(Connection conn = sql2o.open()){
            conn.createQuery("DELETE FROM administrativo_diplomado WHERE id = :id")
                    .addParameter("id", Id)
                    .executeUpdate();
            System.out.println("AdministrativoDiplomado eliminado");
        } catch(Exception e){
            System.out.println(e.getMessage());
        } finally {
			conn.close();
        } 
    }
}
