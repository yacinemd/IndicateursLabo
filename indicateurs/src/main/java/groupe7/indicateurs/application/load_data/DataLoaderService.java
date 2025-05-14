package groupe7.indicateurs.application.load_data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import groupe7.indicateurs.domain.article.model.Article;
import groupe7.indicateurs.domain.article.repository.ArticleRepository;
import groupe7.indicateurs.domain.autre_poste.model.AutrePoste;
import groupe7.indicateurs.domain.autre_poste.repository.AutrePosteRepository;
import groupe7.indicateurs.domain.chercheur.model.Chercheur;
import groupe7.indicateurs.domain.chercheur.repository.ChercheurRepository;
import groupe7.indicateurs.domain.conference.model.Conference;
import groupe7.indicateurs.domain.conference.repository.ConferenceRepository;
import groupe7.indicateurs.domain.doctorant.model.Doctorant;
import groupe7.indicateurs.domain.doctorant.repository.DoctorantRepository;
import groupe7.indicateurs.domain.liaison_personne_article.model.LiaisonPersonneArticle;
import groupe7.indicateurs.domain.liaison_personne_article.repository.LiaisonPersonneArticleRepository;
import groupe7.indicateurs.domain.liaison_personne_conference.model.LiaisonPersonneConference;
import groupe7.indicateurs.domain.liaison_personne_conference.repository.LiaisonPersonneConferenceRepository;
import groupe7.indicateurs.domain.liaison_personne_livre.model.LiaisonPersonneLivre;
import groupe7.indicateurs.domain.liaison_personne_livre.repository.LiaisonPersonneLivreRepository;
import groupe7.indicateurs.domain.livre.model.Livre;
import groupe7.indicateurs.domain.livre.repository.LivreRepository;
import groupe7.indicateurs.domain.personne.model.Personne;
import groupe7.indicateurs.domain.personne.repository.PersonneRepository;
import groupe7.indicateurs.domain.personnel.model.Personnel;
import groupe7.indicateurs.domain.personnel.repository.PersonnelRepository;
import groupe7.indicateurs.domain.quartile.model.Quartile;
import groupe7.indicateurs.domain.quartile.repository.QuartileRepository;
import groupe7.indicateurs.domain.revue.model.Revue;
import groupe7.indicateurs.domain.revue.repository.RevueRepository;
import jakarta.annotation.PostConstruct;

@Service
public class DataLoaderService {

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private RevueRepository revueRepository;

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private LiaisonPersonneArticleRepository liaisonpaRepository;

    @Autowired
    private LiaisonPersonneConferenceRepository liaisonpcRepository;

    @Autowired
    private LiaisonPersonneLivreRepository liaisonplRepository;

    @Autowired
    private PersonnelRepository personnelRepository;

    @Autowired
    private ChercheurRepository chercheurRepository;

    @Autowired
    private DoctorantRepository doctorantRepository;

    @Autowired
    private AutrePosteRepository autrePosteRepository;


    @Autowired
    private QuartileRepository quartileRepository;

    @Autowired
    private ScimagoScraper scimagoScraper;


    private final String filePath = "Personnel_2024-09-25.xlsx";
    private final String url = "https://api.archives-ouvertes.fr/search/?q=imvia&wt=xml-tei&rows=1000000";
    private final String fileName = "resultats_imvia.xml";
    private final String xmlFilePath = "resultats_imvia.xml";




    @PostConstruct
    public void init() throws ParserConfigurationException, SAXException, IOException{
        this.fetchXmlData();
        this.loadPersonnelData(filePath);
        this.extraireInfosEtAfficher(xmlFilePath);
        this.loadLiaisonsXML(xmlFilePath);
    }


    public void fetchXmlData() {
        try {
            String response = getData(url);
            if (response != null) {
                writeToFile(response, fileName);            
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getData(String urlStr) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/xml");

        if (connection.getResponseCode() == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            return response.toString();
        } else {
            return null;
        }
    }

    public void writeToFile(String data, String fileName) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write(data);
        }
    }

