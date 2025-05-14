package groupe7.indicateurs.domain.liaison_personne_conference.model;

import groupe7.indicateurs.domain.conference.model.Conference;
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
@Table(name = "liaison_personne_conference")
public class LiaisonPersonneConference {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pc_id_pc")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "pc_id_personne", referencedColumnName = "pe_id_personne")
    private Personne personne;
    
    @ManyToOne
    @JoinColumn(name = "pc_id_conference", referencedColumnName = "cf_id_conf")
    private Conference conference;

    // Constructeurs
    public LiaisonPersonneConference() {}

    public LiaisonPersonneConference(Personne personne, Conference conference) {
        this.personne = personne;
        this.conference = conference;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
    }

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
