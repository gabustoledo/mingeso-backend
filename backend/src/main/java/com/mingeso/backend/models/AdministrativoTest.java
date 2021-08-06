package com.mingeso.backend.models;

public class AdministrativoTest {

    private Integer id;
    private String nombre;
    private String rut;
    private String correo;
    private String contrasena;
    private Integer rol;
    private Boolean activo;

    //Get y Set id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    //Get y Set nombre
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Get y Set rut
    public String getRut() {
        return rut;
    }
    public void setRut(String rut) {
        this.rut = rut;
    }

    //Get y Set correo
    public String getCorreo() {
        return correo;
    }
    public void setCorreo(String correo) {
        this.correo = correo;
    }

    //Get y Set pass
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //Get y Set rol
    public Integer getRol() {
        return rol;
    }
    public void setRol(Integer rol) {
        this.rol = rol;
    }

    //Get y Set id
    public Boolean getActivo() {
        return activo;
    }
    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
 
}