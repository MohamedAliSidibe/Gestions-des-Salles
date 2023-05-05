package org.sid.services;

import org.sid.dao.ReunionRepository;
import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import org.sid.exceptions.EntityNotFoundException;
import org.sid.exceptions.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReunionServiceImpl implements ReunionService{

    private ReunionRepository reunionRepository;

    @Autowired
    public ReunionServiceImpl(ReunionRepository reunionRepository) {
        this.reunionRepository = reunionRepository;
    }



    @Override
    public ReunionDto saveReunion(ReunionDto reunionDto) {

        if(Objects.isNull(reunionDto)) {
            new EntityNotFoundException("Pas de Reunion"
                    , ErrorCodes.REUNION_NOT_VALID);
        }

        return ReunionDto.fromEntity(
                reunionRepository.save(
                        ReunionDto.toEntity(reunionDto)
                ));
    }

    @Override
    public ReunionDto findById(Integer id) {

        Optional<Reunion> reunion=reunionRepository.findById(id);
        ReunionDto dto = null;
        if (!reunion.isEmpty()){
            dto=ReunionDto.fromEntity(reunion.get());
        }

        return Optional.of(dto).orElseThrow(
                ()->new EntityNotFoundException(
                        "cette reunion n'est pas dispo"
                        ,ErrorCodes.REUNION_NOT_VALID));

    }

    @Override
    public List<ReunionDto> findAll() {

        return reunionRepository.findAll().stream()
                .map(ReunionDto::fromEntity)
                .collect(Collectors.toList());
    }
}
