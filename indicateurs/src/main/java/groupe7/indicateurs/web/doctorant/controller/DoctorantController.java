package groupe7.indicateurs.web.doctorant.controller;

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

import groupe7.indicateurs.application.doctorant.dto.DoctorantDTO;
import groupe7.indicateurs.application.doctorant.mapper.DoctorantMapper;
import groupe7.indicateurs.application.doctorant.service.DoctorantService;
import groupe7.indicateurs.domain.doctorant.model.Doctorant;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/doctorants")
public class DoctorantController {
    
    private final DoctorantService doctorant_service;
    private final DoctorantMapper doctorant_mapper;

    public DoctorantController(DoctorantService doctorant_service, DoctorantMapper doctorant_mapper) {
        this.doctorant_service = doctorant_service;
        this.doctorant_mapper = doctorant_mapper;
    }

    @PostMapping
    public ResponseEntity<DoctorantDTO> createDoctorant(@RequestBody DoctorantDTO doctorant_dto) {
        Doctorant doctorant = doctorant_mapper.toEntity(doctorant_dto);
        Doctorant saved_doctorant = doctorant_service.save(doctorant);
        DoctorantDTO saved_doctorant_dto = doctorant_mapper.toDto(saved_doctorant);
        return ResponseEntity.ok(saved_doctorant_dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DoctorantDTO> getDoctorantById(@PathVariable Long id) {
        Doctorant doctorant = doctorant_service.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException("Doctorant not found"));
        DoctorantDTO doctorant_dto = doctorant_mapper.toDto(doctorant);
        return ResponseEntity.ok(doctorant_dto); 
    }

    @DeleteMapping("/{id}")
    public void deleteDoctorant(@PathVariable Long id) {
        doctorant_service.deleteById(id);
    }

    @GetMapping
public ResponseEntity<List<DoctorantDTO>> getDoctorantsNomByNomOrPrenom(
        @RequestParam(name = "nom", required = false) String nom,
        @RequestParam(name = "prenom", required = false) String prenom) {
    List<Doctorant> doctorants = doctorant_service.getDoctorantsNomByNomOrPrenom(nom, prenom);
    List<DoctorantDTO> doctorantDTOs = doctorants.stream()
                                                .map(doctorant_mapper::toDto) // Mapper
                                                .toList();
    return ResponseEntity.ok(doctorantDTOs);
}

}
