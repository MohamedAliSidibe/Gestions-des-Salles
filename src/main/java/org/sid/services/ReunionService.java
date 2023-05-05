package org.sid.services;
import org.sid.dto.ReunionDto;
import java.util.List;

public interface ReunionService {
    ReunionDto saveReunion(ReunionDto reunionDto);
    ReunionDto findById(Integer id);
    List<ReunionDto> findAll();
}
