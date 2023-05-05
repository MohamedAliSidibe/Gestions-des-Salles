package org.sid.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sid.entities.Reservation;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;
import java.util.stream.DoubleStream;


@Data
@Builder
public class ReservationDto {

    private Integer idReservation;
    @NotNull
    private String jourDeReservation;
    @NotNull
    private  LocalTime heureDebut;
    @NotNull
    private LocalTime heureFin;
    @NotNull
    private Boolean etatReservation;
    @NotNull
    private Boolean etatNettoyage;
    @NotNull
    private CollaborateurDto collaborateur;
    @NotNull
    private SalleDto salle;

    public static ReservationDto fromEntity(Reservation reservation){
        if(Objects.isNull(reservation)){
            return  null;
        }
        return ReservationDto.builder()
                .idReservation(reservation.getIdReservation())
                .jourDeReservation(reservation.getJourDeReservation())
                .heureDebut(reservation.getHeureDebut())
                .heureFin(reservation.getHeureFin())
                .etatReservation(reservation.getEtatReservation())
                .etatNettoyage(reservation.getEtatNettoyage())
                .collaborateur(CollaborateurDto.fromEntity(reservation.getCollaborateur()))
                .salle(SalleDto.fromEntity(reservation.getSalle()))
                .build();
    }
    public  static Reservation toEntity(ReservationDto reservationDto ){
        if(Objects.isNull(reservationDto)){
            return null;
        }
        Reservation reservation=new Reservation();

        reservation.setIdReservation(reservationDto.getIdReservation());
        reservation.setJourDeReservation(reservationDto.getJourDeReservation());
        reservation.setHeureDebut(reservationDto.getHeureDebut());
        reservation.setHeureFin(reservationDto.getHeureFin());
        reservation.setEtatReservation(reservationDto.getEtatReservation());
        reservation.setEtatNettoyage(reservationDto.getEtatNettoyage());
        reservation.setSalle(SalleDto.toEntity(reservationDto.getSalle()));
        reservation.setCollaborateur(CollaborateurDto.toEntity(reservationDto.getCollaborateur()));
        return reservation;

    }



}
