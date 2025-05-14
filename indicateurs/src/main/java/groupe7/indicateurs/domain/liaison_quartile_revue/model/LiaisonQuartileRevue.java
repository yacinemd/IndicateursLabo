package groupe7.indicateurs.domain.liaison_quartile_revue.model;

import groupe7.indicateurs.domain.quartile.model.Quartile;
import groupe7.indicateurs.domain.revue.model.Revue;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "liaison_quartile_revue")
public class LiaisonQuartileRevue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "qr_id_qr")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "qr_id_quartile", referencedColumnName = "ql_id_quartile")
    private Quartile quartile;

    @ManyToOne
    @JoinColumn(name = "qr_id_revue", referencedColumnName = "re_id_revue")
    private Revue revue;

    // Constructeurs
    public LiaisonQuartileRevue() {}

    public LiaisonQuartileRevue(Quartile quartile, Revue revue) {
        this.quartile = quartile;
        this.revue = revue;
    }

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quartile getQuartile() {
        return quartile;
    }

    public void setQuartile(Quartile quartile) {
        this.quartile = quartile;
    }

    public Revue getRevue() {
        return revue;
    }

    public void setRevue(Revue revue) {
        this.revue = revue;
    }
}
