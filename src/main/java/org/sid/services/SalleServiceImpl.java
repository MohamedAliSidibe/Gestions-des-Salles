package org.sid.services;

import lombok.Data;
import org.sid.dao.ReservationRepository;
import org.sid.dao.ReunionRepository;
import org.sid.dao.SalleRepository;
import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Reservation;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import org.sid.entities.TypeReunion;
import org.sid.exceptions.EntityNotFoundException;
import org.sid.exceptions.ErrorCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Data
@Service
public class SalleServiceImpl implements SalleService{

    private SalleRepository salleRepository;
    private ReunionRepository reunionRepository;
    private ReservationRepository reservationRepository;

    @Autowired
    public SalleServiceImpl(SalleRepository salleRepository,
                            ReunionRepository reunionRepository,
                            ReservationRepository reservationRepository) {
        this.salleRepository = salleRepository;
        this.reunionRepository=reunionRepository;
        this.reservationRepository=reservationRepository;
    }


    @Override
    public SalleDto ajouter(SalleDto salleDto) {
        if(Objects.isNull(salleDto)){
            new EntityNotFoundException("Aucune Salle n'est trouvé"
                    , ErrorCodes.SALLE_NOT_FOUND);
        }
        return SalleDto.fromEntity(
                salleRepository.save(SalleDto.toEntity(salleDto)));
    }

    @Override
    public SalleDto findById(Integer id) {
        Optional<Salle> salle=salleRepository.findById(id);
        SalleDto dto = null;
        if (!salle.isEmpty()){
            dto=SalleDto.fromEntity(salle.get());
        }

        return Optional.of(dto).orElseThrow(
                ()->new EntityNotFoundException(
                        "cette salle n'est pas dispo"
                        ,ErrorCodes.SALLE_NOT_FOUND));
    }

    @Override
    public List<SalleDto> findAll() {
        return salleRepository.findAll().stream()
                .map(SalleDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public SalleDto salleOptimale(Integer nombrePerso, TypeReunion typeReunion, LocalTime heureDebut) {
        List<ReunionDto> listeReunionDto = reunionRepository.findByTypeReunion(typeReunion)
                .stream()
                .filter(reunion -> reunion.getSalle().getNombrePlace() >= Math.round(nombrePerso / 0.7))
                .map(ReunionDto::fromEntity)
                .collect(Collectors.toList());

        List<ReservationDto> reservations = reservationRepository.findByHeureDebut(heureDebut)
                .stream()
                .map(ReservationDto::fromEntity)
                .collect(Collectors.toList());

        List<SalleDto> sallesDisponibles = new ArrayList<>();
        for (ReunionDto reunionDto : listeReunionDto) {
            if (reservations.stream().noneMatch(reservationDto ->
                    reservationDto.getSalle().getIdSalle().equals(reunionDto.getSalle().getIdSalle()))) {
                sallesDisponibles.add(reunionDto.getSalle());
            }
        }

        List<SalleDto> sallesOptimales = sallesDisponibles.stream()
                .sorted(Comparator.comparingDouble(salle -> Math.abs(salle.getNombrePlace() - nombrePerso / 0.7)))
                .collect(Collectors.toList());

        if (sallesOptimales.size() > 1) {
            System.out.println("Vous avez " + sallesOptimales.size() + " salles optimales, mais je vous fourni une aléatoire");
            Random random = new Random();
            int index = random.nextInt(sallesOptimales.size());
            System.out.println("Votre salle choisie est " + sallesOptimales.get(index).getNameSalle());
            return sallesOptimales.get(index);
        } else if (sallesOptimales.size() == 1) {
            System.out.println("La salle optimale est " + sallesOptimales.get(0).getNameSalle());
            return sallesOptimales.get(0);
        } else {
            System.out.println("Aucune salle disponible pour cette réunion.");
            return null;
        }
    }


}
