package org.ensaj.Repository;

import org.ensaj.Model.Voiture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoitureRepository extends JpaRepository<Voiture, Long> {

//    @Query("SELECT v FROM Voiture v WHERE v.client.id = :clientId")
    List<Voiture> findByClientId(Long id);
}
