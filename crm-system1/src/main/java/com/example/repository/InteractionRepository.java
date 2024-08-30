package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.model.Interaction;

public interface InteractionRepository extends JpaRepository<Interaction, String> {
}
