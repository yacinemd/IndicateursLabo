package groupe7.indicateurs.domain.personne.service;
import java.util.Optional;
import java.util.List;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;
import jakarta.transaction.Transactional;


@Service
public class PersonneDomainService {
    private final PersonneRepository personneRepository;

    public PersonneDomainService(PersonneRepository personneRepository) {
        this.personneRepository = personneRepository;
    }

    @Transactional
    public void addPersonne(Personne personne) {
        personneRepository.save(personne);
    }

    public Optional<Personne> findPersonneById(Long id) {
        return personneRepository.findById(id);
    }

    public List<Personne> findFirstByNom(String nom) {
        return personneRepository.findByNom(nom);
    }

    public List<Long>  findFirstIdByNom(String nom) {
        return personneRepository.findIdsByNom(nom);
    }

    public void removePersonne(Long id) {
        personneRepository.deleteById(id);
    }
    @Transactional
    public void saveAllPersonne(Iterable<Personne> personnes) {
        personneRepository.saveAll(personnes);
    }
}
