package org.sid.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sid.entities.Reservation;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Data
@Builder
public class SalleDto {

    private Integer idSalle;
    @NotNull
    private String nameSalle;
    @NotNull
    private int nombrePlace;
    private List<String> equipement;
    @JsonIgnore
    private Collection<Reunion> reunions;
    @JsonIgnore
    private Reservation reservation;

    public static SalleDto fromEntity(Salle salle){
        if(Objects.isNull(salle)){
            return  null;
        }
        return SalleDto.builder()
                .idSalle(salle.getIdSalle())
                .nameSalle(salle.getNameSalle())
                .nombrePlace(salle.getNombrePlace())
                .equipement(salle.getEquipement())
                .build();
    }
    public  static Salle toEntity(SalleDto salleDto ){
        if(salleDto==null){
            return null;
        }
        Salle salle=new Salle();
        salle.setIdSalle(salleDto.getIdSalle());
        salle.setNameSalle(salleDto.getNameSalle());
        salle.setNombrePlace(salleDto.getNombrePlace());
        salle.setEquipement(salleDto.getEquipement());
        return salle;

    }
}
