package groupe7.indicateurs.web.article.dto;

import java.time.LocalDate;

import groupe7.indicateurs.web.revue.dto.RevueWebDTO;

public class ArticleWebDTO {
    private Long id;
    private String titre;
    private LocalDate date;
    private RevueWebDTO revue;
    private String pays;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
    public String getPays() {
        return pays;
    }
    public void setPays(String pays) {
        this.pays = pays;
    }

    public RevueWebDTO getRevue() {
        return revue;
    }

    public void setRevue(RevueWebDTO revue) {
        this.revue = revue;
    }
}
