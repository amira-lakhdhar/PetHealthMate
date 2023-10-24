package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.DossierMedical;

import java.util.List;

public interface IDossierMedical {

    List<DossierMedical> getAllDossiersMedicaux();

    DossierMedical getDossierMedicalById(Long id);

    DossierMedical createDossierMedical(DossierMedical dossierMedical);

    DossierMedical updateDossierMedical(Long id, DossierMedical dossierMedical);

    void deleteDossierMedical(Long id);
}
