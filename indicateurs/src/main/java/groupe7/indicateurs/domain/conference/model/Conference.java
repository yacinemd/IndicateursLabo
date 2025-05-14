package groupe7.indicateurs.domain.conference.model;

import java.time.Year;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Conference {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "cf_id_conf")
    private Long id;

    @Column(name = "cf_titre")
    private String titre;


    @Column(name = "cf_annee")
    private Year annee;

    @Column(name = "cf_pays")
    private String pays;



    public Conference() {
    }

    public Conference(String titre, Year annee,String pays) {
        this.titre = titre;
        this.annee = annee;
        this.pays=pays;
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

    public String getPays(){
        return pays;
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
    
    public void setPays(String pays){
        this.pays = pays;
    }

}
