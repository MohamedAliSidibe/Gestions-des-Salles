package org.sid.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sid.entities.Reunion;
import org.sid.entities.Salle;
import org.sid.entities.TypeReunion;
import java.util.Objects;

@Data
@Builder
public class ReunionDto {

    private Integer idReunion;
    @NotNull
    private String nameReunion;
    @NotNull
    private TypeReunion typeReunion;
    @NotNull
    private int nbrePersoConvies;
    @NotNull
    private SalleDto salle;

    public static ReunionDto fromEntity(Reunion reunion){
        if(Objects.isNull(reunion)){
            return  null;
        }
        return ReunionDto.builder()
                .idReunion(reunion.getIdReunion())
                .nameReunion(reunion.getNameReunion())
                .nbrePersoConvies(reunion.getNbrePersoConvies())
                .typeReunion(reunion.getTypeReunion())
                .nbrePersoConvies(reunion.getNbrePersoConvies())
                .salle(SalleDto.fromEntity(reunion.getSalle()))
                .build();
    }
    public  static Reunion toEntity(ReunionDto reunionDto ){
        if(reunionDto==null){
            return null;
        }
        Reunion reunion=new Reunion();
        reunion.setIdReunion(reunionDto.getIdReunion());
        reunion.setNameReunion(reunionDto.getNameReunion());
        reunion.setTypeReunion(reunionDto.getTypeReunion());
        reunion.setNbrePersoConvies(reunionDto.getNbrePersoConvies());
        reunion.setSalle(SalleDto.toEntity(reunionDto.getSalle()));
        return reunion;

    }

}
