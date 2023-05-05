package org.sid.services;
import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReunionDto;


import java.util.List;

public interface CollaborateurService {
    CollaborateurDto save (CollaborateurDto collaborateurDto);
    List<CollaborateurDto> findAll();
    CollaborateurDto findById(Integer id);
}
