package org.sid.services;

import lombok.Data;
import org.sid.dao.CollaborateurRepository;
import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReunionDto;
import org.sid.entities.Collaborateur;
import org.sid.entities.Reunion;
import org.sid.exceptions.EntityNotFoundException;
import org.sid.exceptions.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CollaborateurServiceImpl implements CollaborateurService{


    @Autowired
    private  CollaborateurRepository collaborateurRepository;

    @Override
    public CollaborateurDto save(CollaborateurDto collaborateurDto) {
        if(Objects.isNull(collaborateurDto)) {
            new EntityNotFoundException("Pas de collaborateur"
                    , ErrorCodes.COLLABORATEUR_NOT_VALID);
        }

          return CollaborateurDto.fromEntity(
                  collaborateurRepository.save(
                    CollaborateurDto.toEntity( collaborateurDto)
          ));
    }

    @Override
    public List<CollaborateurDto> findAll() {
         return collaborateurRepository.findAll().stream()
                .map(CollaborateurDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public CollaborateurDto findById(Integer id) {
        Optional<Collaborateur> collaborateur=collaborateurRepository.findById(id);
        CollaborateurDto dto = null;
        if (!collaborateur.isEmpty()){
            dto=CollaborateurDto.fromEntity(collaborateur.get());
        }

        return Optional.of(dto).orElseThrow(
                ()->new EntityNotFoundException(
                        "cet collaborateur n'exite pas"
                        ,ErrorCodes.COLLABORATEUR_NOT_VALID));


    }
}
