package groupe7.indicateurs.application.livre.command;

import java.time.Year;
import java.util.Objects;
 

public class CreateLivreCommand extends LivreCommand {
    
    private Year annee;
    private String titre;

    public CreateLivreCommand(Year annee, String titre) {
        this.annee = Objects.requireNonNull(annee, "L'année est obligatoire");
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
    }

    public Year getAnnee() {
        return annee;
    }
    public String getTitre() {
        return titre;
    }
    
}
