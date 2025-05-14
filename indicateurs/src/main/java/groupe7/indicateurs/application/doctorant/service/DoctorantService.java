package groupe7.indicateurs.application.doctorant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.doctorant.model.Doctorant;
import groupe7.indicateurs.domain.doctorant.repository.DoctorantRepository;

@Service
public class DoctorantService {

    private final DoctorantRepository doctorantRepository;

    public DoctorantService(DoctorantRepository doctorantRepository) {
        this.doctorantRepository = doctorantRepository;
    }

    public List<Doctorant> findAll() {
        return doctorantRepository.findAll();
    }

    public Optional<Doctorant> findById(Long id) {
        return doctorantRepository.findById(id);
    }

    public Doctorant save(Doctorant doctorant) {
        return doctorantRepository.save(doctorant);
    }

    public void deleteById(Long id) {
        doctorantRepository.deleteById(id);
    }

    public List<Doctorant> getDoctorantsNomByNomOrPrenom(String nom, String prenom) {
        return doctorantRepository.findByNomOrPrenom(nom, prenom);
    }
    
}
