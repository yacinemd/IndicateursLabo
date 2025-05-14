package groupe7.indicateurs.domain.liaison_personne_article.model;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.personne.model.Personne;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "liaison_personne_article")
public class LiaisonPersonneArticle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "au_id_ap")
    private Long id; 

    @ManyToOne
    @JoinColumn(name = "au_id_personne", referencedColumnName = "pe_id_personne")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "au_id_article", referencedColumnName = "at_id_article")
    private Article article;

    // Constructeurs, getters et setters
    public LiaisonPersonneArticle() {
    }

    public LiaisonPersonneArticle(Personne personne, Article article) {
        this.personne = personne;
        this.article = article;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
