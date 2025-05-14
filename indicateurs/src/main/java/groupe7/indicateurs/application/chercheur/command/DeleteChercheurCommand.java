package groupe7.indicateurs.application.chercheur.command;

import java.util.Objects;

public class DeleteChercheurCommand extends ChercheurCommand {
    private Long id;

    public DeleteChercheurCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
