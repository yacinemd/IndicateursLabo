package groupe7.indicateurs.application.revue.command;

import java.util.Objects;

public class DeleteRevueCommand extends RevueCommand {
    private Long id;

    public DeleteRevueCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant de la revue est obligatoire");
    }

    // Getters
    
    public Long getId() {
        return id;
    }
}
