package groupe7.indicateurs.application.quartile.dto;

import java.time.Year;

import groupe7.indicateurs.application.revue.dto.RevueDTO;
import groupe7.indicateurs.domain.quartile.model.Quartile;

public class QuartileDTO {
    private Long id;
    private Year annee;
    private String quartile;
    private String domaine;
    private RevueDTO revue;

    public QuartileDTO() {
    }

    public QuartileDTO(Long id, Year annee, String quartile, String domaine) {
        this.id = id;
        this.annee = annee;
        this.quartile = quartile;
        this.domaine = domaine;
    }

    public static QuartileDTO toDto(Quartile entity) {
        QuartileDTO dto = new QuartileDTO(
            entity.getId(),
            entity.getAnnee(),
            entity.getQuartile(),
            entity.getDomaine()
        );
        // Si tu veux mapper la revue aussi :
        // dto.setRevue(RevueDTO.toDto(entity.getRevue()));
        return dto;
    }

    public static Quartile toEntity(QuartileDTO dto) {
        Quartile entity = new Quartile(
            dto.getAnnee(),
            dto.getQuartile(),
            dto.getDomaine()
        );
        entity.setId(dto.getId());
        // Si tu veux mapper la revue :
        // entity.setRevue(RevueDTO.toEntity(dto.getRevue()));
        return entity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Year getAnnee() {
        return annee;
    }

    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    public String getQuartile() {
        return quartile;
    }

    public void setQuartile(String quartile) {
        this.quartile = quartile;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public RevueDTO getRevue() {
        return revue;
    }

    public void setRevue(RevueDTO revue) {
        this.revue = revue;
    }
}
