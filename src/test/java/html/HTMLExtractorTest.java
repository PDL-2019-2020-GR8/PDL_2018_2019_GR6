package html;

/**
 * 
 * @author Ibrahima HAIDARA
 * @author Mariam Coulibaly
 * @author Mahamadou Sylla
 * @author Abdoul Hamide Ba
 *
 */

import helper.Constants;
import interfaces.Extractor;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import wikipedia.html.HTMLExtractor;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HTMLExtractorTest {
	
	private Extractor extractor;
	
	@BeforeEach
	public void setup() {
		extractor = new HTMLExtractor();
	}
	
	@Test
	@DisplayName("Comparison_of_SSH_clients")
	public void testComparison_of_SSH_clients() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_SSH_clients").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_SSH_clients");
		assertEquals(3, tables.size());
	}
	
	@Test
	@DisplayName("Comparison_of_TLS_implementations")
	public void testComparison_of_TLS_implementations() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_TLS_implementations").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_TLS_implementations");
		assertEquals(18, tables.size());
	}
	
	@Test
	@DisplayName("Python_(langage)")
	public void testComparison_of_PythonLangage() throws IOException {
		Document doc = Jsoup.connect(Constants.FR_BASE_WIKIPEDIA_URL + "Python_(langage)").get();
		Elements tables = extractor.extractTables(doc, Constants.FR_BASE_WIKIPEDIA_URL + "Python_(langage)");
		assertEquals(0, tables.size());
	}
	
	
	@Test
	@DisplayName("Comparison_of_Exchange_ActiveSync_clients")
	@Tag("robustness")
	public void testComparison_of_Exchange_ActiveSync_clients() throws IOException {
		assertThrows(HttpStatusException.class, () -> Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Exchange_ActiveSync_clients").get());
	}
	
	@Test
	@DisplayName("Comparison_of_IPv6_application_support")
	@Tag("robustness")
	public void testComparison_of_IPv6_application_support() throws IOException {
		assertThrows(HttpStatusException.class, () -> Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_IPv6_application_support").get());
	}
	
	
	@Test
	@DisplayName("Comparison_of_audio_player_software)")
	@Tag("robustness")
	public void testComparison_of_audio_player_software() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_audio_player_software").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_audio_player_software");
		assertEquals(9, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_display_technology")
	@Tag("robustness")
	public void testComparison_of_display_technology() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_display_technology").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_display_technology");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_distributed_file_systems")
	@Tag("robustness")
	public void testComparison_of_distributed_file_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_distributed_file_systems");
		assertEquals(3, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_document_interfaces")
	@Tag("robustness")
	public void testComparison_of_document_interfaces() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_interfaces").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_interfaces");
		assertEquals(1, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_document_markup_languages")
	@Tag("robustness")
	public void testComparison_of_document_markup_languages() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_markup_languages").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_document_markup_languages");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_help_desk_issue_tracking_software")
	@Tag("robustness")
	public void testComparison_of_help_desk_issue_tracking_software() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_help_desk_issue_tracking_software").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_help_desk_issue_tracking_software");
		assertEquals(1, tables.size());
	}




	@Test
	@DisplayName("Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers")
	@Tag("robustness")
	public void testComparison_of_embedded_computer_systems_on_board_the_Mars_rovers() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_embedded_computer_systems_on_board_the_Mars_rovers");
		assertEquals(1, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_executable_file_formats")
	@Tag("robustness")
	public void testComparison_of_executable_file_formats() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_executable_file_formats").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_executable_file_formats");
		assertEquals(1, tables.size());
	}




	@Test
	@DisplayName("Comparison_of_free_credit_report_websites")
	@Tag("robustness")
	public void testComparison_of_free_credit_report_websites() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_free_credit_report_websites").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_free_credit_report_websites");
		assertEquals(1, tables.size());
	}





	@Test
	@DisplayName("Comparison_of_hardware_random_number_generators")
	@Tag("robustness")
	public void testComparison_of_hardware_random_number_generators() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hardware_random_number_generators").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hardware_random_number_generators");
		assertEquals(0, tables.size());
	}




}
