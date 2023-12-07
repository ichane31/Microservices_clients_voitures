package com.ensaj.tp1_client.Repositories;

import com.ensaj.tp1_client.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire
}
