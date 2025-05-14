package groupe7.indicateurs.application.livre.command;

import java.util.Objects;

public class DeleteLivreCommand {
    
    private Long id;

    public DeleteLivreCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
