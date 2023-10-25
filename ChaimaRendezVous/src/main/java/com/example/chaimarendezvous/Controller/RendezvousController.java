package com.example.chaimarendezvous.Controller;

import com.example.chaimarendezvous.Entity.Rendez_vous;
import com.example.chaimarendezvous.Services.Rendez_vousService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;



    @RestController
    @RequestMapping("/rendez-vous")
    @RequiredArgsConstructor
    public class RendezvousController {
        private final Rendez_vousService rendezvousS;


        @GetMapping("/")
        public List<Rendez_vous> getAllRendez_vous() {return rendezvousS.getAllRendez_vous();}

        @GetMapping("/{id}")
        public Rendez_vous getRendez_vousById(@PathVariable Long id) {return rendezvousS.getRendez_vousbyId (id); }

        @PostMapping("/")
        public Rendez_vous createRendez_vous(@RequestBody Rendez_vous rendez_vous) {return rendezvousS.createRendez_vous(rendez_vous);
        }

        @PutMapping("/{id}")
        public Rendez_vous updatRendez_vous(@PathVariable Long id, @RequestBody Rendez_vous rendez_vous) { return rendezvousS.updateRendez_vous(id, rendez_vous);
        }

        @DeleteMapping("/{id}")
        public void deleteRendez_vous(@PathVariable Long id) { rendezvousS.deleteRendez_vous(id);
        }

    }
