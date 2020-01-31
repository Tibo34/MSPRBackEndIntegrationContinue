package com.mspr.integrationContinue.integrationContinue.Entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProduitTest {

    private Produit produit;
    private int ID =1;
    private String NOM ="carte gold";


    public ProduitTest() {
        this.produit = new Produit();
        produit.setId(ID);
        produit.setNom(NOM);
    }

    @Test
    void getId() {
        int id=produit.getId();
        assertEquals(id,ID);
    }

    @Test
    void setId() {
        int newId=2;
        produit.setId(newId);
        assertEquals(produit.getId(),newId);
    }

    @Test
    void getNom() {
        String nom=produit.getNom();
        assertEquals(nom,NOM);
    }

    @Test
    void setNom() {
        String newNom="carte platine";
        produit.setNom(newNom);
        assertEquals(newNom,produit.getNom());
    }
}