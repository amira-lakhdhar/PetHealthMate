package com.example.amiraweb.Services;

import com.example.amiraweb.Entity.Soin;
import com.example.amiraweb.Repository.SoinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SoinService implements ISoin {

    private final SoinRepository soinR;
    @Override
    public List<Soin> getAllSoins() {
        return soinR.findAll();
    }

    @Override
    public Soin getSoinById(Long id) {
        return soinR.findById(id).orElse(null);
    }

    @Override
    public Soin createSoin(Soin soin) {
        return soinR.save(soin);
    }

    @Override
    public Soin updateSoin(Long id, Soin soin) {
        if (soinR.existsById(id)) {
            soin.setId(id);
            return soinR.save(soin);
        }
        return null; // Gérer le cas où le soin n'existe pas.
    }
@Override
    public void deleteSoin(Long id) {
        soinR.deleteById(id);
    }
}



