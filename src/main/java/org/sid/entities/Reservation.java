package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReservation;
    private String jourDeReservation;
    private  LocalTime heureDebut;
    private LocalTime heureFin;
    private Boolean etatReservation;
    private Boolean etatNettoyage;

    @ManyToOne
    private Collaborateur collaborateur;
    @OneToOne
    private Salle salle;


}
