package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.model.Interaction;
import com.example.repository.InteractionRepository;

import java.util.List;

@Service
public class InteractionService {

    @Autowired
    private InteractionRepository interactionRepository;

    public List<Interaction> getAllInteractions() {
        return interactionRepository.findAll();
    }

    public Interaction addInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    public Interaction getInteraction(String id) {
        return interactionRepository.findById(id).orElse(null);
    }

    public Interaction updateInteraction(Interaction interaction) {
        return interactionRepository.save(interaction);
    }

    public void deleteInteraction(String id) {
        interactionRepository.deleteById(id);
    }
}
