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
		assertEquals(11, tables.size());
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


	@Test
	@DisplayName("Comparison_of_hub_gears")
	@Tag("robustness")
	public void testComparison_of_hub_gears() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hub_gears").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_hub_gears");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_iSCSI_targets")
	@Tag("robustness")
	public void testComparison_of_iSCSI_targets() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_iSCSI_targets").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_iSCSI_targets");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_karate_styles")
	@Tag("robustness")
	public void testComparison_of_karate_styles() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_karate_styles").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_karate_styles");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_mobile_phone_standards")
	@Tag("robustness")
	public void testComparison_of_mobile_phone_standards() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_mobile_phone_standards").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_mobile_phone_standards");
		assertEquals(3, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_European_traffic_laws")
	@Tag("robustness")
	public void testComparison_of_European_traffic_laws() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_European_traffic_laws").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_European_traffic_laws");
		assertEquals(1, tables.size());
	}

	@Test
	@DisplayName("Comparison_of_Nikon_DSLR_cameras")
	@Tag("robustness")
	public void testComparison_of_Nikon_DSLR_cameras() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Nikon_DSLR_cameras").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Nikon_DSLR_cameras");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Nintendo_video_game_consoles")
	@Tag("robustness")
	public void testNintendo_video_game_consoles() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Nintendo_video_game_consoles").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Nintendo_video_game_consoles");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_JavaScript_charting_frameworks")
	@Tag("robustness")
	public void testComparison_of_JavaScript_charting_frameworks() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_JavaScript_charting_frameworks").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_JavaScript_charting_frameworks");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_CRM_systems")
	@Tag("robustness")
	public void testComparison_of_CRM_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CRM_systems").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CRM_systems");
		assertEquals(2, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_CalDAV_and_CardDAV_implementations")
	@Tag("robustness")
	public void testComparison_of_CalDAV_and_CardDAV_implementations() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CalDAV_and_CardDAV_implementations").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_CalDAV_and_CardDAV_implementations");
		assertEquals(2, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_Canon_EOS_digital_cameras")
	@Tag("robustness")
	public void testComparison_of_Canon_EOS_digital_cameras() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Canon_EOS_digital_cameras").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Canon_EOS_digital_cameras");
		assertEquals(2, tables.size());
	}



	@Test
	@DisplayName("Comparison_of_DEX_software")
	@Tag("robustness")
	public void testComparison_of_DEX_software() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DEX_software").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DEX_software");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_DOS_operating_systems")
	@Tag("robustness")
	public void testComparison_of_DOS_operating_systems() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DOS_operating_systems").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_DOS_operating_systems");
		assertEquals(1, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents")
	@Tag("robustness")
	public void testComparison_of_Fukushima_and_Chernobyl_nuclear_accidents() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Fukushima_and_Chernobyl_nuclear_accidents");
		assertEquals(4, tables.size());
	}


	@Test
	@DisplayName("Comparison_of_online_charity_donation_services_in_the_United_Kingdom")
	@Tag("robustness")
	public void testComparison_of_online_charity_donation_services_in_the_United_Kingdom() throws IOException {
		Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_online_charity_donation_services_in_the_United_Kingdom").get();
		Elements tables = extractor.extractTables(doc, Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_online_charity_donation_services_in_the_United_Kingdom");
		assertEquals(4, tables.size());
	}
}
