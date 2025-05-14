package groupe7.indicateurs.web.quartile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import groupe7.indicateurs.application.quartile.dto.QuartileDTO;
import groupe7.indicateurs.application.quartile.mapper.QuartileMapper;
import groupe7.indicateurs.application.quartile.service.QuartileService;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;
import groupe7.indicateurs.domain.quartile.model.Quartile;

@RestController
@RequestMapping("/api/quartiles")
public class QuartileController {

    private final QuartileService quartileService;
    private final QuartileMapper quartileMapper;

    public QuartileController(QuartileService quartileService, QuartileMapper quartileMapper) {
        this.quartileService = quartileService;
        this.quartileMapper = quartileMapper;
    }

    @PostMapping
    public ResponseEntity<QuartileDTO> createQuartile(@RequestBody QuartileDTO quartileDTO) {
        Quartile quartile = quartileMapper.toEntity(quartileDTO);
        Quartile savedQuartile = quartileService.save(quartile);
        QuartileDTO savedDTO = quartileMapper.toDto(savedQuartile);
        return ResponseEntity.ok(savedDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuartileDTO> getQuartileById(@PathVariable Long id) {
        Quartile quartile = quartileService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Quartile not found"));
        return ResponseEntity.ok(quartileMapper.toDto(quartile));
    }

    @DeleteMapping("/{id}")
    public void deleteQuartile(@PathVariable Long id) {
        quartileService.deleteById(id);
    }

    @GetMapping("/bestbyrevue/{id}")
    public ResponseEntity<?> getBestQuartilesByRevueId(@PathVariable Long id) {
        return ResponseEntity.ok(quartileService.findBestQuartilesByRevueId(id));
    }


}
