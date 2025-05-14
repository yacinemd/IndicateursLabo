package groupe7.indicateurs.domain.conference.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.conference.repository.ConferenceRepository;

@Service
public class ConferenceDomainService {

    private final ConferenceRepository conferenceRepository;

    public ConferenceDomainService(ConferenceRepository conferenceRepository) {
        this.conferenceRepository = conferenceRepository;
    }

    public void addConference(Conference conference) {
        conferenceRepository.save(conference);
    }

    public Optional<Conference> getConferenceById(Long id) {
        return conferenceRepository.findById(id);
    }

    public void removeConference(Long id) {
        conferenceRepository.deleteById(id);
    }



}
