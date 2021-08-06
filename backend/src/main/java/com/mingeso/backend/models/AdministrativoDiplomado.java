package com.mingeso.backend.models;

public class AdministrativoDiplomado {

    private Integer id;
    private Integer id_diplomado;
	private Integer id_administrativo;

    //Get y Set id
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    
    //Get y Set idDiplomado
    public Integer getIdDiplomado() {
        return id_diplomado;
    }
    public void setIdDiplomado(Integer idDiplomado) {
        this.id_diplomado = idDiplomado;
    }

		//Get y Set Administrativo
    public Integer getIdAdministrativo() {
			return id_administrativo;
	}
	public void setIdAdministrativo(Integer idAdministrativo) {
			this.id_administrativo = idAdministrativo;
	}
}