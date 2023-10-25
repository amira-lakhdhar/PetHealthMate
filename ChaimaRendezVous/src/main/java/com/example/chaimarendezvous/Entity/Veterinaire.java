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
public class Veterinaire implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String specialite;

    @OneToMany(mappedBy = "veterinaire")
    private List<Rendez_vous> rendezVous;
}
