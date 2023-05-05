package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Collaborateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCollab;
    private String nom;
    private String prenom;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "collaborateur")
    private Collection<Reservation> reservations;
}
