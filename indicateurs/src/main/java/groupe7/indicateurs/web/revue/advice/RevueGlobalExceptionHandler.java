package groupe7.indicateurs.web.revue.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@ControllerAdvice
public class RevueGlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    // D'autres gestionnaires d'exceptions peuvent être ajoutés ici
}
