package groupe7.indicateurs.web.personne.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupe7.indicateurs.application.personne.dto.PersonneDTO;
import groupe7.indicateurs.application.personne.mapper.PersonneMapper;
import groupe7.indicateurs.application.personne.service.PersonneService;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;
import groupe7.indicateurs.domain.personne.model.Personne;

//commentaire teste

@RestController
@RequestMapping("/api/personnes")
public class PersonneController {
    private final PersonneService personneService;
    private final PersonneMapper personneMapper;

    public PersonneController(PersonneService personneService, PersonneMapper personneMapper) {
        this.personneService = personneService;
        this.personneMapper = personneMapper;
    }

    @PostMapping
    public ResponseEntity<PersonneDTO> createPersonne(@RequestBody PersonneDTO personneDTO) {
        Personne personne = personneMapper.toEntity(personneDTO);
        Personne savedPersonne = personneService.save(personne);
        PersonneDTO savedPersonneDTO = personneMapper.toDto(savedPersonne);
        return ResponseEntity.ok(savedPersonneDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonneDTO> getPersonneById(@PathVariable Long id) {
        Personne personne = personneService.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException("Personne not found"));
        PersonneDTO personneDTO = personneMapper.toDto(personne);
        return ResponseEntity.ok(personneDTO); 
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id) {
        personneService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<PersonneDTO>> getPersonnesByNom(@RequestParam(name = "nom", required = false) String nom) {
       
            // Si un nom est fourni, on filtre les personnes par nom
            List<Personne> personnes = personneService.findFirstByNom(nom); // Appel au service
            List<PersonneDTO> personneDTOs = personnes.stream()
                                                      .map(personneMapper::toDto) // Mapper
                                                      .toList();
            return ResponseEntity.ok(personneDTOs); // Retourner les DTO
        
    }
    

}
