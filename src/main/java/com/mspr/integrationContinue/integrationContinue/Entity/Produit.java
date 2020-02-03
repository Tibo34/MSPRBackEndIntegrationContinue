package com.mspr.integrationContinue.integrationContinue.Entity;

import javax.persistence.*;

@Entity
@Table(name = "Produit")
public class Produit {

    private int id;
    private String nom;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "nom",nullable = true,length = 50, unique = true)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

}
