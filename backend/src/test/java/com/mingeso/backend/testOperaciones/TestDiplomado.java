package com.mingeso.backend.testOperaciones;

import static org.junit.Assert.assertEquals;

import com.mingeso.backend.models.Diplomado;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class TestDiplomado extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private final Gson gson;

    TestDiplomado(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    void testCreate() throws Exception {
        
		Diplomado diplomado = new Diplomado(); 
		diplomado.setNombre("diplomadoTest");
		diplomado.setObjetivo("objetivoTest");

        String inputJson = gson.toJson(diplomado);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/Diplomado")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
				
        String diplomadoResultado = mvcResult.getResponse().getContentAsString();
        Diplomado diplomadoFinal = gson.fromJson(diplomadoResultado, Diplomado.class);

        assertEquals("diplomadoTest", diplomadoFinal.getNombre());
        assertEquals("objetivoTest", diplomadoFinal.getObjetivo());

        Integer Id = diplomadoFinal.getId();
         
				mvcResult = mvc.perform(MockMvcRequestBuilders.delete("/DiplomadoDelete/" + Id)
				.contentType(MediaType.APPLICATION_JSON)
				.content(inputJson)).andReturn();
    }
}