package groupe7.indicateurs.application.projet.command;

import java.util.Objects;

public class DeleteProjetCommand extends ProjetCommand {
    private Long id;

    public DeleteProjetCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant du projet est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
