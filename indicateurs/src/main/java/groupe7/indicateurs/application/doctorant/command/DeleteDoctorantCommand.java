package groupe7.indicateurs.application.doctorant.command;

import java.util.Objects;

public class DeleteDoctorantCommand extends DoctorantCommand {
    private Long id;

    public DeleteDoctorantCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
