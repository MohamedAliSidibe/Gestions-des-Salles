package org.sid.dao;

import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import org.sid.entities.TypeReunion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReunionRepository extends JpaRepository<Reunion,Integer> {
    List<Reunion> findByTypeReunion(TypeReunion typeReunion);
}
