package groupe7.indicateurs.application.statistique;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import groupe7.indicateurs.domain.article.repository.ArticleRepository;
import groupe7.indicateurs.domain.personnel.repository.PersonnelRepository;
import groupe7.indicateurs.domain.quartile.repository.QuartileRepository;
import groupe7.indicateurs.domain.conference.repository.ConferenceRepository;
import groupe7.indicateurs.domain.livre.repository.LivreRepository;

@Service
public class StatistiquesService {
    
    private final PersonnelRepository personnelRepository;
    private final ArticleRepository articleRepository;
    private final QuartileRepository quartileRepository;
    private final ConferenceRepository conferenceRepository;
    private final LivreRepository livreRepository;

    public StatistiquesService(PersonnelRepository personnelRepository, ArticleRepository articleRepository, QuartileRepository quartileRepository ,ConferenceRepository conferenceRepository , LivreRepository livreRepository) {
        this.personnelRepository = personnelRepository;
        this.articleRepository = articleRepository;
        this.quartileRepository = quartileRepository;
        this.conferenceRepository = conferenceRepository;
        this.livreRepository = livreRepository;
    }

    
    public Object[] getPersonnelStats() {
        return personnelRepository.countPersonnelTypes();
    }

    public List<Object[]> getArticlesStats() {
        return articleRepository.countArticlesByYear();
    }

    public List<Object[]> getConferencesStats() {
        return conferenceRepository.countConferencesByYear();
    }

    public List<Object[]> getLivresStats() {
        return livreRepository.countLivresByYear();
    }

    public void afficherStatistiques() {
        Object[] personnelCounts = personnelRepository.countPersonnelTypes();
        

        List<Object[]> articlesByYear = articleRepository.countArticlesByYear();
        for (Object[] row : articlesByYear) {
            System.out.println("Année : " + row[0] + " | Articles : " + row[1]);
        }
    }

    
    public List<Map<String, Object>> getRevueQuartileFiltered(Long id) {
        return quartileRepository.findQuartileByRevueId(id);
    }
    
    
    
}