package groupe7.indicateurs.application.quartile.command;

import java.util.Objects;

public class DeleteQuartileCommand {
    private Long id;

    public DeleteQuartileCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
