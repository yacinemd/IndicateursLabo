package groupe7.indicateurs.web.projet.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupe7.indicateurs.application.projet.dto.ProjetDTO;
import groupe7.indicateurs.application.projet.mapper.ProjetMapper;
import groupe7.indicateurs.application.projet.service.ProjetService;
import groupe7.indicateurs.domain.projet.model.Projet;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/projets")
public class ProjetController {
    private final ProjetService projetService;
    private final ProjetMapper projetMapper;

    public ProjetController(ProjetService projetService, ProjetMapper projetMapper) {
        this.projetService = projetService;
        this.projetMapper = projetMapper;
    }

    @PostMapping
    public ResponseEntity<ProjetDTO> createProjet(@RequestBody ProjetDTO projetDTO) {
        Projet projet = projetMapper.toEntity(projetDTO);
        Projet savedProjet = projetService.save(projet);
        ProjetDTO savedProjetDTO = projetMapper.toDto(savedProjet);
        return ResponseEntity.ok(savedProjetDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProjetDTO> getProjetById(@PathVariable Long id) {
        Projet projet = projetService.findById(id)
                                     .orElseThrow(() -> new ResourceNotFoundException("Projet not found"));
        ProjetDTO projetDTO = projetMapper.toDto(projet);
        return ResponseEntity.ok(projetDTO); 
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetService.deleteById(id);
    }
}
