package com.mingeso.backend.models;

public class Formulario {

    private Integer id;
    private String nombre;
    private String correo;
    private Integer status;
    private Integer id_diplomado;

    //Get y Set id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Get y Set id
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Get y Set id
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //Get y Set id
    public Integer getStatus() {
        return status;
    }
    public void setStatus(Integer status) {
        this.status = status;
    }
    
    //Get y Set id
    public Integer getIdDiplomado() {
        return id_diplomado;
    }
    public void setIdDiplomado(Integer idDiplomado) {
        this.id_diplomado = idDiplomado;
    }
}