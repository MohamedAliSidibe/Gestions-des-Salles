package org.sid.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Salle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSalle;
    private String nameSalle;
    private int nombrePlace;
    @ElementCollection
    private List<String> equipement;
    @JsonIgnore
    @OneToMany(mappedBy = "salle")
    private Collection<Reunion> reunions;
    @JsonIgnore
    @OneToOne(mappedBy = "salle")
    private Reservation reservation;

}
