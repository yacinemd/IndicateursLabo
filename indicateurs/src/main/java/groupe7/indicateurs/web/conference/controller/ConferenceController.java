package groupe7.indicateurs.web.conference.controller;

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

import groupe7.indicateurs.application.conference.dto.ConferenceDTO;
import groupe7.indicateurs.application.conference.mapper.ConferenceMapper;
import groupe7.indicateurs.application.conference.service.ConferenceService;
import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/api/conferences")
public class ConferenceController {
    private final ConferenceService conferenceService;
    private final ConferenceMapper conferenceMapper;

    public ConferenceController(ConferenceService conferenceService, ConferenceMapper conferenceMapper) {
        this.conferenceService = conferenceService;
        this.conferenceMapper = conferenceMapper;
    }

    @PostMapping
    public ResponseEntity<ConferenceDTO> createConference(@RequestBody ConferenceDTO conferenceDTO) {
        Conference conference = conferenceMapper.toEntity(conferenceDTO);
        Conference savedConference = conferenceService.save(conference);
        ConferenceDTO savedConferenceDTO = conferenceMapper.toDto(savedConference);
        return ResponseEntity.ok(savedConferenceDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConferenceDTO> getConferenceById(@PathVariable Long id) {
        Conference conference = conferenceService.findById(id)
                                     .orElseThrow(() -> new ResourceNotFoundException("Conference not found"));
        ConferenceDTO conferenceDTO = conferenceMapper.toDto(conference);
        return ResponseEntity.ok(conferenceDTO); 
    }

    @DeleteMapping("/{id}")
    public void deleteConference(@PathVariable Long id) {
        conferenceService.deleteById(id);
    }

    @GetMapping
    public ResponseEntity<List<ConferenceDTO>> getConferencesByTitre(@RequestParam(name = "titre", required = false) String titre) {
        List<Conference> conferences = conferenceService.getConferencesByTitre(titre);
        List<ConferenceDTO> conferenceDTOs = conferences.stream()
                                            .map(conferenceMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(conferenceDTOs);
    }

    @GetMapping("/year")
    public ResponseEntity<List<ConferenceDTO>> getConferencesByYear(@RequestParam Year date1, @RequestParam Year date2) {
        List<Conference> conferences = conferenceService.getConferencesByYear(date1, date2);
        List<ConferenceDTO> conferenceDTOs = conferences.stream()
                                            .map(conferenceMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(conferenceDTOs);
    }

    @GetMapping("/cpt")
    public ResponseEntity<Integer> countByYearAndCountry(@RequestParam(name = "date1") Year date1,
                                                          @RequestParam(name = "date2") Year date2,
                                                          @RequestParam(name = "country") String country) {
        Integer count = conferenceService.countByYearAndCountry(date1, date2, country);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/yearAndCountry")
    public ResponseEntity<List<ConferenceDTO>> getConferencesByYearAndCountry(@RequestParam Year date1, @RequestParam Year date2, @RequestParam String country) {
        List<Conference> conferences = conferenceService.getConferencesByYearAndCountry(date1, date2, country);
        List<ConferenceDTO> conferenceDTOs = conferences.stream()
                                            .map(conferenceMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(conferenceDTOs);
    }

    @GetMapping("/team")
    public ResponseEntity<List<ConferenceDTO>> getConferencesByTeam(@RequestParam(name = "team", required = false) String team) {
        List<Conference> conferences = conferenceService.getConferencesByTeam(team);
        List<ConferenceDTO> conferenceDTOs = conferences.stream()
                                            .map(conferenceMapper::toDto)
                                            .collect(Collectors.toList());
        return ResponseEntity.ok(conferenceDTOs);
    }

}
