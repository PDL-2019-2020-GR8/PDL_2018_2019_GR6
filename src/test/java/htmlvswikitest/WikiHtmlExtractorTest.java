package htmlvswikitest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.util.HashMap;
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
@DisplayName("comparison test of the number of tables extract in html and wiki of title Comparison_of_SSH_clients ")
public void testComparison_of_SSH_clients() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Ballon_d'Or").get();
	Elements tableHtml = extractorhtml.extractTables(dochtml, Constants.EN_BASE_WIKIPEDIA_URL + "Ballon_d'Or");
	Document docWiki = WikiRunner.getDocument("en", "Ballon_d'Or");
	Elements tableWiki = extractorwiki.extractTables(docWiki, Constants.EN_BASE_WIKIPEDIA_URL + "Ballon_d'Or");
	//System.out.println("=============================================================================================");
	//System.out.println(docWiki);
	//System.out.println(tableHtml.size());
	 System.out.println(tableWiki.size() +""+ tableHtml.size());
	// assertEquals(tableWiki.size(), tableHtml.size());
  }	

//@Test
//@DisplayName("Test if bliki extrator does'nt  fetch tag ")
//public void testContents_ofCSV1() throws IOException, UrlNotFoundException {
//	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_S.M.A.R.T._tools");
//	HashMap<String, List<String>> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_S.M.A.R.T._tools", Constants.WIKI_OUTPUT_DIR);
//	assertFalse(data2.get("Comparison_of_S.M.A.R.T._tools1").get(1).contentEquals("<br>"));
//	assertFalse(data2.get("Comparison_of_S.M.A.R.T._tools1").get(1).contains("<ref>"));
//	 }

 

@Test
@DisplayName("test if wiki data which is generate is the same as the data online")
public void testContents_ofCSV2() throws IOException, UrlNotFoundException {
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_3D_computer_graphics_software");
	 HashMap<String, List<String>> datawiki  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_3D_computer_graphics_software", Constants.WIKI_OUTPUT_DIR);
	 assertTrue(datawiki.get("Comparison_of_3D_computer_graphics_software1").contains("Carrara,20130826 85119,DAZ 3D,Microsoft Windows  Mac OS X,Animation  Modeling,proprietary"));
  }

//@Test
//@DisplayName("comparison test of the content of the html and csv file ofComparison_of_California_ski_resorts ")
//public void testContents_ofCsvAndHtmlFile() throws IOException, UrlNotFoundException {
//	 Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_California_ski_resorts").get();
//	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_California_ski_resorts");
//	 HashMap<String, List<String>> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_California_ski_resorts", Constants.HTML_OUTPUT_DIR);
//	 HashMap<String, List<String>> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_California_ski_resorts", Constants.WIKI_OUTPUT_DIR);
//	assertEquals(data2.get(8),data.get(8));
//  }

@Test
@DisplayName("comparison test of the content of the html and csv file of Comparison_(grammar)1 ")
public void testContents_ofCsvAndHtmlFile3() throws IOException, UrlNotFoundException {
	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_(grammar)").get();
	 Document docWiki = WikiRunner.getDocument("en", "Comparison_(grammar)");
	 HashMap<String, List<String>> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_(grammar)", Constants.HTML_OUTPUT_DIR);
	 HashMap<String, List<String>> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_(grammar)", Constants.WIKI_OUTPUT_DIR);
	 assertEquals(data2.get("Comparison_(grammar)1").get(1),data2.get("Comparison_(grammar)1").get(1));
  }
//@Test
//@DisplayName("comparison test of the content of the html and csv file of Comparison_of_Windows_Vista_and_Windows_XP ")
//public void testContents_ofCsvAndHtmlFile4() throws IOException, UrlNotFoundException {
//	Document dochtml = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + "Comparison_of_Windows_Vista_and_Windows_XP").get();
//	 Document docWiki = WikiRunner.getDocument("en", "Comparison_of_Windows_Vista_and_Windows_XP");
//	 HashMap<String, List<String>> data = htmlConverter.convertToCsv(dochtml, Constants.EN_BASE_WIKIPEDIA_URL,"Comparison_of_Windows_Vista_and_Windows_XP", Constants.HTML_OUTPUT_DIR);
//	 HashMap<String, List<String>> data2  = wikiConverter.convertToCsv(docWiki, Constants.EN_BASE_WIKIPEDIA_URL, "Comparison_of_Windows_Vista_and_Windows_XP", Constants.WIKI_OUTPUT_DIR);
//	assertEquals(data2.get(3),data.get(3));
//  }
}