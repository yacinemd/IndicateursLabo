package groupe7.indicateurs.application.projet.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.personnel.dto.PersonnelDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.projet.model.Projet;

@Component
public class ProjetMapper {

    public ProjetDTO toDto(Projet projet) {
        if (projet == null) {
            return null;
        }

        ProjetDTO dto = new ProjetDTO();
        dto.setId(projet.getId());
        dto.setNom(projet.getNom());
        dto.setIdContributeurProjet(projet.getIdContributeurProjet());
        dto.setDateDebut(projet.getDateDebut());
        dto.setDateFin(projet.getDateFin());
        dto.setType(projet.getType());
        dto.setBudget(projet.getBudget());
        dto.setPartenariat(projet.getPartenariat());
        dto.setPersonnels(projet.getPersonnels().stream()
        .map(PersonnelDTO::toDto)
        .toList()
    );


        return dto;
    }

    public Projet toEntity(ProjetDTO dto) {
        if (dto == null) {
            return null;
        }

        Projet projet = new Projet();
        projet.setId(dto.getId());
        projet.setNom(dto.getNom());
        projet.setIdContributeurProjet(dto.getIdContributeurProjet());
        projet.setDateDebut(dto.getDateDebut());
        projet.setDateFin(dto.getDateFin());
        projet.setType(dto.getType());
        projet.setBudget(dto.getBudget());
        projet.setPartenariat(dto.getPartenariat());
        projet.setPersonnels(dto.getPersonnels().stream()
        .map(PersonnelDTO::toEntity)
        .toList()
    );

        return projet;
    }
}
