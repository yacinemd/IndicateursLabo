package groupe7.indicateurs.application.livre.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.livre.dto.LivreDTO;
import groupe7.indicateurs.domain.livre.model.Livre;

@Component
public class LivreMapper {
    
    public LivreDTO toDto(Livre livre) {
        if (livre == null) {
            return null;
        }

        LivreDTO dto = new LivreDTO();
        dto.setId(livre.getId());
        dto.setAnnee(livre.getAnnee());
        dto.setTitre(livre.getTitre());
        dto.setPays(livre.getPays());
              
        return dto;
    }

    public Livre toEntity(LivreDTO dto) {
        if (dto == null) {
            return null;
        }

        Livre livre = new Livre();
        livre.setId(dto.getId());
        livre.setAnnee(dto.getAnnee());
        livre.setTitre(dto.getTitre());
        livre.setPays(dto.getPays());
        
        return livre;
    }
}
