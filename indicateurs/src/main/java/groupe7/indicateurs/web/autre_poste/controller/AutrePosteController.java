package groupe7.indicateurs.web.autre_poste.controller;

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

import groupe7.indicateurs.application.autre_poste.dto.AutrePosteDTO;
import groupe7.indicateurs.application.autre_poste.mapper.AutrePosteMapper;
import groupe7.indicateurs.application.autre_poste.service.AutrePosteService;
import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/autrepostes")
public class AutrePosteController {

    private final AutrePosteService autrePosteService;
    private final AutrePosteMapper autrePosteMapper;

    public AutrePosteController(AutrePosteService autrePosteService, AutrePosteMapper autrePosteMapper) {
        this.autrePosteService = autrePosteService;
        this.autrePosteMapper = autrePosteMapper;
    }

    @PostMapping
    public ResponseEntity<AutrePosteDTO> createAutrePoste(@RequestBody AutrePosteDTO autrePosteDTO) {
        AutrePoste autrePoste = autrePosteMapper.toEntity(autrePosteDTO);
        AutrePoste savedAutrePoste = autrePosteService.save(autrePoste);
        AutrePosteDTO savedAutrePosteDTO = autrePosteMapper.toDto(savedAutrePoste);
        return ResponseEntity.ok(savedAutrePosteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutrePosteDTO> getAutrePosteById(@PathVariable Long id) {
        AutrePoste autrePoste = autrePosteService.findById(id)
                                                  .orElseThrow(() -> new ResourceNotFoundException("AutrePoste not found"));
        AutrePosteDTO autrePosteDTO = autrePosteMapper.toDto(autrePoste);
        return ResponseEntity.ok(autrePosteDTO); 
    }

    @DeleteMapping("/{id}")
    public void deleteAutrePoste(@PathVariable Long id) {
        autrePosteService.deleteById(id);
    }
    @GetMapping
    public ResponseEntity<List<AutrePosteDTO>> getAutresPostesByNomOrPrenom(
            @RequestParam(name = "nom", required = false) String nom,
            @RequestParam(name = "prenom", required = false) String prenom) {
        List<AutrePoste> autresPostes = autrePosteService.getAutresPostesByNomOrPrenom(nom, prenom);
        List<AutrePosteDTO> autresPosteDTOs = autresPostes.stream()
                                                          .map(autrePosteMapper::toDto)
                                                          .toList();
        return ResponseEntity.ok(autresPosteDTOs);
    }
}
