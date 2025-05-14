package groupe7.indicateurs.domain.quartile.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.quartile.model.Quartile;
import groupe7.indicateurs.domain.quartile.repository.QuartileRepository;

@Service
public class QuartileDomainService {
    private final QuartileRepository quartileRepository;

    public QuartileDomainService(QuartileRepository quartileRepository) {
        this.quartileRepository = quartileRepository;
    }

    public void addQuartile(Quartile quartile) {
        quartileRepository.save(quartile);
    }

    public Optional<Quartile> findQuartileById(Long id) {
        return quartileRepository.findById(id);
    }

    public void removeQuartile(Long id) {
        quartileRepository.deleteById(id);
    }
}
