package groupe7.indicateurs.application.personnel.command;

import java.util.Objects;

public class DeletePersonnelCommand extends PersonnelCommand {
    private Long id;

    public DeletePersonnelCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
