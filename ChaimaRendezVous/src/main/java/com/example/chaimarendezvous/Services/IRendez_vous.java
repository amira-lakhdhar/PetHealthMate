package com.example.chaimarendezvous.Services;

import com.example.chaimarendezvous.Entity.Rendez_vous;

import java.util.List;

public interface IRendez_vous {
    List<Rendez_vous> getAllRendez_vous();

    Rendez_vous getRendez_vousbyId(Long id);

    Rendez_vous createRendez_vous(Rendez_vous rendez_vous);

    Rendez_vous updateRendez_vous(Long id, Rendez_vous rendez_vous);

    void deleteRendez_vous(Long id);
}
