package groupe7.indicateurs.application.conference.command;

import java.time.Year;
import java.util.Objects;

public class UpdateConferenceCommand extends ConferenceCommand {
    private long id;
    private String titre;
    private Year annee;
    private String pays;
   


    public UpdateConferenceCommand(long id, String titre, Year annee,  String pays) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.pays = Objects.requireNonNull(pays, "Le pays est obligatoire");
    }

    // Getters
    
    public long getId() {
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
}
