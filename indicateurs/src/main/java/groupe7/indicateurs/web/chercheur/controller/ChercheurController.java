package groupe7.indicateurs.web.chercheur.controller;

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

import groupe7.indicateurs.application.chercheur.dto.ChercheurDTO;
import groupe7.indicateurs.application.chercheur.mapper.ChercheurMapper;
import groupe7.indicateurs.application.chercheur.service.ChercheurService;
import groupe7.indicateurs.domain.chercheur.model.Chercheur;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/chercheurs")
public class ChercheurController {
    
    private final ChercheurService chercheur_service;
    private final ChercheurMapper chercheur_mapper;

    public ChercheurController(ChercheurService chercheur_service, ChercheurMapper chercheur_mapper) {
        this.chercheur_service = chercheur_service;
        this.chercheur_mapper = chercheur_mapper;
    }

    @PostMapping
    public ResponseEntity<ChercheurDTO> createChercheur(@RequestBody ChercheurDTO chercheur_dto) {
        Chercheur chercheur = chercheur_mapper.toEntity(chercheur_dto);
        Chercheur saved_chercheur = chercheur_service.save(chercheur);
        ChercheurDTO saved_chercheur_dto = chercheur_mapper.toDto(saved_chercheur);
        return ResponseEntity.ok(saved_chercheur_dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChercheurDTO> getChercheurById(@PathVariable Long id) {
        Chercheur chercheur = chercheur_service.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException("Chercheur not found"));
        ChercheurDTO chercheur_dto = chercheur_mapper.toDto(chercheur);
        return ResponseEntity.ok(chercheur_dto); 
    }

    @DeleteMapping("/{id}")
    public void deleteChercheur(@PathVariable Long id) {
        chercheur_service.deleteById(id);
    }

@GetMapping
public ResponseEntity<List<ChercheurDTO>> getChercheursNomByNomOrPrenom(
        @RequestParam(name = "nom", required = false) String nom,
        @RequestParam(name = "prenom", required = false) String prenom) {
    List<Chercheur> chercheurs = chercheur_service.getChercheursNomByNomOrPrenom(nom, prenom);
    List<ChercheurDTO> chercheurDTOs = chercheurs.stream()
                                                      .map(chercheur_mapper::toDto) // Mapper
                                                      .toList();
    return ResponseEntity.ok(chercheurDTOs);
}




}