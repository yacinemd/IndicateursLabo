package groupe7.indicateurs.domain.revue.model;

import java.time.Year;
import java.util.List;

import groupe7.indicateurs.domain.article.model.Article;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Revue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "re_id_revue")
    private Long id;

    @OneToMany(mappedBy = "revue")
    private List<Article> articles;

    @Column(name = "re_annee")
    private Year annee;

    @Column(name = "re_titre")
    private String titre;

    @Column( name = "re_publisher")
    private String publisher;


    
    public Revue() {
    }

    public Revue(Year annee, String titre, String publisher) {
        this.annee = annee;
        this.titre = titre;
        this.publisher=publisher;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Year getAnnee() {
        return annee;
    }

    public String getTitre() {
        return titre;
    }


    public String getPublisher(){
        return publisher;
    }

    


    public List<Article> getArticles() {
        return articles;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void setPublisher(String publisher){
        this.publisher=publisher;
    }
}
