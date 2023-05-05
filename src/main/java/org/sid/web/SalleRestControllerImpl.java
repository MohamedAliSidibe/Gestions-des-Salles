package org.sid.web;

import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.TypeReunion;
import org.sid.services.CollaborateurService;
import org.sid.services.ReservationService;
import org.sid.services.ReunionService;
import org.sid.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static aj.org.objectweb.asm.Type.getType;

@RestController
public class SalleRestControllerImpl implements SalleRestController {

    @Autowired
    private SalleService salleService;
    @Autowired
    private ReunionService reunionService;
    @Autowired
    private ReservationService reservationService;
    @Autowired
    private CollaborateurService collaborateurService;

    @Override
    public SalleDto ajouterSalle(SalleDto salleDto) {
        return salleService.ajouter(salleDto);
    }

    @Override
    public ReunionDto ajouterReunion(ReunionDto reunionDto) {

        return reunionService.saveReunion(reunionDto);
    }

    @Override
    public List<ReservationDto> findAllReservation() {
        return reservationService.findAll();
    }

    @Override
    public List<SalleDto> findAllSalle() {
        return salleService.findAll();
    }

    @Override
    public List<CollaborateurDto> findAllCollab() {
        return collaborateurService.findAll();
    }



    private static final DateTimeFormatter HEURE_FORMAT = DateTimeFormatter.ofPattern("HH:mm");
    @Override
    public Boolean reserver(ReservationDto reservationDto) {
        // Vérification que les heures de début et de fin sont au format attendu
        LocalTime heureDebut = reservationDto.getHeureDebut();
        LocalTime heureFin = reservationDto.getHeureFin();
        if (heureDebut == null || heureFin == null) {
            throw new IllegalArgumentException("Les heures de début et de fin doivent être définies");
        }
        String heureDebutStr = heureDebut.format(HEURE_FORMAT);
        String heureFinStr = heureFin.format(HEURE_FORMAT);

        reservationDto.setHeureDebut(heureDebut);
        reservationDto.setHeureFin(heureFin);

        return reservationService.reserver(reservationDto);
    }


    @Override
    public SalleDto salleOptimale(Integer nombrePerso, TypeReunion typeReunion, LocalTime heureDebut) {
        return salleService.salleOptimale(nombrePerso, typeReunion, heureDebut);
    }

    @Override
    public void annulerReservation(Integer id) {
         reservationService.annulerReservation(id);
    }


}
