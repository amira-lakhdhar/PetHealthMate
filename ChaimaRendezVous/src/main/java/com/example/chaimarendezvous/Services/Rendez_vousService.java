package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Rendez_vous;
import com.example.chaimarendezvous.Repository.RendezVousRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class Rendez_vousService implements IRendez_vous{



    private final RendezVousRepository Rendez_vous;


    @Override
    public List<Rendez_vous> getAllRendez_vous() {
        return null;
    }

    @Override
    public Rendez_vous getRendez_vousbyId(Long id){ return   Rendez_vous.findById(id).orElse(null);
    }

    @Override
    public Rendez_vous createRendez_vous(Rendez_vous rendez_vous) {return null;
    }




    @Override
    public Rendez_vous updateRendez_vous(Long id, Rendez_vous rendez_vous) {
        return null;
    }

    @Override
    public void deleteRendez_vous(Long id) {

    }

    // Implémentez les méthodes de l'interface DossierMedicalService ici.
}
