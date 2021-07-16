package com.mingeso.backend.models;

public class Repositorio {

    private Integer id;
    private String correo;
    private String postulacion;
    private Integer status;

    //Get y Set id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Get y Set id
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //Get y Set id
    public String getPostulacion() {
        return postulacion;
    }
    public void setPostulacion(String postulacion) {
        this.postulacion = postulacion;
    }

    //Get y Set id
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
}