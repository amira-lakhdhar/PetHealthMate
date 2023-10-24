package com.example.amiraweb.Controller;

import com.example.amiraweb.Entity.Traitement;
import com.example.amiraweb.Services.TraitementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/traitements")
@RequiredArgsConstructor
public class TraitementController {

    private final TraitementService traitementS;
    @GetMapping("/")
    public List<Traitement> getAllTraitement() {
        return traitementS.getAllTraitements();
    }

    @GetMapping("/{id}")
    public Traitement getTraitementById(@PathVariable Long id){return traitementS.getTraitementById(id);}

    @PostMapping("/")
    public Traitement createTraitement(@RequestBody Traitement traitement) {return traitementS.createTraitement(traitement);}

    @PutMapping("/{id}")
    public Traitement updateTraitement(@PathVariable Long id, @RequestBody Traitement traitement) {
        return traitementS.updateTraitement(id, traitement);
    }

    @DeleteMapping("/{id}")
    public void deleteTraitement(@PathVariable Long id) {
        traitementS.deleteTraitement(id);
    }


}