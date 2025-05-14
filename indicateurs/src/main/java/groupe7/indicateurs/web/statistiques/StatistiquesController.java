package groupe7.indicateurs.web.statistiques;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import groupe7.indicateurs.application.statistique.StatistiquesService;

@RestController
@RequestMapping("/api/statistiques")
public class StatistiquesController {
    
    private final StatistiquesService statistiquesService;

    public StatistiquesController(StatistiquesService statistiquesService) {
        this.statistiquesService = statistiquesService;
    }

    @GetMapping("/personnel")
    public Object[] getPersonnelStats() {
        return statistiquesService.getPersonnelStats();
    }

    @GetMapping("/articles")
    public List<Object[]> getArticlesStats() {
        return statistiquesService.getArticlesStats();
    }

    @GetMapping("/conferences")
    public List<Object[]> getConferencesStats() {
        return statistiquesService.getConferencesStats();
    }

    @GetMapping("/livres")
    public List<Object[]> getLivresStats() {
        return statistiquesService.getLivresStats();
    }

    @GetMapping("/quartiles/search")
    public List<Map<String, Object>> searchRevueQuartiles(@RequestParam Long id) {
        return statistiquesService.getRevueQuartileFiltered(id);
    }
    


}