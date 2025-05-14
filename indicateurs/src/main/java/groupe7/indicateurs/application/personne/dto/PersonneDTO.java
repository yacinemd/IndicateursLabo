package groupe7.indicateurs.application.personne.dto;

import groupe7.indicateurs.application.article.dto.ArticleDTO;
import groupe7.indicateurs.domain.personne.model.Personne;


import java.util.List;

public class PersonneDTO {
    private Long id;
    private String nom;
    private String prenom;
    private Boolean hdr;
    private List<ArticleDTO> articles;

    public PersonneDTO(){
    }

    public PersonneDTO(Long id, String nom, String prenom, Boolean hdr) {
        this.id = id;
        this.nom=nom;
        this.prenom=prenom;
        this.hdr=hdr;
    }

    public static PersonneDTO toDto(Personne entity) {
        return new PersonneDTO(
            entity.getId(),
            entity.getNom(),
            entity.getPrenom(),
            entity.getHdr()
        );
    }

    public static Personne toEntity(PersonneDTO dto) {
        return new Personne(
            dto.getNom(),
            dto.getPrenom(),
            dto.getHdr()
        );
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Boolean getHdr() {
        return hdr;
    }

    public void setHdr(Boolean hdr) {
        this.hdr = hdr;
    }

    public void setArticles(List<ArticleDTO> articles){
        this.articles=articles;
    }

    public List<ArticleDTO> getArticles(){
        return articles;
    }

}
