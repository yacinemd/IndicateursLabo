package groupe7.indicateurs.domain.liaison_personne_livre.model;

import groupe7.indicateurs.domain.livre.model.Livre;
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
@Table(name = "liaison_personne_livre")
public class LiaisonPersonneLivre {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pl_id_pl")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "pl_id_personne", referencedColumnName = "pe_id_personne")
    private Personne personne;
    
    @ManyToOne
    @JoinColumn(name = "pl_id_livre", referencedColumnName = "li_id_livre")
    private Livre livre;

    // Constructeurs
    public LiaisonPersonneLivre() {}

    public LiaisonPersonneLivre(Personne personne, Livre livre) {
        this.personne = personne;
        this.livre = livre;
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

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }
}
