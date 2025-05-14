package groupe7.indicateurs.web.revue.controller;

import java.time.Year;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupe7.indicateurs.application.revue.dto.RevueDTO;
import groupe7.indicateurs.application.revue.mapper.RevueMapper;
import groupe7.indicateurs.application.revue.service.RevueService;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;
import groupe7.indicateurs.domain.revue.model.Revue;

@RestController
@RequestMapping("/api/revues")
public class RevueController {
    private final RevueService revueService;
    private final RevueMapper revueMapper;

    public RevueController(RevueService revueService, RevueMapper revueMapper) {
        this.revueService = revueService;
        this.revueMapper = revueMapper;
    }

    @PostMapping
    public ResponseEntity<RevueDTO> createRevue(@RequestBody RevueDTO revueDTO) {
        Revue revue = revueMapper.toEntity(revueDTO);
        Revue savedRevue = revueService.save(revue);
        RevueDTO savedRevueDTO = revueMapper.toDto(savedRevue);
        return ResponseEntity.ok(savedRevueDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RevueDTO> getRevueById(@PathVariable Long id) {
        Revue revue = revueService.findById(id)
                                  .orElseThrow(() -> new ResourceNotFoundException("Revue not found"));
        RevueDTO revueDTO = revueMapper.toDto(revue);
        return ResponseEntity.ok(revueDTO); 
    }

    @GetMapping("/id")
    public Long getRevueIdByTitreAndPublisher(
            @RequestParam String titre,
            @RequestParam String publisher) {
        return revueService.getRevueIdByTitreAndPublisher(titre, publisher);
    }

    @DeleteMapping("/{id}")
    public void deleteRevue(@PathVariable Long id) {
        revueService.deleteById(id);
    }

    @GetMapping("/year")
    public ResponseEntity<List<RevueDTO>> getRevuesByYear(@RequestParam Year year) {
        List<Revue> revues = revueService.getRevuesByYear(year);
        List<RevueDTO> revueDTOs = revues.stream()
                                            .map(revueMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(revueDTOs);
    }
    
    @GetMapping("/titre")
    public ResponseEntity<List<RevueDTO>> getRevuesByTitre(@RequestParam String titre) {
        List<Revue> revues = revueService.getRevuesByTitre(titre);
        List<RevueDTO> revueDTOs = revues.stream()
                                            .map(revueMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(revueDTOs);
    }
 
    @GetMapping("/publisher")
    public ResponseEntity<List<RevueDTO>> getRevuesByPublisher(@RequestParam String publisher) {
        List<Revue> revues = revueService.getRevuesByPublisher(publisher);
        List<RevueDTO> revueDTOs = revues.stream()
                                            .map(revueMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(revueDTOs);
    }

    @GetMapping("/titreOrPublisher")
    public ResponseEntity<List<RevueDTO>> findByTitreOrPublisher(@RequestParam String queryparam) {
        List<Revue> revues = revueService.findByTitreOrPublisher(queryparam);
        List<RevueDTO> revueDTOs = revues.stream()
                                            .map(revueMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(revueDTOs);
    }

    @GetMapping("/equipe")
    public ResponseEntity<List<RevueDTO>> findRevuesByEquipe(@RequestParam String equipe) {
        List<Revue> revues = revueService.findRevuesByEquipe(equipe);
        List<RevueDTO> revueDTOs = revues.stream()
                                            .map(revueMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(revueDTOs);
    }

}
