package groupe7.indicateurs.domain.personnel.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import groupe7.indicateurs.domain.personnel.model.Personnel;
import groupe7.indicateurs.domain.personnel.repository.PersonnelRepository;

@Service
public class PersonnelDomainService {

    private final PersonnelRepository personnelRepository;

    public PersonnelDomainService(PersonnelRepository personnelRepository) {
        this.personnelRepository = personnelRepository;
    }

    @Transactional
    public void addPersonnel(Personnel personnel) {
        personnelRepository.save(personnel);
    }

    public Optional<Personnel> findPersonnelById(Long id) {
        return personnelRepository.findById(id);
    }

    public void removePersonnel(Long id) {
        personnelRepository.deleteById(id);
    }
}
