package org.sid;
import java.time.LocalTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.sid.dao.ReservationRepository;
import org.sid.dto.ReservationDto;
import org.sid.dto.SalleDto;
import org.sid.entities.Reservation;
import org.sid.entities.TypeReunion;
import org.sid.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.*;

@SpringBootTest
class GestionnaireDesSallesApplicationTests {

		@Autowired
		private SalleService salleService;
		@Autowired
		private ReservationRepository reservationRepository;


	@Test
	public void testSalleOptimale() {
		int nombrePerso = 7;
		TypeReunion typeReunion = TypeReunion.VC;
		LocalTime heureDebut = LocalTime.of(8, 0);


		SalleDto salle = salleService.salleOptimale(nombrePerso, typeReunion, heureDebut);

		assertNotNull(salle);
		assertNotNull(salle.getIdSalle());
		assertTrue(salle.getNombrePlace() >= nombrePerso/0.7);
		assertNotNull(salle.getNameSalle());
		assertNotNull(salle.getEquipement());

	}




}
