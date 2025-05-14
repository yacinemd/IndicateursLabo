package groupe7.indicateurs.application.conference.command;

import java.time.Year;
import java.util.Objects;

public class CreateConferenceCommand extends ConferenceCommand {
    private String titre;
    private Year annee;
    private String pays;
    

    public CreateConferenceCommand(String titre, Year annee, String pays) {
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.pays = Objects.requireNonNull(pays, "Le pays est obligatoire");
    }

    // Getters
    
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
