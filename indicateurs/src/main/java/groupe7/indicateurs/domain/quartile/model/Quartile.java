package groupe7.indicateurs.domain.quartile.model;

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
public class Quartile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ql_id_quartile")
    private Long id;
    
    @Column(name = "ql_annee")
    private Year annee;

    @Column(name = "ql_quartile")
    private String quartile;

    @Column(name = "ql_domaine")
    private String domaine;

    @ManyToOne
    @JoinColumn(name= "ql_id_revue")
    private Revue revue;


    public Quartile(){
        
    }

    public Quartile(Year annee, String quartile, String domaine) {
        this.domaine = domaine;
        this.annee = annee;
        this.quartile = quartile;
    }

    // Getters

    public Long getId() {
        return id;
    }
   
    public Year getAnnee() {
        return annee;
    }

    public String getQuartile() {
        return quartile;
    }
    public String getDomaine() {
        return domaine;
    }
    public Revue getRevue() {
        return revue;
    }

    // Setters

    public void setId(Long id) {
        this.id = id;
    }

    public void setAnnee(Year annee) {
        this.annee = annee;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public void setQuartile(String quartile) {
        this.quartile = quartile;
    }

    public void setRevue(Revue revue) {
        this.revue = revue;
    }
}
