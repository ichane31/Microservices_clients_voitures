package com.ensaj.tp1_client.Models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private int age;

    // Lombok générera les constructeurs, getters, setters, equals, hashCode, et toString.

    // Constructeur par défaut nécessaire pour JPA
    public Client() {
    }

    // Constructeur avec nom et prénom
    public Client(String nom, String prenom , int age) {
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
    }
}
