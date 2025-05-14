package groupe7.indicateurs.application.personnel.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.personnel.dto.PersonnelDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.personnel.model.Personnel;

@Component
public class PersonnelMapper {

    public PersonnelDTO toDto(Personnel personnel) {
        if (personnel == null) {
            return null;
        }

        PersonnelDTO dto = new PersonnelDTO();
        dto.setId(personnel.getId());
        dto.setNom(personnel.getNom());
        dto.setPrenom(personnel.getPrenom());
        dto.setHdr(personnel.getHdr());
        dto.setStatut(personnel.getStatut());
        dto.setEquipe(personnel.getEquipe());
        dto.setTitulaire(personnel.getTitulaire());
        dto.setDateDebut(personnel.getDate_debut());
        dto.setDateFin(personnel.getDate_fin());
        dto.setProjets(personnel.getProjets().stream()
            .map(ProjetDTO::toDto)
            .toList()
        );
        dto.setArticles(personnel.getArticles().stream()
            .map(ArticleDTO::toDto)
            .toList()
        );

        return dto;
    }

    public Personnel toEntity(PersonnelDTO dto) {
        if (dto == null) {
            return null;
        }

        Personnel personnel = new Personnel();
        personnel.setId(dto.getId());
        personnel.setNom(dto.getNom());
        personnel.setPrenom(dto.getPrenom());
        personnel.setHdr(dto.getHdr());
        personnel.setStatut(dto.getStatut());
        personnel.setEquipe(dto.getEquipe());
        personnel.setTitulaire(dto.getTitulaire());
        personnel.setDate_debut(dto.getDateDebut());
        personnel.setDate_fin(dto.getDateFin());
        personnel.setProjets(dto.getProjets().stream()
            .map(ProjetDTO::toEntity)
            .toList()
            );
        personnel.setArticles(dto.getArticles().stream()
            .map(ArticleDTO::toEntity)
            .toList()
        );

        return personnel;
    }
}
