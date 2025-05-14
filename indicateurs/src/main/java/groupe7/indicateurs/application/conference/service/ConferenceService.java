package groupe7.indicateurs.application.conference.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.conference.repository.ConferenceRepository;

@Service
public class ConferenceService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public List<Conference> findAll() {
        return conferenceRepository.findAll();
    }

    public Optional<Conference> findById(Long id) {
        return conferenceRepository.findById(id);
    }

    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    public void deleteById(Long id) {
        conferenceRepository.deleteById(id);
    }
    public List<Conference> getConferencesByTitre(String titre) {
        return conferenceRepository.findByTitre(titre);
    }

    public List<Conference> getConferencesByYear(Year date1, Year date2) {
        return conferenceRepository.findByYear(date1, date2);
    }

    public Integer countByYearAndCountry(Year date1, Year date2, String country) {
        return conferenceRepository.countByYearAndCountry(date1, date2, country);
    }

    public List<Conference> getConferencesByYearAndCountry(Year date1, Year date2, String country) {
        return conferenceRepository.findByYearAndCountry(date1, date2, country);
    }
    
    public List<Conference> getConferencesByTeam (String team) {
        return conferenceRepository.findConferencesByTeam(team);
    }


}
