// This code will scrap the job offers and save them in an excel file
// The excel file name is set to: output.xlsx

package system;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ScrapAndSaveToExcel {

    public static String ReturnDatePublication(String DP){
        String[] textDP = DP.split(" ");
        String[] numberDate = textDP[2].split("\\.");
        String datePub = String.join("/", numberDate[0], numberDate[1], numberDate[2]);
        return datePub;
    }
    public static void main(String[] args) throws IOException {
        List<String> urls = new ArrayList<>();
        // Adding numbers from 1 to 30 to the list
        for (int i = 1; i <= 24; i++) {
            urls.add(String.valueOf(i));
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Scraped Data");
        int rowNum = 0;
        Row row = sheet.createRow(rowNum++);
        // Put the titles of the colones:
        row.createCell(0).setCellValue("Titre");
        row.createCell(1).setCellValue("OffreLink");
        row.createCell(2).setCellValue("SiteName");
        row.createCell(3).setCellValue("DatePublication");
        row.createCell(4).setCellValue("ApplyDate"); // Postuler Date
        row.createCell(5).setCellValue("CompanyAdresse"); // Adresse d'entreprise
        row.createCell(6).setCellValue("WebSiteCompany");
        row.createCell(7).setCellValue("CompanyName");
        row.createCell(8).setCellValue("ComapanyDescription");
        row.createCell(9).setCellValue("OffreDescription"); // Poste Description
        row.createCell(10).setCellValue("Region");
        row.createCell(11).setCellValue("City");
        row.createCell(12).setCellValue("Sectors");
        row.createCell(13).setCellValue("Occupation");
        row.createCell(14).setCellValue("ContractType");
        row.createCell(15).setCellValue("EducationLevel");
        row.createCell(16).setCellValue("Diploma"); // Diploma/Specialty
        row.createCell(17).setCellValue("ExperienceLevel");
        row.createCell(18).setCellValue("SearchedProfile");
        row.createCell(19).setCellValue("PersonalityTraits"); // PersonalityTraits
        row.createCell(20).setCellValue("HardSkills"); // HardSkills
        row.createCell(21).setCellValue("SoftSkills"); // SoftSkills
        row.createCell(22).setCellValue("RecommandedCompetence"); // Recommanded Competence
        row.createCell(23).setCellValue("Langues"); // Langue > niveau
        row.createCell(24).setCellValue("Salary"); // Salary
        row.createCell(25).setCellValue("SocialAdvantages"); // Avantages Sociaux
        row.createCell(26).setCellValue("RemoteWork"); // Télétravail
        row.createCell(27).setCellValue("NumberOfPosts");

         // -------------------------------
        for(String numberURL : urls){
            String url = "https://www.emploi.ma/recherche-jobs-maroc?page=" + numberURL;
            try {
                Document doc = Jsoup.connect(url).get();
                Elements offres = doc.select(".row");


                System.out.println("\n----------------------------------------------------------------");
                System.out.println("Jobs in Emploi.com");
                for(Element offre : offres){
                    String OffreLink = offre.select("h5 > a").attr("href");
                    Document urlSite = Jsoup.connect("https://www.emploi.ma/" + OffreLink).get(); // URL
                    String Titre = urlSite.select("h1.title" ).text(); // Titre
                    String SiteName = "Emploi.ma"; // Site Name
                    String DatePublication = ReturnDatePublication(urlSite.select(".job-ad-publication-date" ).text()); // Date de publication
                    String Company = urlSite.select(".company-title").text(); // Nom d'entreprise
                    String WebSiteCompany = urlSite.select(".website-url").text(); // website d'entreprise
                    Elements InfoDuProfile = urlSite.select("li[role=presentation]");

                    // Description:
                    String urlDescription =  urlSite.select(".job-ad-company-description a").attr("href");
                    Document docDescription = Jsoup.connect("https://www.emploi.ma/" + urlDescription).get();
                    String Description = docDescription.select(".company-profile-description").text();
                    String delimiter = "Description de l'entreprise";
                    int startIndex = Description.indexOf(delimiter);
                    if (startIndex != -1) {
                        // Add the length of the delimiter to get the start index of the remaining text
                        startIndex += delimiter.length();

                        // Extract the rest of the text
                        Description = Description.substring(startIndex);
                    }
                    // ---------------------

                    // Criterie:
                    Elements criteriaElements = urlSite.select(".job-ad-criteria tr");
                    String nombreDePoste = urlSite.select(".job-ad-criteria tr:last-child td:last-child").text();
                    // -------------------

                    // Excel Row:
                    // Create a new row in the Excel sheet
                    row = sheet.createRow(rowNum++);
                    // ----------------------

                    String ProfileRecherce = "";
                    for (Element Profile : InfoDuProfile) {
                        String profile = Profile.text();
                        ProfileRecherce = ProfileRecherce + " | " + profile;
                    }

                    String métier = ""; // Métier :
                    String SecteurActivité = ""; // Secteur d?activité :
                    String TypeDeContract = ""; // Type de contrat :
                    String Région = ""; // Région :
                    String Ville = ""; // Ville :
                    String NiveauExpérience = ""; //  Niveau d'expérience :
                    String NiveauEtudes = ""; // Niveau d'études :
                    String LanguesExigées = ""; // Langues exigées :
                    String CompétencesClés = ""; // Compétences clés :

                    for(Element criteria : criteriaElements){
                        String firstCell = criteria.select("td:first-child").text();
                        Elements lastCell = criteria.select(".field-item");
                        if(firstCell != ""){
                            for(Element fieldItem : lastCell){
                                String item = fieldItem.text();
                                switch (firstCell) {
                                    case "Métier :":
                                        métier = métier + item + ";";
                                        break;
                                    case "Secteur d´activité :":
                                        SecteurActivité = SecteurActivité + item + ";";
                                        break;
                                    case "Type de contrat :":
                                        TypeDeContract = TypeDeContract + item + ";";
                                        break;
                                    case "Région :":
                                        Région = Région + item + ";";
                                        break;
                                    case "Niveau d'expérience :":
                                        NiveauExpérience = NiveauExpérience + item + ";";
                                        break;
                                    case "Niveau d'études :":
                                        NiveauEtudes = NiveauEtudes + item + ";";
                                        break;
                                    case "Langues exigées :":
                                        LanguesExigées = LanguesExigées + item + ";";
                                        break;
                                    case "Compétences clés :":
                                        CompétencesClés = CompétencesClés + item + ";";
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }
                        if (firstCell.trim().equals("Ville :")){
                            Ville = criteria.select("td:last-child").text();
                        }
                    }
                    String prefix = "Exerperience ";
                    NiveauExpérience = NiveauExpérience.substring(prefix.length());

                    // Write data to the cells in the row
                    row.createCell(0).setCellValue(Titre);
                    row.createCell(1).setCellValue("https://www.emploi.ma/" + OffreLink);
                    row.createCell(2).setCellValue(SiteName);
                    row.createCell(3).setCellValue(DatePublication);
                    row.createCell(4).setCellValue(""); // Postuler Date
                    row.createCell(5).setCellValue(""); // Adresse d'entreprise
                    row.createCell(6).setCellValue(WebSiteCompany);
                    row.createCell(7).setCellValue(Company);
                    row.createCell(8).setCellValue(Description);
                    row.createCell(9).setCellValue(""); // Poste Description
                    row.createCell(10).setCellValue(Région);
                    row.createCell(11).setCellValue(Ville);
                    row.createCell(12).setCellValue(SecteurActivité);
                    row.createCell(13).setCellValue(métier);
                    row.createCell(14).setCellValue(TypeDeContract);
                    row.createCell(15).setCellValue(NiveauEtudes);
                    row.createCell(16).setCellValue(""); // Diploma/Specialty
                    row.createCell(17).setCellValue(NiveauExpérience);
                    row.createCell(18).setCellValue(ProfileRecherce);
                    row.createCell(19).setCellValue(""); // PersonalityTraits
                    row.createCell(20).setCellValue(CompétencesClés); // HardSkills
                    row.createCell(21).setCellValue(""); // SoftSkills
                    row.createCell(22).setCellValue(""); // Recommanded Competence
                    row.createCell(23).setCellValue(LanguesExigées); // Langue > niveau
                    row.createCell(24).setCellValue(""); // Salary
                    row.createCell(25).setCellValue(""); // Avantages Sociaux
                    row.createCell(26).setCellValue("False"); // Avantages Sociaux
                    row.createCell(27).setCellValue(nombreDePoste);

                    
                    System.out.println("Info of Offer were added at Row Number: " + rowNum);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                System.out.println("Sleeping for 7 seconds");
                Thread.sleep(7000); // Sleep for 7 seconds (7 * 1000 milliseconds)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
        // Save the workbook to a file
        try (FileOutputStream fileOut = new FileOutputStream("output.xlsx")) {
            workbook.write(fileOut);
            System.out.println("Excel file created successfully!");
        } catch (Exception e) {
        e.printStackTrace();
        }

        // Close the workbook
        workbook.close();
    }
}
