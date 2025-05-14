package groupe7.indicateurs.web.livre.controller;

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

import groupe7.indicateurs.application.livre.dto.LivreDTO;
import groupe7.indicateurs.application.livre.mapper.LivreMapper;
import groupe7.indicateurs.application.livre.service.LivreService;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;
import groupe7.indicateurs.domain.livre.model.Livre;



@RestController
@RequestMapping("/api/livres")
public class LivreController {

    private final LivreService livreService;
    private final LivreMapper livreMapper;

    public LivreController(LivreService livreService, LivreMapper livreMapper) {
        this.livreService = livreService;
        this.livreMapper = livreMapper;
    }

    @PostMapping
    public ResponseEntity<LivreDTO> createLivre(@RequestBody LivreDTO livreDTO) {
        Livre livre = livreMapper.toEntity(livreDTO);
        Livre savedLivre = livreService.save(livre);
        LivreDTO savedLivreDTO = livreMapper.toDto(savedLivre);
        return ResponseEntity.ok(savedLivreDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivreDTO> getLivreById(@PathVariable Long id) {
        Livre livre = livreService.findById(id)
                                              .orElseThrow(() -> new ResourceNotFoundException("Personne not found"));
        LivreDTO livreDTO = livreMapper.toDto(livre);
        return ResponseEntity.ok(livreDTO); 
    }

    @DeleteMapping("/{id}")
    public void deletePersonne(@PathVariable Long id) {
        livreService.deleteById(id);
    }

    @GetMapping("/year")
    public ResponseEntity<List<LivreDTO>> getLivresByYear(@RequestParam Year date1, @RequestParam Year date2) {
        List<Livre> livres = livreService.findByYear(date1, date2);
        List<LivreDTO> livreDTOs = livres.stream()
                                             .map(livreMapper::toDto)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(livreDTOs);
    }

    @GetMapping("/cpt")
    public ResponseEntity<Integer> countByYearAndCountry(@RequestParam Year date1, @RequestParam Year date2, @RequestParam String country) {
        Integer count = livreService.countByYearAndCountry(date1, date2, country);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/yearAndCountry")
    public ResponseEntity<List<LivreDTO>> getLivresByYearAndCountry(@RequestParam Year date1, @RequestParam Year date2, @RequestParam String country) {
        List<Livre> livres = livreService.findByYearAndCountry(date1, date2, country);
        List<LivreDTO> livreDTOs = livres.stream()
                                             .map(livreMapper::toDto)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(livreDTOs);
    }

    @GetMapping
    public ResponseEntity<List<LivreDTO>> getLivresByTitre(@RequestParam(name = "titre", required = false) String titre) {
        List<Livre> livres = livreService.findByTitre(titre);
        List<LivreDTO> livreDTOs = livres.stream()
                                             .map(livreMapper::toDto)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(livreDTOs);
    }
    
    @GetMapping("/team")
    public ResponseEntity<List<LivreDTO>> getLivresByTeam(@RequestParam(name = "team", required = false) String team) {
        List<Livre> livres = livreService.findByTeam(team);
        List<LivreDTO> livreDTOs = livres.stream()
                                             .map(livreMapper::toDto)
                                             .collect(Collectors.toList());
        return ResponseEntity.ok(livreDTOs);
    }
    
}
