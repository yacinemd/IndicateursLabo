package groupe7.indicateurs.application.personne.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.personne.dto.PersonneDTO;
import groupe7.indicateurs.domain.personne.model.Personne;



@Component
public class PersonneMapper {
    public PersonneDTO toDto(Personne personne) {
        if (personne == null) {
            return null;
        }

        PersonneDTO dto = new PersonneDTO();
        dto.setId(personne.getId());
        dto.setNom(personne.getNom());
        dto.setPrenom(personne.getPrenom());
        dto.setHdr(personne.getHdr());
       
        
        return dto;
    }

    public Personne toEntity(PersonneDTO dto) {
        if (dto == null) {
            return null;
        }

        Personne personne = new Personne();
        personne.setId(dto.getId());
        personne.setNom(dto.getNom());
        personne.setPrenom(dto.getPrenom());
        personne.setHdr(dto.getHdr());
     
        
        return personne;
    }

}
