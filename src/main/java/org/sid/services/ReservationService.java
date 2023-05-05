package org.sid.services;

import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Salle;

import java.util.List;

public interface ReservationService {
    ReservationDto save(ReservationDto reservationDto);
    ReservationDto findById(Integer id);
    List<ReservationDto> findAll();
    Boolean reserver(ReservationDto reservationDto);
     List<ReservationDto> findReservationsBySalle(SalleDto salleDto);

    void annulerReservation(Integer id);
    ReservationDto modifierReservation(Integer id,ReservationDto reservationDto);

}
