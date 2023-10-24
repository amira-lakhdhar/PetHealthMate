package com.example.amiraweb.Controller;

import com.example.amiraweb.Entity.DossierMedical;
import com.example.amiraweb.Services.DossierMedicalService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//http://localhost:8088/pethealth/amiraweb/dossiersmedicaux/
@RestController
@RequestMapping("/dossiersmedicaux")
@AllArgsConstructor
public class DossierMedicalController {

    private final DossierMedicalService dossierM;

    @GetMapping("/")
    public List<DossierMedical> getAllDossierMedical() {return dossierM.getAllDossiersMedicaux();}

    @GetMapping("/{id}")
    public DossierMedical getAllDossierMedicalById(@PathVariable Long id) {
        return dossierM.getDossierMedicalById(id);
    }

    @PostMapping("/")
    public DossierMedical createDossierMedical(@RequestBody DossierMedical dossierMedical) {return dossierM.createDossierMedical(dossierMedical);
    }

    @PutMapping("/{id}")
    public DossierMedical updateDossierMedical(@PathVariable Long id, @RequestBody DossierMedical dossierMedical) {
        return dossierM.updateDossierMedical(id, dossierMedical);
    }

    @DeleteMapping("/{id}")
    public void deleteSoin(@PathVariable Long id) {
        dossierM.deleteDossierMedical(id);
    }



}
