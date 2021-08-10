package com.mingeso.backend.models;

public class Diplomado {

    private Integer id;
    private String nombre;
    private String objetivo;

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

		//Martes 10 agosto
    public String getObjetivo() {
			return objetivo;
		}
		public void setObjetivo(String objetivo) {
				this.objetivo = objetivo;
		}

}