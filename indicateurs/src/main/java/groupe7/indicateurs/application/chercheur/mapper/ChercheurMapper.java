package groupe7.indicateurs.application.chercheur.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.chercheur.dto.ChercheurDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.chercheur.model.Chercheur;
import groupe7.indicateurs.domain.personnel.model.Personnel;

@Component
public class ChercheurMapper {
    
    public ChercheurDTO toDto(Chercheur chercheur) {
        if (chercheur == null || chercheur.getPersonnel() == null) {
            return null;
        }

        ChercheurDTO dto = new ChercheurDTO();
        dto.setId(chercheur.getId());
        dto.setNom(chercheur.getPersonnel().getNom());
        dto.setPrenom(chercheur.getPersonnel().getPrenom());
        dto.setHdr(chercheur.getHdr());
        dto.setStatut(chercheur.getStatut());
        dto.setEquipe(chercheur.getEquipe());
        dto.setTitulaire(chercheur.getTitulaire());
        dto.setDate_debut(chercheur.getDate_debut());
        dto.setDate_fin(chercheur.getDate_fin());
        dto.setProjets(chercheur.getProjets().stream()
            .map(ProjetDTO::toDto)
            .toList()
        );
        dto.setArticles(chercheur.getArticles().stream()
            .map(ArticleDTO::toDto)
            .toList()
        );

        return dto;
    }

    public Chercheur toEntity(ChercheurDTO dto) {
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

        Chercheur chercheur = new Chercheur();
        chercheur.setId(dto.getId());
        chercheur.setPersonnel(personnel);

        chercheur.setHdr(dto.getHdr());
        chercheur.setProjets(dto.getProjets().stream()
            .map(ProjetDTO::toEntity)
            .toList()
        );
        
        chercheur.setArticles(dto.getArticles().stream()
            .map(ArticleDTO::toEntity)
            .toList()    
        );

        return chercheur;
    }
}