package com.mingeso.backend.testOperaciones;

import static org.junit.Assert.assertEquals;

import com.mingeso.backend.models.AdministrativoTest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestAdministrativos extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private final Gson gson;

    TestAdministrativos(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void testCreate() throws Exception {
        
      AdministrativoTest administrativo = new AdministrativoTest(); 
		administrativo.setNombre("NombreTest");
		administrativo.setRut("RutTest");
		administrativo.setCorreo("CorreoTest");
		administrativo.setContrasena("PassTest");
		administrativo.setRol(1);
		administrativo.setActivo(true);

        String inputJson = gson.toJson(administrativo);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/Administrativo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String administrativoResultado = mvcResult.getResponse().getContentAsString();
        AdministrativoTest administrativoFinal = gson.fromJson(administrativoResultado, AdministrativoTest.class);

				Integer uno = 1;
        assertEquals("NombreTest", administrativoFinal.getNombre());
        assertEquals("RutTest", administrativoFinal.getRut());
        assertEquals("CorreoTest", administrativoFinal.getCorreo());
        assertEquals("PassTest", administrativoFinal.getContrasena());
				assertEquals(uno, administrativoFinal.getRol());
        assertEquals(true, administrativoFinal.getActivo());

        Integer Id = administrativoFinal.getId();
         
				mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/AdministrativoDelete/" + Id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
    }
}