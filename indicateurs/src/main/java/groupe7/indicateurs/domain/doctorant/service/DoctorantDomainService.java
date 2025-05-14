package groupe7.indicateurs.domain.doctorant.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.doctorant.model.Doctorant;
import groupe7.indicateurs.domain.doctorant.repository.DoctorantRepository;
import jakarta.transaction.Transactional;

@Service
public class DoctorantDomainService {
    private final DoctorantRepository doctorantRepository;

    public DoctorantDomainService(DoctorantRepository doctorantRepository) {
        this.doctorantRepository = doctorantRepository;
    }

    @Transactional
    public void addDoctorant(Doctorant doctorant) {
        doctorantRepository.save(doctorant);
    }

    public void updateDoctorant(Doctorant doctorant) {
        doctorantRepository.save(doctorant);
    }

    @Transactional
    public void updateEncadrant(Long id, String Encadrant) {
        doctorantRepository.updateEncadrant(id, Encadrant);
    }

    public Optional<Doctorant> findDoctorantById(Long id) {
        return doctorantRepository.findById(id);
    }

    @Transactional
    public void removeDoctorant(Long id) {
        doctorantRepository.deleteById(id);
    }
}