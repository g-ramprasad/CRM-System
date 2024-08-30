package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.model.Interaction;
import com.example.service.InteractionService;

import java.util.List;

@RestController
@RequestMapping("/api/interactions")
public class InteractionController {

    @Autowired
    private InteractionService interactionService;

    @GetMapping
    public ResponseEntity<List<Interaction>> getAllInteractions() {
        List<Interaction> interactions = interactionService.getAllInteractions();
        return new ResponseEntity<List<Interaction>>(interactions, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Interaction> addInteraction(@RequestBody Interaction interaction) {
        Interaction createdInteraction = interactionService.addInteraction(interaction);
        return new ResponseEntity<Interaction>(createdInteraction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInteraction(@PathVariable String id) {
        Interaction interaction = interactionService.getInteraction(id);
        if (interaction != null) {
            return new ResponseEntity<Interaction>(interaction, HttpStatus.OK);
        } else {
        	return new ResponseEntity<String>("Interaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInteraction(@PathVariable String id, @RequestBody Interaction interaction) {
        if (interactionService.getInteraction(id) != null) {
            interaction.setId(id);
            Interaction updatedInteraction = interactionService.updateInteraction(interaction);
            return new ResponseEntity<Interaction>(updatedInteraction, HttpStatus.OK);
        } else {
        	return new ResponseEntity<String>("Cannot update. Interaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInteraction(@PathVariable String id) {
        if (interactionService.getInteraction(id) != null) {
            interactionService.deleteInteraction(id);
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        } else {
        	return new ResponseEntity<String>("Cannot delete. Interaction with ID " + id + " not found", HttpStatus.NOT_FOUND);
        }
    }
}
