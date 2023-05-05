package org.sid.dao;

import org.sid.entities.Reservation;
import org.sid.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalTime;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {
    List<Reservation> findBySalle(Salle salle);
    List<Reservation> findByHeureDebut(LocalTime heureDebut);
}
