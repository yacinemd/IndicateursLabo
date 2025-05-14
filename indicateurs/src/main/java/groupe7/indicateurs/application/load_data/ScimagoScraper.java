package groupe7.indicateurs.application.load_data;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ScimagoScraper {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) "
            + "AppleWebKit/537.36 (KHTML, like Gecko) "
            + "Chrome/92.0.4515.159 Safari/537.36";

    public Map<String, Map<String, String>> fetchQuartiles(String title, String publisher) {
        Map<String, Map<String, String>> quartileAnnees = new HashMap<>();

        try {
            String searchQuery = title + " " + publisher;
            String encodedQuery = URLEncoder.encode(searchQuery, StandardCharsets.UTF_8);
            String searchUrl = "https://www.scimagojr.com/journalsearch.php?q=" + encodedQuery;

            Document searchDoc = Jsoup.connect(searchUrl)
                    .userAgent(USER_AGENT)
                    .get();

            Element resultLink = searchDoc.selectFirst("div.search_results a");
            if (resultLink == null) return quartileAnnees;

            String href = resultLink.attr("href");
            String journalUrl = "https://www.scimagojr.com/" + href;

            Document doc = Jsoup.connect(journalUrl)
                    .userAgent(USER_AGENT)
                    .get();

            Element titleElement = doc.select("div.celltitle:contains(Quartiles)").first();
            if (titleElement == null) return quartileAnnees;

            Element container = titleElement;
            for (int i = 0; i < 5 && container != null; i++) {
                container = container.parent();
            }

            if (container == null) return quartileAnnees;

            Element table = container.selectFirst("table");
            if (table == null) return quartileAnnees;

            Elements rows = table.select("tr");
            for (Element row : rows) {
                Elements cells = row.select("td");
                if (cells.size() >= 3) {
                    String category = cells.get(0).text().trim();
                    String year = cells.get(1).text().trim();
                    String quartile = cells.get(2).text().trim();

                    quartileAnnees
                        .computeIfAbsent(category, k -> new HashMap<>())
                        .put(year, quartile);
                }
            }

        } catch (Exception e) {
            // tu peux logger ici si tu veux
        }

        return quartileAnnees;
    }
}
