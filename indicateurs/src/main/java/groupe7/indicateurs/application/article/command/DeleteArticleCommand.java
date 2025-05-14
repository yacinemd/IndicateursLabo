package groupe7.indicateurs.application.article.command;

import java.util.Objects;

public class DeleteArticleCommand {
    private Long id;

    public DeleteArticleCommand(Long id) {
        this.id = Objects.requireNonNull(id, "L'identifiant est obligatoire");
    }

    public Long getId() {
        return id;
    }
}
