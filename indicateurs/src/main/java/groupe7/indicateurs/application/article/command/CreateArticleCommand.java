package groupe7.indicateurs.application.article.command;

import java.time.LocalDate;
import java.util.Objects;

import groupe7.indicateurs.application.revue.dto.RevueDTO;

public class CreateArticleCommand extends ArticleCommand {
    private String titre;
    private LocalDate date;
    private RevueDTO revue;

    public CreateArticleCommand(String titre, LocalDate date, RevueDTO revue) {
        this.titre = Objects.requireNonNull(titre, "Le titre est obligatoire");
        this.date = Objects.requireNonNull(date, "La date est obligatoire");   
        this.revue=revue;
    }

    public String getTitre() {
        return titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public RevueDTO getRevue(){
        return revue;
    }
    
}
