package org.sid;


import org.sid.dto.CollaborateurDto;
import org.sid.dto.ReservationDto;
import org.sid.dto.ReunionDto;
import org.sid.dto.SalleDto;
import org.sid.entities.*;
import org.sid.services.CollaborateurService;
import org.sid.services.ReservationService;
import org.sid.services.ReunionService;
import org.sid.services.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class GestionnaireDesSallesApplication implements CommandLineRunner {

	@Autowired
	private CollaborateurService collaborateurService;
	@Autowired
	private SalleService salleService;
	@Autowired
	private ReunionService  reunionService;
	@Autowired
	private ReservationService reservationService;

	public static void main(String[] args) {
		SpringApplication.run(GestionnaireDesSallesApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		Collaborateur collaborateur1=new Collaborateur(null, "Mohamed",
				"Sidibe", "me@gmail.com",null);
		Collaborateur collaborateur2=new Collaborateur(null, "Me",
				"Hassan", "me@gmail.com",null);

		collaborateurService.save(CollaborateurDto.fromEntity(collaborateur1));
		collaborateurService.save(CollaborateurDto.fromEntity(collaborateur2));


		List<String> equipement1=new ArrayList<>();
		equipement1.add("Ecran");
		equipement1.add("Webcam");
		equipement1.add("Pieuve");

		List<String> equipement2=new ArrayList<>();
		equipement2.add("Tableau");

		List<String> equipement3=new ArrayList<>();
		equipement3.add("Ecran");
		equipement3.add("Webcam");

		List<String> equipement4=new ArrayList<>();
		equipement4.add("Tableau");
		equipement4.add("Ecran");
		equipement4.add("Pieuve");


		Salle salle1=new Salle(null,"E1001",23,null,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle1));
		Salle salle2=new Salle(null,"E3001",13,equipement1,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle2));
		Salle salle3=new Salle(null,"E1004",4,equipement2,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle3));
		Salle salle4=new Salle(null,"E1002",23,equipement3,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle4));
		Salle salle5=new Salle(null,"E4001",23,equipement4,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle5));
		Salle salle6=new Salle(null,"E3002",13,equipement1,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle6));
		Salle salle7=new Salle(null,"E3003",20,equipement1,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle7));
		Salle salle8=new Salle(null,"E3004",13,equipement1,null,null);
		salleService.ajouter(SalleDto.fromEntity(salle8));


		SalleDto sallerecup1=salleService.findById(3);
		Reunion reunion1=new Reunion(null,"reunion1", TypeReunion.SPEC,20,SalleDto.toEntity(sallerecup1));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion1));
		SalleDto sallerecup2=salleService.findById(5);
		Reunion reunion2=new Reunion(null,"reunion2", TypeReunion.RC,20,SalleDto.toEntity(sallerecup2));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion2));
		SalleDto sallerecup3=salleService.findById(1);
		Reunion reunion3=new Reunion(null,"reunion3", TypeReunion.RS,20,SalleDto.toEntity(sallerecup3));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion3));
		SalleDto sallerecup4=salleService.findById(2);
		Reunion reunion4=new Reunion(null,"reunion4", TypeReunion.VC,20,SalleDto.toEntity(sallerecup4));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion4));
		SalleDto sallerecup6=salleService.findById(6);
		Reunion reunion5=new Reunion(null,"reunion5", TypeReunion.VC,28,SalleDto.toEntity(sallerecup6));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion5));
		SalleDto sallerecup7=salleService.findById(7);
		Reunion reunion7=new Reunion(null,"reunion6", TypeReunion.VC,25,SalleDto.toEntity(sallerecup7));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion7));
		SalleDto sallerecup8=salleService.findById(8);
		Reunion reunion8=new Reunion(null,"reunion7", TypeReunion.VC,20,SalleDto.toEntity(sallerecup8));
		reunionService.saveReunion(ReunionDto.fromEntity(reunion8));


		CollaborateurDto collabrecup1=collaborateurService.findById(1);
		Reservation reservation1 =new Reservation(null,"lundi", LocalTime.of(8,0),LocalTime.of(9,0),true,false,CollaborateurDto.toEntity(collabrecup1),SalleDto.toEntity(sallerecup2));
		reservationService.save(ReservationDto.fromEntity(reservation1));

		CollaborateurDto collabrecup2=collaborateurService.findById(2);
		Reservation reservation2 =new Reservation(null,"lundi",LocalTime.of(10,0),LocalTime.of(11,0),true,false,CollaborateurDto.toEntity(collabrecup2),SalleDto.toEntity(sallerecup3));
		reservationService.save(ReservationDto.fromEntity(reservation2));

		CollaborateurDto collabrecup3=collaborateurService.findById(2);
		Reservation reservation3 =new Reservation(null,"lundi",LocalTime.of(8,0),LocalTime.of(9,0),true,false,CollaborateurDto.toEntity(collabrecup3),SalleDto.toEntity(sallerecup6));
		reservationService.save(ReservationDto.fromEntity(reservation3));

		CollaborateurDto collabrecup4=collaborateurService.findById(1);
		Reservation reservation4 =new Reservation(null,"lundi",LocalTime.of(8,0),LocalTime.of(9,0),true,false,CollaborateurDto.toEntity(collabrecup4),SalleDto.toEntity(sallerecup7));
		reservationService.save(ReservationDto.fromEntity(reservation4));




		reservationService.findReservationsBySalle(sallerecup1);

		///reservationService.reserver(ReservationDto.fromEntity(new Reservation(null,"lundi",LocalTime.of(12,0),LocalTime.of(13,0),true,false,CollaborateurDto.toEntity(collabrecup2),SalleDto.toEntity(sallerecup3))));

		//ReunionDto reunionDto= reunionService.findById(4);
		salleService.salleOptimale(7,TypeReunion.VC,LocalTime.of(8,0));


	}
}
