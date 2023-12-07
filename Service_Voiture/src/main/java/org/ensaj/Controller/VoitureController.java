package org.ensaj.Controller;

import org.ensaj.Model.Client;
import org.ensaj.Model.Voiture;
import org.ensaj.Repository.VoitureRepository;
import org.ensaj.Service.VoitureService;
import org.ensaj.VoitureApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VoitureController {

    @Autowired
    VoitureRepository voitureRepository;

    @Autowired
    VoitureService voitureService;

    @Autowired
    VoitureApplication.ClientService clientService ;

    @GetMapping(value = "/voitures", produces = {"application/json"})
    public ResponseEntity<Object> chercherVoiture() {
        try {
            List<Voiture> voitures = voitureRepository.findAll();
            return ResponseEntity.ok(voitures);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error fetching voitures: " + e.getMessage());
        }
    }

    @GetMapping("/voitures/{Id}")
    public ResponseEntity<Object> chercherUneVoiture(@PathVariable Long Id) {
        try {
            Voiture voiture = voitureRepository.findById(Id)
                    .orElseThrow(() -> new Exception("Voiture Introuvable"));

            // Fetch the client details using the clientService
            voiture.setClient(clientService.clientById(voiture.getClientId()));

            return ResponseEntity.ok(voiture);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Voiture not found with ID: " + Id);
        }
    }

    @GetMapping("/voitures/client/{Id}")
    public ResponseEntity<List<Voiture>> chercherVoitureParClient(@PathVariable Long Id) {
        try {
            Client client = clientService.clientById(Id);
            if (client != null) {
                List<Voiture> voitures = voitureRepository.findByClientId(Id);
                return ResponseEntity.ok(voitures);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @PostMapping("/voitures/{clientId}")
    public ResponseEntity<Object> enregistrerUneVoiture(@PathVariable Long clientId, @RequestBody Voiture voiture) {
        try {
            // Fetch the client details using the clientService
            Client client = clientService.clientById(clientId);

            if (client != null) {
                // Set the fetched client in the voiture object
                voiture.setClient(client);

                // Save the Voiture with the associated Client
                voiture.setClientId(clientId);
                voiture.setClient(client);
                Voiture savedVoiture = voitureService.enregistrerVoiture(voiture);

                return ResponseEntity.ok(savedVoiture);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Client not found with ID: " + clientId);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error saving voiture: " + e.getMessage());
        }
    }


}
