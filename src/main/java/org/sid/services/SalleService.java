package org.sid.services;

import org.sid.dto.SalleDto;
import org.sid.entities.TypeReunion;

import java.time.LocalTime;
import java.util.List;

public interface SalleService {
    SalleDto ajouter(SalleDto salleDto);
    SalleDto findById(Integer id);
    List<SalleDto> findAll();
    SalleDto salleOptimale(Integer nombrePerso, TypeReunion typeReunion, LocalTime heureDebut);


}
