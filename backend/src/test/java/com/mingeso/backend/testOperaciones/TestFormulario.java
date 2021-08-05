
package com.mingeso.backend.testOperaciones;

import static org.junit.Assert.assertEquals;

import com.mingeso.backend.models.Formulario;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

 class TestFormulario extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private final Gson gson;

    TestFormulario(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
     void testCreate() throws Exception {
        
		Formulario formulario = new Formulario(); 
		formulario.setNombre("NombreTest");
		formulario.setCorreo("CorreoTest");
		formulario.setStatus(1);
        formulario.setIdDiplomado(1);

        String inputJson = gson.toJson(formulario);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/Formulario")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
				
        String formularioResultado = mvcResult.getResponse().getContentAsString();
        Formulario formularioFinal = gson.fromJson(formularioResultado, Formulario.class);

		Integer uno = 1;
        assertEquals("NombreTest", formularioFinal.getNombre());
        assertEquals("CorreoTest", formularioFinal.getCorreo());
        assertEquals(uno, formularioFinal.getStatus());
        assertEquals(uno, formularioFinal.getIdDiplomado());

        Integer Id = formularioFinal.getId();
         
				mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/FormularioDelete/" + Id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
    }
}