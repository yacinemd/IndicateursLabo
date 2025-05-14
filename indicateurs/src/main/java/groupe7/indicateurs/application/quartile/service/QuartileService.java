package groupe7.indicateurs.application.quartile.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.quartile.model.Quartile;
import groupe7.indicateurs.domain.quartile.repository.QuartileRepository;

@Service
public class QuartileService {
    private final QuartileRepository quartileRepository;

    public QuartileService(QuartileRepository quartileRepository) {
        this.quartileRepository = quartileRepository;
    }

    public List<Quartile> findAll() {
        return quartileRepository.findAll();
    }

    public Optional<Quartile> findById(Long id) {
        return quartileRepository.findById(id);
    }

    public Quartile save(Quartile quartile) {
        return quartileRepository.save(quartile);
    }

    public void deleteById(Long id) {
        quartileRepository.deleteById(id);
    }

    public List<Map<String, Object>> findBestQuartilesByRevueId(Long id) {
        return quartileRepository.findBestQuartileByRevueId(id);
    }    

}
