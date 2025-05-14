package groupe7.indicateurs.domain.article.model;

import java.time.Year;

import groupe7.indicateurs.domain.revue.model.Revue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "at_id_article")
    private Long id;
    
    @Column(name = "at_titre",length=1000)
    private String titre;

    @Column(name = "at_date")
    private Year date;

    @Column(name = "at_pays")
    private String pays;
    
    @ManyToOne
    @JoinColumn(name="at_id_revue")
    private Revue revue;



    public Article(){
        
    }

    public Article(String titre, Year date , String pays, Revue revue){
        this.titre=titre;
        this.date = date;
        this.pays=pays;
        this.revue=revue;
    }

    public Long getId() {
        return id;
    }
   
    public String getTitre() {
        return titre;
    }

    public Year getDate() {
        return date;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDate(Year date) {
        this.date = date;
    }

    public String getPays() {
        return pays;
    }
    
    public void setPays(String pays) {
        this.pays = pays;
    }

    
    public Revue getRevue() {
        return revue;
    }

    public void setRevue(Revue revue) {
        this.revue = revue;
    }
}
