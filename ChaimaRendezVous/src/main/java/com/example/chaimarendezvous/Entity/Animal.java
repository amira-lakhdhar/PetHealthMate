package com.example.chaimarendezvous.Entity;

import javax.persistence.*;

import lombok.*;
import java.io.Serializable;
import java.util.List;
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Animal implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String espece;
    private String race;

  //  @OneToMany(mappedBy = "animal")

   //private List<com.example.web_distribue.Entity.Rendez_vous> rendezVous;
    // Ajoutez d'autres attributs, getters et setters selon vos besoins.

    //
}

