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
import org.junit.jupiter.api.Tag;
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
@DisplayName("Test if the number of tables in wiki and html is equal of the title Comparison_of_Asian_national_space_programs")
public void testComparison_of_Asian_national_space_programs() throws IOException, UrlNotFoundException{
	Document doc = WikiRunner.getDocument("en", "Comparison_of_Asian_national_space_programs");
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Asian_national_space_programs");
	 Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Asian_national_space_programs").get();
	 Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Asian_national_space_programs");
	assertEquals(table.size(), tables.size());	
}
@Test
@DisplayName("Test if the number of tables in wiki and html is equal of the title Comparison_between_Esperanto_and_Ido")
public void testbetween_Esperanto_and_Ido() throws IOException, UrlNotFoundException{
	Document doc = WikiRunner.getDocument("en", "Comparison_between_Esperanto_and_Ido");
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_between_Esperanto_and_Ido");
	Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_between_Esperanto_and_Ido").get();
	 Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_between_Esperanto_and_Ido");
	assertEquals(table.size(), tables.size());	
}

@Test
@DisplayName("Test if the number of tables in wiki and html is equal for the title Comparison_of_orbital_launch_systems") 
public void testComparison_of_orbital_launch_systems() throws IOException, UrlNotFoundException {

	Document doc = WikiRunner.getDocument("en", "Comparison_of_orbital_launch_systems");;
	Document docu = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems").get();
	Elements tables = extractorwiki.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems");
	Elements table = extractorhtml.extractTables(docu, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_orbital_launch_systems");
    assertEquals(tables.size(),table.size());
}

@Test
@DisplayName("Test if the number of tables in wiki and html is equal for the title Comparison_of_C_Sharp_and_Visual_Basic_.NET") 
public void testComparison_of_C_Sharp_and_Visual_Basic_NET() throws IOException, UrlNotFoundException {

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
@DisplayName("Comparison_of_S.M.A.R.T._tools between html")
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
	 String docHtmlLine2 = data2.get(2) ;
	 String docWikiLine2 = data.get(2) ;
	 assertEquals( docHtmlLine2,docWikiLine2);
  }
@Test
@DisplayName("comparison test of the content of the html and csv file ofComparison_of_California_ski_resorts ")
public void testContents_ofCsvAndHtmlFile() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_California_ski_resorts").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_California_ski_resorts");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_California_ski_resorts", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_California_ski_resorts", Constants.WIKI_OUTPUT_DIR);
	 String docHtmlLine8 = data2.get(8) ;
	 String docWikiLine8 = data.get(8) ;
	 assertEquals( docHtmlLine8,docWikiLine8);
  }
