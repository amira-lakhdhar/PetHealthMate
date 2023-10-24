package com.example.amiraweb.Entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class DossierMedical implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @OneToOne
    //private Animal animal;

    /*@OneToMany(mappedBy = "dossierMedical")
         private List<Soin> soins;*/

    // Ajoutez d'autres attributs, getters et setters selon vos besoins.

    // Constructeurs

    // Méthodes d'accès (getters et setters)

    // Autres méthodes métier

    // ...
}
