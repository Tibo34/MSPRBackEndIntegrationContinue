package com.mspr.integrationContinue.integrationContinue.Controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mspr.integrationContinue.integrationContinue.Entity.Produit;
import com.mspr.integrationContinue.integrationContinue.IntegrationContinueApplication;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = IntegrationContinueApplication.class)
@WebMvcTest(ProduitController.class)
class ProduitControllerTest {

    private Produit produit;
    private int id=1;
    private String nom="carte gold";
    private Collection<Produit> produits;

    @LocalServerPort
    private int port;
    @Autowired
    protected MockMvc mvc;
    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private ProduitController produitController;

    @BeforeEach
    public void setUp() {
        produit=new Produit();
        produit.setId(id);
        produit.setNom(nom);
        produits=new ArrayList<>();
        produits.add(produit);
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getList() {
        String uri="http://localhost:9000/Produits";
        when(produitController.getList()).thenReturn(produits);
        try {
            MvcResult  mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            Produit[] productlist = mapFromJson(content, Produit[].class);
            assertTrue(productlist.length > 0);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void afficherProduit() {
        String uri="http://localhost:9000/Produits/1";
        when(produitController.afficherProduit(1)).thenReturn(produit);
        MvcResult mvcResult = null;
        try {
            mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
                    .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
            int status = mvcResult.getResponse().getStatus();
            assertEquals(200, status);
            String content = mvcResult.getResponse().getContentAsString();
            Produit prod = mapFromJson(content, Produit.class);
            assertEquals(prod.getId(),id);
            assertEquals(prod.getNom(),nom);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void save() {
    }

    @Test
    public void delete() {
    }


    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }
    protected <T> T mapFromJson(String json, Class<T> clazz)
            throws JsonParseException, JsonMappingException, IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}