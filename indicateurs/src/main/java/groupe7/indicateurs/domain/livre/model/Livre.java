package groupe7.indicateurs.domain.livre.model;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "li_id_livre")
    private Long id;
    
    @Column(name = "li_annee")
    private Year annee;

    @Column(name = "li_titre")
    private String titre;

    @Column(name= "li_pays")
    private String pays;

    public Livre(){
        
    }

    public Livre(Year annee, String titre, String pays){
        this.pays = pays;
        this.annee = annee;
        this.titre = titre;
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

    public String getPays() {
        return pays;
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

    public void setPays(String pays) {
        this.pays = pays;
    }
}
