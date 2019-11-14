package htmlvswikitest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import helper.Constants;
import helper.UrlNotFoundException;
import helper.WikiRunner;
import interfaces.Extractor;
import wikipedia.html.HTMLConverter;
import wikipedia.html.HTMLExtractor;
import wikipedia.wiki.WikiConverter;
import wikipedia.wiki.WikiExtractor;

public class WikiHtmlExtractorTest {
private Extractor  extractorhtml; 
private Extractor  extractorwiki;
private HTMLConverter htmlConverter;
private WikiConverter wikiConverter;

@BeforeEach
public void setup() {
	extractorhtml = new HTMLExtractor();
	extractorwiki = new WikiExtractor();
	 this.htmlConverter = new HTMLConverter();
	 this.wikiConverter = new WikiConverter();
}

@Test
@DisplayName("Comparison_of_Asian_national_space_programs")
public void test_nb_tables_wikivshtml_Comparison_of_Asian_national_space_programs() throws IOException, UrlNotFoundException{
	Document doc = WikiRunner.getDocument("en", "Comparison_of_Asian_national_space_programs");
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
	 Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems").get();
	 Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
	assertEquals(table.size(), tables.size());	
}
@Test
@DisplayName("Comparison_between_Esperanto_and_Ido")
public void test_nb_tables_wikivshtml_Comparison_between_Esperanto_and_Ido() throws IOException, UrlNotFoundException{
	Document doc = WikiRunner.getDocument("en", "Comparison_between_Esperanto_and_Ido");
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
	Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems").get();
	 Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
	assertEquals(table.size(), tables.size());	
}

@Test
@DisplayName("Comparison_of_orbital_launch_systems") 
public void test_nb_tables_wikivshtmlComparison_of_orbital_launch_systems() throws IOException, UrlNotFoundException {

	Document doc = WikiRunner.getDocument("en", "Comparison_of_orbital_launch_systems");;
	Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems").get();
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems");
	Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems");
    assertEquals(tables.size(),table.size());
}

@Test
@DisplayName("Comparison_of_C_Sharp_and_Visual_Basic_.NET") 
public void test_nb_tables_wikivshtmlComparison_of_C_Sharp_and_Visual_Basic() throws IOException, UrlNotFoundException {

	Document doc = WikiRunner.getDocument("en", "Comparison_of_C_Sharp_and_Visual_Basic_.NET");;
	Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_C_Sharp_and_Visual_Basic_.NET").get();
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_C_Sharp_and_Visual_Basic_.NET");
	Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_C_Sharp_and_Visual_Basic_.NET");
    assertEquals(tables.size(),table.size());
}

@Test
@DisplayName("comparison test of the number of tables extract in html and wiki of title Comparison_of_SSH_clients ")
public void testComparison_of_SSH_clients() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_SSH_clients").get();
	Elements tableHtml = extractorhtml.extractTables(dochtml, Constants.EN_BASE_WIKIPEDIA_URL + "Ballon_d'Or");
	Document docWiki = WikiRunner.getDocument("en", "Comparison_of_SSH_clients");
	Elements tableWiki = extractorwiki.extractTables(docWiki, Constants.EN_BASE_WIKIPEDIA_URL + "Ballon_d'Or");
	//System.out.println("=============================================================================================");
	//System.out.println(docWiki);
	//System.out.println(tableHtml.size());
	System.out.println("=============================================================================================");
	assertEquals(tableWiki.size(), tableHtml.size());
  }	

@Test
@DisplayName("Comparison_of_S.M.A.R.T._tools between html et wiki csv generate")
public void testContents_ofCSV1() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_S.M.A.R.T._tools").get();
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_S.M.A.R.T._tools", Constants.HTML_OUTPUT_DIR);
	 String contenu = new String("Argus Monitor,Windows,Trialware[3],GUI,IDE(PATA)  SATA,eSATA  USB,Some RAID controllers,Yes,?,By window  sound  email  program execution at choosable parameter changes  threshold,Also shows temperature of CPU  GPU  CPU core speed  Intel Turbo Boost status  CPU power consumption  system load and system fan speeds. Can control speed of GPU and system fans.");
	assertEquals(contenu,data.get(2));
  }
@Test
@DisplayName("comparison test of the content of the html and csv file ofComparison_of_Internet_Relay_Chat_bots ")
public void testContents_ofCSV2() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Internet_Relay_Chat_bots").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_Internet_Relay_Chat_bots");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_Internet_Relay_Chat_bots", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_Internet_Relay_Chat_bots", Constants.WIKI_OUTPUT_DIR);
	assertEquals(data2.get(2),data.get(2));
  }
@Test
@DisplayName("comparison test of the content of the html and csv file ofComparison_of_California_ski_resorts ")
public void testContents_ofCsvAndHtmlFile() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_California_ski_resorts").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_California_ski_resorts");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_California_ski_resorts", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_California_ski_resorts", Constants.WIKI_OUTPUT_DIR);
	assertEquals(data2.get(8),data.get(8));
  }
@Test
@DisplayName("comparison test of the content of the html and csv file ofComparison_of_Dewey_and_Library_of_Congress_subject_classification ")
public void testContents_ofCsvAndHtmlFile2() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Dewey_and_Library_of_Congress_subject_classification").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_Dewey_and_Library_of_Congress_subject_classification");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_Dewey_and_Library_of_Congress_subject_classification", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_Dewey_and_Library_of_Congress_subject_classification", Constants.WIKI_OUTPUT_DIR);
	assertEquals(data2.get(2),data.get(2));
  }
@Test
@DisplayName("comparison test of the content of the html and csv file of Comparison_of_continuous_integration_software ")
public void testContents_ofCsvAndHtmlFile3() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_continuous_integration_software").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_continuous_integration_software");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_continuous_integration_software", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_continuous_integration_software", Constants.WIKI_OUTPUT_DIR);
	assertEquals(data2.get(4),data.get(4));
  }
@Test
@DisplayName("comparison test of the content of the html and csv file of Comparison_of_Windows_Vista_and_Windows_XP ")
public void testContents_ofCsvAndHtmlFile4() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Windows_Vista_and_Windows_XP").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_Windows_Vista_and_Windows_XP");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_Windows_Vista_and_Windows_XP", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_Windows_Vista_and_Windows_XP", Constants.WIKI_OUTPUT_DIR);
	assertEquals(data2.get(3),data.get(3));
  }
}