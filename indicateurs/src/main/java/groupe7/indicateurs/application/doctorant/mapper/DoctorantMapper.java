package groupe7.indicateurs.application.doctorant.mapper;

import org.springframework.stereotype.Component;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.application.doctorant.dto.DoctorantDTO;
import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.domain.doctorant.model.Doctorant;
import groupe7.indicateurs.domain.personnel.model.Personnel;

@Component
public class DoctorantMapper {
    
    public DoctorantDTO toDto(Doctorant doctorant) {
        if (doctorant == null || doctorant.getPersonnel() == null) {
            return null;
        }

        DoctorantDTO dto = new DoctorantDTO();
        dto.setId(doctorant.getId());
        dto.setNom(doctorant.getPersonnel().getNom());
        dto.setPrenom(doctorant.getPersonnel().getPrenom());
        dto.setHdr(doctorant.getHdr());
        dto.setStatut(doctorant.getStatut());
        dto.setEquipe(doctorant.getEquipe());
        dto.setTitulaire(doctorant.getTitulaire());
        dto.setDate_debut(doctorant.getDate_debut());
        dto.setDate_fin(doctorant.getDate_fin());
        dto.setEncadrant(doctorant.getEncadrant());
        dto.setDate_soutenance(doctorant.getDate_soutenance());
        dto.setProjets(doctorant.getProjets().stream()
            .map(ProjetDTO::toDto)
            .toList()
        );
        dto.setArticles(doctorant.getArticles().stream()
            .map(ArticleDTO::toDto)
            .toList()
        );

        return dto;
    }

    public Doctorant toEntity(DoctorantDTO dto) {
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
        personnel.setProjets(dto.getProjets().stream()
            .map(ProjetDTO::toEntity)
            .toList()
        );

        personnel.setArticles(dto.getArticles().stream()
            .map(ArticleDTO::toEntity)
            .toList()    
        );
        
        Doctorant doctorant = new Doctorant();
        doctorant.setId(dto.getId());
        doctorant.setPersonnel(personnel);
        doctorant.setEncadrant(dto.getEncadrant());
        doctorant.setDate_soutenance(dto.getDate_soutenance());



        return doctorant;
    }
}
