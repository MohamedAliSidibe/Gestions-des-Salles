package org.sid.web;

import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.TypeReunion;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;
import java.util.List;

@RequestMapping("/gestiondesalle")
public interface SalleRestController {

    @PostMapping("/addSalle")
    SalleDto ajouterSalle(@RequestBody SalleDto salleDto);
    @PostMapping("/addReunion")
    ReunionDto ajouterReunion(@RequestBody ReunionDto reunionDto);
    @GetMapping("/listReservation")
    List<ReservationDto> findAllReservation();
    @GetMapping("/listSalle")
    List<SalleDto> findAllSalle();
    @GetMapping("/listCollaborateur")
    List<CollaborateurDto> findAllCollab();
    @PostMapping("/reserver")
    Boolean reserver(@RequestBody ReservationDto reservationDto);
    @PostMapping("/optimale")
    @ResponseBody
    SalleDto salleOptimale(@RequestParam Integer nombrePerso,
                           @RequestParam TypeReunion typeReunion,
                           @RequestParam LocalTime  heureDebut);
    @DeleteMapping("/annulerReservation/{id}")
    void annulerReservation(@PathVariable Integer id);

}

