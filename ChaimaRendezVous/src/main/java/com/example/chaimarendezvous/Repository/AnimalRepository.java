package com.example.chaimarendezvous.Repository;

import com.example.chaimarendezvous.Entity.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}