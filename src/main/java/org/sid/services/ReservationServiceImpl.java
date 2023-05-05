package org.sid.services;

import org.sid.dao.ReservationRepository;
import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Reservation;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import org.sid.exceptions.EntityNotFoundException;
import org.sid.exceptions.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService{

    private ReservationRepository reservationRepository;
    private SalleService salleService;
    @Autowired
    public ReservationServiceImpl(ReservationRepository reservationRepository,SalleService salleService) {
        this.reservationRepository = reservationRepository;
        this.salleService=salleService;
    }

    @Override
    public ReservationDto save(ReservationDto reservationDto) {

        return ReservationDto.fromEntity(
                reservationRepository.save(
                        ReservationDto.toEntity(reservationDto)
                ));
    }

    @Override
    public ReservationDto findById(Integer id) {
        Optional<Reservation> reservation=reservationRepository.findById(id);
        ReservationDto dto = null;
        if (!reservation.isEmpty()){
            dto=ReservationDto.fromEntity(reservation.get());
        }

        return Optional.of(dto).orElseThrow(
                ()->new EntityNotFoundException(
                        "cette reservation n'est pas dispo"
                        ,ErrorCodes.RESERVATION_NOT_FOUND));

    }

    @Override
    public List<ReservationDto> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());

    }
    @Override
    public List<ReservationDto> findReservationsBySalle(SalleDto salleDto) {
        return reservationRepository.findBySalle(SalleDto.toEntity(salleDto)).stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public Boolean reserver(ReservationDto reservationDto) {
        // Verification de la durée de reservation
        Duration duree = Duration.between(reservationDto.getHeureDebut(), reservationDto.getHeureFin());
        if (!duree.equals(Duration.ofHours(1))) {
            throw new IllegalArgumentException("La durée de la réservation doit être supérieure à une heure");
        }

        // Verification de la Disponibilite de la salle
        List<ReservationDto> reservations = findAll();
        for (ReservationDto reservation : reservations) {
            if (reservation.getHeureFin().equals(reservationDto.getHeureDebut().plusHours(-1))) {
               //La salle n'est pas disponible à cette heure
                continue;
            }
            if (reservation.getHeureFin().isAfter(reservationDto.getHeureDebut()) ||
                    reservation.getHeureFin().plusHours(1).isAfter(reservationDto.getHeureDebut())) {
                System.out.println("La salle est en nettoyage");
                return false;
                // La salle est en état de nettoyage .
            }
        }

        //Enregistrer La réservation.
        reservationDto.setEtatReservation(true);
        reservationDto.setEtatNettoyage(false);
        save(reservationDto);
        return true;
    }




    @Override
    public void annulerReservation(Integer id) {
        if(Objects.isNull(id)){
            new EntityNotFoundException("Annulation echoué"
                    , ErrorCodes.RESERVATION_NOT_FOUND);
        }
        else {
            reservationRepository.deleteById(id);
            System.out.println("Votre Reservation a été annulé");
        }


    }

    @Override
    public ReservationDto modifierReservation(Integer id, ReservationDto reservationDto) {
        ReservationDto dto = null;
        Optional<Reservation> reservation = reservationRepository.findById(id);
        if (!reservation.isEmpty()){
            dto=ReservationDto.fromEntity(reservation.get());

        }
        Reservation reservations = reservation.get();
        // Update the reservation entity with the values from reservationDto
        reservations.setJourDeReservation(reservations.getJourDeReservation());
        reservations.setHeureDebut(reservationDto.getHeureDebut());
        reservations.setHeureFin(reservations.getHeureFin());
        reservations.setEtatReservation(reservationDto.getEtatReservation());
        reservations.setEtatNettoyage(reservationDto.getEtatNettoyage());
        reservations.getCollaborateur();
        reservations.getSalle();

        return ReservationDto.fromEntity(reservationRepository.save
                (reservations)) ;
    }
}
