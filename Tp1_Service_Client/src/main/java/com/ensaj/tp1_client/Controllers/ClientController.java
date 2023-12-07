package com.ensaj.tp1_client.Controllers;

import com.ensaj.tp1_client.Models.Client;
import com.ensaj.tp1_client.Repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private  ClientRepository clientRepository;

    @PostMapping("/")
    public ResponseEntity<Client> enregistrerUnClient(@RequestBody Client client) {
        Client savedClient = clientRepository.save(client);

        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Client>> chercherClients() {
        List<Client> clients = clientRepository.findAll();

        if (clients.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(clients, HttpStatus.OK);
        }
    }


    @GetMapping("/client/{id}")
    public ResponseEntity<Client> chercherUnClient(@PathVariable Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        return optionalClient.map(client -> new ResponseEntity<>(client, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<Client> modifierClient(@PathVariable Long id, @RequestBody Client updatedClient) {
        Optional<Client> optionalClient = clientRepository.findById(id);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();

            // Update the existing Client with the new details
            if (updatedClient.getNom() != null) {
                existingClient.setNom(updatedClient.getNom());
            }

            if (updatedClient.getPrenom() != null ) {
                existingClient.setPrenom(updatedClient.getPrenom());
            }

            if (updatedClient.getAge() != 0) {
                existingClient.setAge(updatedClient.getAge());
            }

            // Save the updated Client
            Client savedClient = clientRepository.save(existingClient);

            return new ResponseEntity<>(savedClient, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
