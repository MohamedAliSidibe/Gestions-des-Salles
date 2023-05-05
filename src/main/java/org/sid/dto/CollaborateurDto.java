package org.sid.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.sid.entities.Collaborateur;
import java.util.Collection;
import java.util.Objects;

@Data
@Builder
public class CollaborateurDto {
    private Integer idCollab;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String email;
    @JsonIgnore
    private Collection<ReservationDto> reservations;

    public static CollaborateurDto fromEntity(Collaborateur collaborateur){
        if(Objects.isNull(collaborateur)){
            return  null;
        }
        return CollaborateurDto.builder()
                .idCollab(collaborateur.getIdCollab())
                .nom(collaborateur.getNom())
                .prenom(collaborateur.getPrenom())
                .email(collaborateur.getEmail())
                .build();
    }
    public  static Collaborateur toEntity(CollaborateurDto collaborateurDto ){
        if(Objects.isNull(collaborateurDto)){
            return null;
        }
        Collaborateur collaborateur=new Collaborateur();
        collaborateur.setIdCollab(collaborateurDto.getIdCollab());
        collaborateur.setNom(collaborateurDto.getNom());
        collaborateur.setPrenom(collaborateurDto.getPrenom());
        collaborateur.setEmail(collaborateurDto.getEmail());
        return collaborateur;

    }


}
