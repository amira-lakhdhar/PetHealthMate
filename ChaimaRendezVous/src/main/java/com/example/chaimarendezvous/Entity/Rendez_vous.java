package com.example.chaimarendezvous.Entity;

import javax.persistence.*;

import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity public class Rendez_vous implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dateRendezVous;

    @ManyToOne
    private Veterinaire veterinaire;

    @ManyToOne
    private Animal animal;

}
