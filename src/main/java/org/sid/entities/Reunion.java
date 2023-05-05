package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReunion;
    private String nameReunion;
    @Enumerated(EnumType.STRING)
    private TypeReunion typeReunion;
    private int nbrePersoConvies;
    @ManyToOne
    private Salle salle;
}
