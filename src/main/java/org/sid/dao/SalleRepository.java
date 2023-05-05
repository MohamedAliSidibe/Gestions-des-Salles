package org.sid.dao;

import org.sid.dto.SalleDto;
import org.sid.entities.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalleRepository extends JpaRepository<Salle,Integer> {

}
