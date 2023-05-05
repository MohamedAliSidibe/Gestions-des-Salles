package org.sid.dao;

import org.sid.entities.Collaborateur;
import org.sid.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollaborateurRepository extends JpaRepository<Collaborateur,Integer> {
}
