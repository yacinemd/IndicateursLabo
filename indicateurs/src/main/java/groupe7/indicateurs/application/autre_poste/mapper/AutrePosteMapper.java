package groupe7.indicateurs.application.autre_poste.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.autre_poste.dto.AutrePosteDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;
import groupe7.indicateurs.domain.personnel.model.Personnel;

@Component
public class AutrePosteMapper {

    public AutrePosteDTO toDto(AutrePoste autrePoste) {
        if (autrePoste == null || autrePoste.getPersonnel() == null) {
            return null;
        }

        AutrePosteDTO dto = new AutrePosteDTO();
        dto.setId(autrePoste.getId());
        dto.setNom(autrePoste.getPersonnel().getNom());
        dto.setPrenom(autrePoste.getPersonnel().getPrenom());
        dto.setHdr(autrePoste.getHdr());
        dto.setStatut(autrePoste.getStatut());
        dto.setEquipe(autrePoste.getEquipe());
        dto.setTitulaire(autrePoste.getTitulaire());
        dto.setDate_debut(autrePoste.getDate_debut());
        dto.setDate_fin(autrePoste.getDate_fin());
        dto.setProjets(autrePoste.getProjets().stream()
            .map(ProjetDTO::toDto)
            .toList()
        );
        dto.setTitre(autrePoste.getTitre());
        dto.setArticles(autrePoste.getArticles().stream()
            .map(ArticleDTO::toDto)
            .toList()
        );

        return dto;
    }

    public AutrePoste toEntity(AutrePosteDTO dto) {
        if (dto == null) {
            return null;
        }

        Personnel personnel = new Personnel();
        personnel.setNom(dto.getNom());
        personnel.setPrenom(dto.getPrenom());
        personnel.setEquipe(dto.getEquipe());
        personnel.setStatut(dto.getStatut());
        personnel.setTitulaire(dto.getTitulaire());
        personnel.setDate_debut(dto.getDate_debut());
        personnel.setDate_fin(dto.getDate_fin());

        AutrePoste autrePoste = new AutrePoste();
        autrePoste.setId(dto.getId());
        autrePoste.setPersonnel(personnel);

        autrePoste.setHdr(dto.getHdr());
        autrePoste.setProjets(dto.getProjets().stream()
            .map(ProjetDTO::toEntity)
            .toList()
        );
        autrePoste.setTitre(dto.getTitre());
        autrePoste.setArticles(dto.getArticles().stream()
            .map(ArticleDTO::toEntity)
            .toList()    
        );

        return autrePoste;
    }
}
