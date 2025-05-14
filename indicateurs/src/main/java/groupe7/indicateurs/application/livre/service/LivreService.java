package groupe7.indicateurs.application.livre.service;

import java.time.Year;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.livre.model.Livre;
import groupe7.indicateurs.domain.livre.repository.LivreRepository;

@Service
public class LivreService {
    private final LivreRepository livreRepository;

    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }


    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    public Optional<Livre> findById(Long id) {
        return livreRepository.findById(id);
    }
    
    public Livre save(Livre livre) {
       return livreRepository.save(livre);
    }

    public void deleteById(Long id) {
        livreRepository.deleteById(id);
    }

    public List<Livre> findByYear(Year date1, Year date2) {
        return livreRepository.findByYear(date1, date2);
    }

    public Integer countByYearAndCountry(Year date1,Year date2, String country) {
        return livreRepository.countByYearAndCountry(date1, date2 , country);
    }

    public List<Livre> findByYearAndCountry(Year date1, Year date2, String country) {
        return livreRepository.findByYearAndCountry(date1, date2, country);
    }

    public List<Livre> findByTitre(String titre) {
        return livreRepository.findByTitre(titre);
    }

    public List<Livre> findByTeam (String team) {
        return livreRepository.findByTeam(team);
    }
}
