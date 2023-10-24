package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.Traitement;
import com.example.amiraweb.Repository.TraitementRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TraitementService implements ITraitement {


    private final TraitementRepository traitementR;


    @Override
    public List<Traitement> getAllTraitements() {
        return null;
    }

    @Override
    public Traitement getTraitementById(Long id) {
        return traitementR.findById(id).orElse(null);
    }

    @Override
    public Traitement createTraitement(Traitement traitement) {
        return null;
    }

    @Override
    public Traitement updateTraitement(Long id, Traitement traitement) {
        return null;
    }

    @Override
    public void deleteTraitement(Long id) {

    }
}