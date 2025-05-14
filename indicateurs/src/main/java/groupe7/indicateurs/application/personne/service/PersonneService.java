package groupe7.indicateurs.application.personne.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;

@Service
public class PersonneService {
    private final PersonneRepository personneRepository;

    public PersonneService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public Optional<Personne> findById(Long id) {
        return personneRepository.findById(id);
    }

    public Personne save(Personne personne) {
        return personneRepository.save(personne);
    }

    public void deleteById(Long id) {
        personneRepository.deleteById(id);
    }

    public List<Personne> findFirstByNom(String nom) {
        return personneRepository.findByNom(nom); 
    }
}
