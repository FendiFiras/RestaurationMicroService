package org.esprit.menumicroservice.Entities;

import jakarta.persistence.*;

@Entity
public class Plat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlat;

    private String nom;

    private Double prix;

    private String description;

    @ManyToOne
    @JoinColumn(name = "menu_id")
    private Menu menu;

    // === Getters & Setters ===

    public Long getIdPlat() {
        return idPlat;
    }

    public void setIdPlat(Long idPlat) {
        this.idPlat = idPlat;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }
}
