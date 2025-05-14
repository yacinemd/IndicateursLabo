package groupe7.indicateurs.application.conference.command;

import java.util.Objects;

public class DeleteConferenceCommand extends ConferenceCommand {
    private Long id;

    public DeleteConferenceCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant de la conference est obligatoire");
    }

    // Getters
    
    public Long getId() {
        return id;
    }
}
