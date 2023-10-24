package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.Traitement;

import java.util.List;

public interface ITraitement {

    List<Traitement> getAllTraitements();

    Traitement getTraitementById(Long id);

    Traitement createTraitement(Traitement traitement);

    Traitement updateTraitement(Long id, Traitement traitement);

    void deleteTraitement(Long id);
}