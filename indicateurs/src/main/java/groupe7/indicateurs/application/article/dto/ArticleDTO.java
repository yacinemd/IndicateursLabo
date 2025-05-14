package groupe7.indicateurs.application.article.dto;

import java.time.Year;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.revue.model.Revue;

public class ArticleDTO {
    private Long id;
    private Year date;
    private String titre;
    private String pays;
    private Revue revue;

    public ArticleDTO(){

    }

    public ArticleDTO(Long id,Year date,String titre,String pays){
        this.id=id;
        this.date=date;
        this.titre=titre;
        this.pays=pays;
        this.revue=null;
    }

    public static ArticleDTO toDto(Article entity) {
        return new ArticleDTO(
            entity.getId(),
            entity.getDate(),
            entity.getTitre(),
            entity.getPays()
        );
    }

    public static Article toEntity(ArticleDTO dto) {
        return new Article(
            dto.getTitre(),
            dto.getDate(),
            dto.getPays(),
            dto.getRevue()
        );
    }

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

    public Year getDate(){
        return date;
    }

    public void setDate(Year date){
        this.date=date;
    }

    public String getPays(){
        return pays;
    }
    public void setPays(String pays){
        this.pays=pays;
    }

    public Revue getRevue(){
        return revue;
    }

    public void setRevue(Revue revue) {
        this.revue = revue;
    }
}
