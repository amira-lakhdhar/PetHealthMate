package com.example.amiraweb.Controller;

import com.example.amiraweb.Entity.Soin;
import com.example.amiraweb.Services.SoinService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/soins")
@RequiredArgsConstructor
    public class SoinController {
        private final SoinService soinS;


        @GetMapping("/")
        public List<Soin> getAllSoins() {
            return soinS.getAllSoins();
        }

        @GetMapping("/{id}")
        public Soin getSoinById(@PathVariable Long id) {
            return soinS.getSoinById(id);
        }

        @PostMapping("/")
        public Soin createSoin(@RequestBody Soin soin) {
            return soinS.createSoin(soin);
        }

        @PutMapping("/{id}")
        public Soin updateSoin(@PathVariable Long id, @RequestBody Soin soin) {
            return soinS.updateSoin(id, soin);
        }

        @DeleteMapping("/{id}")
        public void deleteSoin(@PathVariable Long id) {
            soinS.deleteSoin(id);
        }

    }