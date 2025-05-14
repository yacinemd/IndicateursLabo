package groupe7.indicateurs.application.autre_poste.command;

import java.util.Objects;

public class DeleteAutrePosteCommand extends AutrePosteCommand {
    private Long id;

    public DeleteAutrePosteCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