    public void extraireInfosEtAfficher(String xmlPath) throws ParserConfigurationException, SAXException, IOException {
        File file = new File(xmlPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(new InputSource(new FileReader(file)));

        String ns = "http://www.tei-c.org/ns/1.0";

        // Mapping des org vers pays
        NodeList orgs = document.getElementsByTagNameNS(ns, "org");
        Map<String, String> orgIdToCountry = new HashMap<>();
        for (int i = 0; i < orgs.getLength(); i++) {
            Element org = (Element) orgs.item(i);
            String xmlId = org.getAttribute("xml:id");
            NodeList countries = org.getElementsByTagNameNS(ns, "country");
            if (countries.getLength() > 0) {
                String key = ((Element) countries.item(0)).getAttribute("key");
                if (key != null && !key.isEmpty()) {
                    orgIdToCountry.put(xmlId, key);
                }
            }
        }

        // Publications
        NodeList publications = document.getElementsByTagNameNS(ns, "biblFull");
        for (int i = 0; i < publications.getLength(); i++) {
            Element pub = (Element) publications.item(i);

            // Titre
            NodeList titleNodes = pub.getElementsByTagNameNS(ns, "title");
            String title = (titleNodes.getLength() > 0) ? titleNodes.item(0).getTextContent().trim() : "Titre inconnu";

            // Pays
            Set<String> countries = new HashSet<>();
            NodeList affiliations = pub.getElementsByTagNameNS(ns, "affiliation");
            for (int j = 0; j < affiliations.getLength(); j++) {
                Element aff = (Element) affiliations.item(j);
                String ref = aff.getAttribute("ref");
                if (ref != null && ref.startsWith("#")) {
                    String structId = ref.substring(1);
                    String country = orgIdToCountry.get(structId);
                    if (country != null) {
                        countries.add(country);
                    }
                }
            }
            String countryString = String.join(", ", countries);

            NodeList editionNodes = pub.getElementsByTagNameNS(ns, "edition");
            Year anneeProduction = null;
            for (int j = 0; j < editionNodes.getLength(); j++) {
                Element edition = (Element) editionNodes.item(j);
                NodeList dateNodes = edition.getElementsByTagNameNS(ns, "date");
                for (int k = 0; k < dateNodes.getLength(); k++) {
                    Element dateElem = (Element) dateNodes.item(k);
                    if ("whenProduced".equals(dateElem.getAttribute("type"))) {
                        String fullDate = dateElem.getTextContent().trim();
                        if (fullDate.length() >= 4) {
                            try {
                                anneeProduction = Year.parse(fullDate.substring(0, 4));
                            } catch (DateTimeException e) {
                            }
                        }
                        break;
                    }
                }
                if (anneeProduction != null) break;
            }

            NodeList journalNodes = pub.getElementsByTagNameNS(ns, "title");
                            String journal = "Journal inconnu";
                            for (int j = 0; j < journalNodes.getLength(); j++) {
                                Element journalElement = (Element) journalNodes.item(j);
                                if ("j".equals(journalElement.getAttribute("level"))) {
                                    journal = journalElement.getTextContent().trim();
                                    break;
                                }
                            }
        
                            NodeList imprintNodes = pub.getElementsByTagNameNS(ns, "imprint");
                            String publisher = "Publisher inconnu";
                            for (int j = 0; j < imprintNodes.getLength(); j++) {
                                Element imprintElement = (Element) imprintNodes.item(j);
                                NodeList publisherNodes = imprintElement.getElementsByTagNameNS(ns, "publisher");
                                if (publisherNodes.getLength() > 0) {
                                    publisher = publisherNodes.item(0).getTextContent().trim();
                                    break;
                                }
                            }

            // Type
            NodeList typeNodes = pub.getElementsByTagNameNS(ns, "classCode");
            String pubType = "Inconnu";
            for (int j = 0; j < typeNodes.getLength(); j++) {
                Element typeElement = (Element) typeNodes.item(j);
                if ("halTypology".equals(typeElement.getAttribute("scheme"))) {
                    String typeCode = typeElement.getAttribute("n");
                    switch (typeCode) {


                        case "COUV":
                            pubType = "Book";
                            Livre livre = new Livre();
                            livre.setTitre(title);
                            livre.setAnnee(anneeProduction);
                            livre.setPays(countryString);
                            livreRepository.save(livre);
                            break;

                        case "OUV":
                            pubType = "Book";
                            livre = new Livre();
                            livre.setTitre(title);
                            livre.setAnnee(anneeProduction);
                            livre.setPays(countryString);
                            livreRepository.save(livre);
                            break;

                        case "ART": //Quartile
                            pubType = "Article";
                            Revue revue = new Revue();

                            Optional<Revue> existingRevue = revueRepository.findByTitreAndPublisher(journal, publisher);
                            


                            if (existingRevue.isEmpty()) {
                                revue.setTitre(journal);
                                revue.setPublisher(publisher);
                                revue.setAnnee(anneeProduction);
                                revueRepository.save(revue);
                                Map<String, Map<String, String>> quartiles = scimagoScraper.fetchQuartiles(journal, publisher);

                              
                                for (Map.Entry<String, Map<String, String>> entry : quartiles.entrySet()) {
                                    String category = entry.getKey();
                                    for (Map.Entry<String, String> q : entry.getValue().entrySet()) {
                                        Quartile quartile= new Quartile();
                                        String year = q.getKey();
                                        String Quar = q.getValue();
                                        Year annee = Year.parse(year);
                                        quartile.setAnnee(annee);
                                        quartile.setDomaine(category);
                                        quartile.setQuartile(Quar);
                                        quartile.setRevue(revue);
                                        quartileRepository.save(quartile);
                                    }
                                }
                              
                                
                                
                            } else {
                                revue = existingRevue.get();
                            }

                            Article article = new Article();
                            article.setTitre(title);
                            article.setDate(anneeProduction);
                            article.setRevue(revue);
                            article.setPays(countryString);
                            articleRepository.save(article);
                            break;


                        case "COMM":
                            pubType = "Conference";
                            Conference conference = new Conference();
                            conference.setTitre(title);
                            conference.setAnnee(anneeProduction);
                            conference.setPays(countryString);
                            conferenceRepository.save(conference);
                            break;
                    }
                    break;
                }
            }
        }
    }




    @Transactional
    public void loadLiaisonsXML(String xmlPath) throws ParserConfigurationException, IOException, SAXException {
        File file = new File(xmlPath);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        String ns = "http://www.tei-c.org/ns/1.0";
        NodeList publications = document.getElementsByTagNameNS(ns, "biblFull");

        for (int i = 0; i < publications.getLength(); i++) {
            Element pub = (Element) publications.item(i);

            // Titre
            NodeList titleNodes = pub.getElementsByTagNameNS(ns, "title");
            String titre = (titleNodes.getLength() > 0) ? titleNodes.item(0).getTextContent().trim() : null;

            // Type
            NodeList typeNodes = pub.getElementsByTagNameNS(ns, "classCode");
            String pubType = "Inconnu";
            for (int j = 0; j < typeNodes.getLength(); j++) {
                Element typeElement = (Element) typeNodes.item(j);
                if ("halTypology".equals(typeElement.getAttribute("scheme"))) {
                    pubType = typeElement.getAttribute("n");
                    break;
                }
            }

            // Auteurs
            NodeList authorNodes = pub.getElementsByTagNameNS(ns, "author");
            for (int j = 0; j < authorNodes.getLength(); j++) {
                Element author = (Element) authorNodes.item(j);
                NodeList persNames = author.getElementsByTagNameNS(ns, "persName");
                if (persNames.getLength() > 0) {
                    Element persName = (Element) persNames.item(0);

                    String forename = "";
                    String surname = "";

                    NodeList forenames = persName.getElementsByTagNameNS(ns, "forename");
                    if (forenames.getLength() > 0)
                        forename = forenames.item(0).getTextContent().trim();

                    NodeList surnames = persName.getElementsByTagNameNS(ns, "surname");
                    if (surnames.getLength() > 0)
                        surname = surnames.item(0).getTextContent().trim();

                    if (!surname.isEmpty()) {
                        String nom = surname.toUpperCase();
                        String prenom = forename;
                        List<Personne> personnes = personneRepository.findByNomAndPrenom(nom, prenom);

                        if (!personnes.isEmpty()) {
                            Personne personne = personnes.get(0);

                            switch (pubType) {
                                case "ART":
                                    List<Article> articles = articleRepository.findByTitre(titre);
                                    if (!articles.isEmpty()) {
                                        Article article = articles.get(0);
                                        if (!liaisonpaRepository.existsByPersonneAndArticle(personne, article)) {
                                            LiaisonPersonneArticle liaison = new LiaisonPersonneArticle(personne, article);
                                            liaisonpaRepository.save(liaison);
                                        }
                                    }
                                    break;

                                case "COMM":
                                    List<Conference> conferences = conferenceRepository.findByTitre(titre);
                                    if (!conferences.isEmpty()) {
                                        Conference conf = conferences.get(0);
                                        if (!liaisonpcRepository.existsByPersonneAndConference(personne, conf)) {
                                            LiaisonPersonneConference liaison = new LiaisonPersonneConference(personne, conf);
                                            liaisonpcRepository.save(liaison);
                                        }
                                    }
                                    break;

                                case "COUV":
                                    List<Livre> livres = livreRepository.findByTitre(titre);
                                    if (!livres.isEmpty()) {
                                        Livre livre = livres.get(0);
                                        if (!liaisonplRepository.existsByPersonneAndLivre(personne, livre)) {
                                            LiaisonPersonneLivre liaison = new LiaisonPersonneLivre(personne, livre);
                                            liaisonplRepository.save(liaison);
                                        }
                                    }
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    @Transactional
    public void loadPersonnelData(String filePath) {
        try {
            // Charger le fichier depuis le classpath
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();

            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                for (Sheet sheet : workbook) { // Parcours de chaque feuille
                    for (Row row : sheet) { // Parcours de chaque ligne
                        if (row.getRowNum() == 0) { // Ignorer la ligne d'entête
                            continue;
                        }

                        // Lecture des cellules
                        String nom = row.getCell(1) != null ? row.getCell(1).getStringCellValue() : "";
                        String prenom = row.getCell(2) != null ? row.getCell(2).getStringCellValue() : "";
                        Boolean hdr = row.getCell(5) != null;

                        LocalDate dateDebut = null;
                        if (row.getCell(6) != null && row.getCell(6).getCellType() == CellType.NUMERIC) {
                            LocalDateTime localDateTime = row.getCell(6).getLocalDateTimeCellValue();
                            dateDebut = localDateTime.toLocalDate();
                        }

                        LocalDate dateFin = null;
                        if (row.getCell(7) != null && row.getCell(7).getCellType() == CellType.NUMERIC) {
                            LocalDateTime localDateTime2 = row.getCell(7).getLocalDateTimeCellValue();
                            dateFin = localDateTime2.toLocalDate();
                        }

                        String statut = row.getCell(8) != null ? row.getCell(8).getStringCellValue() : "";
                        Boolean titulaire = row.getCell(9) != null && "Titulaire".equalsIgnoreCase(row.getCell(9).getStringCellValue());
                        String equipe = row.getCell(11) != null ? row.getCell(11).getStringCellValue() : "";

                        LocalDate dateSoutenance = null;
                        if (row.getCell(12) != null && row.getCell(12).getCellType() == CellType.NUMERIC) {
                            LocalDateTime localDateTime3 = row.getCell(12).getLocalDateTimeCellValue();
                            dateSoutenance = localDateTime3.toLocalDate();
                        }

                        // Création et sauvegarde des entités
                        Personne personne = new Personne();
                        personne.setNom(nom);
                        personne.setPrenom(prenom);
                        personne.setHdr(hdr);


                        Personnel personnel = new Personnel(statut, equipe, titulaire, dateDebut, dateFin);
                        personnel.setPersonne(personne); // Associer à Personne
                        

                        // Sauvegarde d'autres entités si nécessaire
                        if (personnel.getStatut().equalsIgnoreCase("Enseignant chercheur"))
                        {
                            Chercheur chercheur = new Chercheur();
                            chercheur.setPersonnel(personnel);
                            chercheurRepository.save(chercheur);
                        }
                        
                        if (personnel.getStatut().equalsIgnoreCase("Doctorant"))
                        {
                            Doctorant doctorant = new Doctorant(dateSoutenance,null,personnel);
                            doctorantRepository.save(doctorant);
                        }

                        if (personnel.getStatut().equalsIgnoreCase("Post-doctorant"))
                        {
                            AutrePoste autrePoste = new AutrePoste();
                            autrePoste.setPersonnel(personnel);
                            autrePoste.setTitre(personnel.getStatut());
                            autrePosteRepository.save(autrePoste);
                        }    
                        personnelRepository.save(personnel);
                        personneRepository.save(personne);

                    }
                }

                System.out.println("Données insérées avec succès dans la base de données.");
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }

        try {
            // Charger le fichier depuis le classpath
            ClassPathResource resource = new ClassPathResource(filePath);
            InputStream inputStream = resource.getInputStream();

            try (Workbook workbook = new XSSFWorkbook(inputStream)) {
                for (Sheet sheet : workbook) { // Parcours de chaque feuille
                    for (Row row : sheet) { // Parcours de chaque ligne
                        if (row.getRowNum() == 0) { // Ignorer la ligne d'entête
                            continue;
                        }
                        if (row.getCell(8) != null && "Doctorant".equalsIgnoreCase(row.getCell(8).getStringCellValue().trim())) {
                            if (row.getCell(13) != null && row.getCell(13).getCellType() == CellType.STRING) {
                                String cellValue = row.getCell(13).getStringCellValue().trim();
                        
                                if (!cellValue.isEmpty()) {
                                    String nom = row.getCell(1) != null ? row.getCell(1).getStringCellValue().trim() : "";
                                    String prenom = row.getCell(2) != null ? row.getCell(2).getStringCellValue().trim() : "";
                        
                                    List<Long> idDoctorant = personneRepository.findIdByNomAndPrenom(nom, prenom);
                        
                                    if (!idDoctorant.isEmpty()) {
                                        doctorantRepository.updateEncadrant(idDoctorant.get(0), cellValue);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Erreur lors de la lecture du fichier : " + e.getMessage());
        }
    }
}




