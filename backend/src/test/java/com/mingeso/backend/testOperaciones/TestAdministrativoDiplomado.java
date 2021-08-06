package com.mingeso.backend.testOperaciones;

import static org.junit.Assert.assertEquals;

import com.mingeso.backend.models.AdministrativoDiplomadoTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.json.*;

public class TestAdministrativoDiplomado extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private final Gson gson;

    TestAdministrativoDiplomado(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void testCreate() throws Exception {
        
			AdministrativoDiplomadoTest administrativoDiplomado = new AdministrativoDiplomadoTest(); 
			administrativoDiplomado.setIdDiplomado(1);
			administrativoDiplomado.setIdAdministrativo(1);

        String inputJson = gson.toJson(administrativoDiplomado);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/AdministrativoDiplomado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
				
        String administrativoResultado = mvcResult.getResponse().getContentAsString();
        AdministrativoDiplomadoTest administrativoFinal = gson.fromJson(administrativoResultado, AdministrativoDiplomadoTest.class);

				Integer uno = 1;
        assertEquals(uno, administrativoFinal.getIdDiplomado());
        assertEquals(uno, administrativoFinal.getIdAdministrativo());

        Integer Id = administrativoFinal.getId();

        
         
				mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/AdministrativoDiplomadoDelete/" + Id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
    }
}