package groupe7.indicateurs.application.revue.dto;

import java.time.Year;

import groupe7.indicateurs.domain.revue.model.Revue;

public class RevueDTO {

    private Long id;
    private String titre;
    private Year annee;
    private String publisher;

    public RevueDTO() {
    }

    public RevueDTO(Long id, String titre, Year annee,String publisher) {
        this.id = id;
        this.titre = titre;
        this.annee = annee;
        this.publisher=publisher;
    }

    public static RevueDTO toDto(Revue entity) {
        return new RevueDTO(
            entity.getId(),
            entity.getTitre(),
            entity.getAnnee(),
            entity.getPublisher()
        );
    }

    public static Revue toEntity(RevueDTO dto) {
        return new Revue(
            dto.getAnnee(),
            dto.getTitre(),
            dto.getPublisher()
        );
    }

    // Getters

    public Long getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

  

    public Year getAnnee() {
        return annee;
    }

    public String getPublisher(){
        return publisher;
    }



    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

 

    public void setAnnee(Year annee) {
        this.annee = annee;
    }


    public void setPublisher(String publisher){
        this.publisher=publisher;
    }

}
