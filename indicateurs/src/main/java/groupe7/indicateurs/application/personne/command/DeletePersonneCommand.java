package groupe7.indicateurs.application.personne.command;

import java.util.Objects;

public class DeletePersonneCommand extends PersonneCommand {
    private Long id;

    public DeletePersonneCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