@Test
@DisplayName("comparison test of the content of the html and csv file ofComparison_of_Dewey_and_Library_of_Congress_subject_classification ")
public void testContents_ofCsvAndHtmlFile2() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Dewey_and_Library_of_Congress_subject_classification").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_Dewey_and_Library_of_Congress_subject_classification");
	 List<String> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_Dewey_and_Library_of_Congress_subject_classification", Constants.HTML_OUTPUT_DIR);
	 List<String> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_Dewey_and_Library_of_Congress_subject_classification", Constants.WIKI_OUTPUT_DIR);
	 String docHtmlLine2 = data2.get(2) ;
	 String docWikiLine2 = data.get(2) ;
	 assertEquals( docHtmlLine2,docWikiLine2);
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
	
	@Test
	@DisplayName("Comparison_of_display_technology")
	@Tag("robustness")
	public void testComparison_of_display_technology() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_display_technology").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_display_technology");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_distributed_file_systems")
	@Tag("robustness")
	public void testComparison_of_distributed_file_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
		assertEquals(3, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_document_interfaces")
	@Tag("robustness")
	public void testComparison_of_document_interfaces() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_interfaces").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_interfaces");
		assertEquals(1, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_document_markup_languages")
	@Tag("robustness")
	public void testComparison_of_document_markup_languages() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_markup_languages").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_markup_languages");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_help_desk_issue_tracking_software")
	@Tag("robustness")
	public void testComparison_of_help_desk_issue_tracking_software() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_help_desk_issue_tracking_software").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_help_desk_issue_tracking_software");
		assertEquals(1, tables.size());
	}




	@Test
	@DisplayName("Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers")
	@Tag("robustness")
	public void testComparison_of_embedded_computer_systems_on_board_the_Mars_rovers() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers");
		assertEquals(1, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_executable_file_formats")
	@Tag("robustness")
	public void testComparison_of_executable_file_formats() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_executable_file_formats").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_executable_file_formats");
		assertEquals(1, tables.size());
	}




	@Test
	@DisplayName("Comparison_of_free_credit_report_websites")
	@Tag("robustness")
	public void testComparison_of_free_credit_report_websites() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_free_credit_report_websites").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_free_credit_report_websites");
		assertEquals(1, tables.size());
	}





	@Test
	@DisplayName("Comparison_of_hardware_random_number_generators")
	@Tag("robustness")
	public void testComparison_of_hardware_random_number_generators() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hardware_random_number_generators").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hardware_random_number_generators");
		assertEquals(0, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_hub_gears")
	@Tag("robustness")
	public void testComparison_of_hub_gears() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hub_gears").get();
		Elements tables =	extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hub_gears");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_iSCSI_targets")
	@Tag("robustness")
	public void testComparison_of_iSCSI_targets() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_iSCSI_targets").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_iSCSI_targets");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_karate_styles")
	@Tag("robustness")
	public void testComparison_of_karate_styles() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_karate_styles").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_karate_styles");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_mobile_phone_standards")
	@Tag("robustness")
	public void testComparison_of_mobile_phone_standards() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_mobile_phone_standards").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_mobile_phone_standards");
		assertEquals(3, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_European_traffic_laws")
	@Tag("robustness")
	public void testComparison_of_European_traffic_laws() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_European_traffic_laws").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_European_traffic_laws");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_Nikon_DSLR_cameras")
	@Tag("robustness")
	public void testComparison_of_Nikon_DSLR_cameras() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Nikon_DSLR_cameras").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Nikon_DSLR_cameras");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Nintendo_video_game_consoles")
	@Tag("robustness")
	public void testNintendo_video_game_consoles() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Nintendo_video_game_consoles").get();
		Elements tables =	extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Nintendo_video_game_consoles");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_JavaScript_charting_frameworks")
	@Tag("robustness")
	public void testComparison_of_JavaScript_charting_frameworks() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_JavaScript_charting_frameworks").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_JavaScript_charting_frameworks");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_CRM_systems")
	@Tag("robustness")
	public void testComparison_of_CRM_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CRM_systems").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CRM_systems");
		assertEquals(2, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_CalDAV_and_CardDAV_implementations")
	@Tag("robustness")
	public void testComparison_of_CalDAV_and_CardDAV_implementations() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CalDAV_and_CardDAV_implementations").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CalDAV_and_CardDAV_implementations");
		assertEquals(2, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_Canon_EOS_digital_cameras")
	@Tag("robustness")
	public void testComparison_of_Canon_EOS_digital_cameras() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Canon_EOS_digital_cameras").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Canon_EOS_digital_cameras");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_DEX_software")
	@Tag("robustness")
	public void testComparison_of_DEX_software() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DEX_software").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DEX_software");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_DOS_operating_systems")
	@Tag("robustness")
	public void testComparison_of_DOS_operating_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DOS_operating_systems").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DOS_operating_systems");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents")
	@Tag("robustness")
	public void testComparison_of_Fukushima_and_Chernobyl_nuclear_accidents() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents").get();
		Elements tables = extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents");
		assertEquals(4, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_online_charity_donation_services_in_the_United_Kingdom")
	@Tag("robustness")
	public void testComparison_of_online_charity_donation_services_in_the_United_Kingdom() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_online_charity_donation_services_in_the_United_Kingdom").get();
		Elements tables = 	extractorhtml.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_online_charity_donation_services_in_the_United_Kingdom");
		assertEquals(4, tables.size());
	}
}