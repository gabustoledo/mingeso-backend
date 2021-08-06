package com.mingeso.backend.testOperaciones;

import static org.junit.Assert.assertEquals;

import com.mingeso.backend.models.Operaciones;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestOperaciones extends AbstractTest {
    @Override
    @Before
    public void setUp() {
        super.setUp();
    }

    private final Gson gson;

    TestOperaciones(){
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    @Test
    public void sumaTest1() throws Exception {
        Operaciones suma = new Operaciones();
		suma.setPrimerNumero(10);
		suma.setSegundoNumero(15);

        String inputJson = gson.toJson(suma);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/operaciones/suma")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String sumaResultado = mvcResult.getResponse().getContentAsString();
        Operaciones sumaFinal = gson.fromJson(sumaResultado, Operaciones.class);

        assertEquals(25, sumaFinal.getResultado());
    }

    @Test
    public void sumaTest2() throws Exception {
        Operaciones suma = new Operaciones();
		suma.setPrimerNumero(100);
		suma.setSegundoNumero(200);

        String inputJson = gson.toJson(suma);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/operaciones/suma")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String sumaResultado = mvcResult.getResponse().getContentAsString();
        Operaciones sumaFinal = gson.fromJson(sumaResultado, Operaciones.class);

        assertEquals(300, sumaFinal.getResultado());
    }

    @Test
    public void restaTest1() throws Exception {
        Operaciones resta = new Operaciones();
		resta.setPrimerNumero(50);
		resta.setSegundoNumero(25);

        String inputJson = gson.toJson(resta);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/operaciones/resta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String restaResultado = mvcResult.getResponse().getContentAsString();
        Operaciones restaFinal = gson.fromJson(restaResultado, Operaciones.class);

        assertEquals(25, restaFinal.getResultado());
    }

    @Test
    public void restaTest2() throws Exception {
        Operaciones resta = new Operaciones();
		resta.setPrimerNumero(20);
		resta.setSegundoNumero(14);

        String inputJson = gson.toJson(resta);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/operaciones/resta")
                .contentType(MediaType.APPLICATION_JSON)
                .content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);

        String restaResultado = mvcResult.getResponse().getContentAsString();
        Operaciones restaFinal = gson.fromJson(restaResultado, Operaciones.class);

        assertEquals(6, restaFinal.getResultado());
    }
}