package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.DossierMedical;
import com.example.amiraweb.Repository.DossierMedicalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DossierMedicalService implements IDossierMedical {
    private final DossierMedicalRepository dossierMedicalR;


    @Override
    public List<DossierMedical> getAllDossiersMedicaux() {
        return null;
    }

    @Override
    public DossierMedical getDossierMedicalById(Long id){
     return   dossierMedicalR.findById(id).orElse(null);
    }


    @Override
    public DossierMedical createDossierMedical(DossierMedical dossierMedical) {
        return null;
    }

    @Override
    public DossierMedical updateDossierMedical(Long id, DossierMedical dossierMedical) {
        return null;
    }

    @Override
    public void deleteDossierMedical(Long id) {

    }

    // Implémentez les méthodes de l'interface DossierMedicalService ici.
}
