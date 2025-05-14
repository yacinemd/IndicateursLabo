package groupe7.indicateurs.web.personnel.controller;

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

import groupe7.indicateurs.application.personnel.dto.PersonnelDTO;
import groupe7.indicateurs.application.personnel.mapper.PersonnelMapper;
import groupe7.indicateurs.application.personnel.service.PersonnelService;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;
import groupe7.indicateurs.domain.personnel.model.Personnel;

@RestController
@RequestMapping("/api/personnels")
public class PersonnelController {
    
    private final PersonnelService personnelService;
    private final PersonnelMapper personnelMapper;

    public PersonnelController(PersonnelService personnelService, PersonnelMapper personnelMapper) {
        this.personnelService = personnelService;
        this.personnelMapper = personnelMapper;
    }

    @PostMapping
    public ResponseEntity<PersonnelDTO> createPersonnel(@RequestBody PersonnelDTO personnelDTO) {
        Personnel personnel = personnelMapper.toEntity(personnelDTO);
        Personnel savedPersonnel = personnelService.save(personnel);
        PersonnelDTO savedPersonnelDTO = personnelMapper.toDto(savedPersonnel);
        return ResponseEntity.ok(savedPersonnelDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonnelDTO> getPersonnelById(@PathVariable Long id) {
        Personnel personnel = personnelService.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException("Personnel not found"));
        PersonnelDTO personnelDTO = personnelMapper.toDto(personnel);
        return ResponseEntity.ok(personnelDTO); 
    }

    @GetMapping()
    public ResponseEntity<List<PersonnelDTO>> getPersonnel() {
        List<PersonnelDTO> personnelDTOs = personnelService.findAll().stream().map(personnelMapper::toDto).toList();
        return ResponseEntity.ok(personnelDTOs); 
    }

    @DeleteMapping("/{id}")
    public void deletePersonnel(@PathVariable Long id) {
        personnelService.deleteById(id);
    }

    @GetMapping("/publiByTeam")
    public ResponseEntity<Integer> getPubliByTeam(@RequestParam String team) {
        int totalPublications = personnelService.getPubliByTeam(team);
        return ResponseEntity.ok(totalPublications);
    }

    @GetMapping("/articleByTeam")
    public ResponseEntity<Integer> getArticleByTeam(@RequestParam String team) {
        int totalArticles = personnelService.getArticleByTeam(team);
        return ResponseEntity.ok(totalArticles);
    }
    @GetMapping("/conferenceByTeam")
    public ResponseEntity<Integer> getConferenceByTeam(@RequestParam String team) {
        int totalConferences = personnelService.getConferenceByTeam(team);
        return ResponseEntity.ok(totalConferences);
    }
    @GetMapping("/livreByTeam")
    public ResponseEntity<Integer> getLivreByTeam(@RequestParam String team) {
        int totalLivres = personnelService.getLivreByTeam(team);
        return ResponseEntity.ok(totalLivres);
    }
    @GetMapping("/revueByTeam")
    public ResponseEntity<Integer> getRevueByTeam(@RequestParam String team) {
        int totalRevues = personnelService.getRevueByTeam(team);
        return ResponseEntity.ok(totalRevues);
    }

}
