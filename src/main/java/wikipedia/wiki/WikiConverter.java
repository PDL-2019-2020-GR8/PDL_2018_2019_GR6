package wikipedia.wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import helper.Constants;
import helper.FileHandlerImpl;
import helper.UrlNotFoundException;
import helper.WikiRunner;
import interfaces.Converter;
import interfaces.FileHandler;


/**
 * 
 * @author Ibrahima HAIDARA
 * @author Mariam Coulibaly
 * @author Mahamadou Sylla
 * @author Abdoul Hamide Ba
 *
 */

public class WikiConverter implements Converter {
	private WikiExtractor extractor;
	private FileHandler filehandler;
	private char separator = ',';
	private int filenameCounter ;
	private  HashMap<String, List<String>> wikiData;

	public WikiConverter() {
		this.extractor = new WikiExtractor();
		this.filehandler = new FileHandlerImpl();

	}

	/**
	 * Checks whether or not a character is a number
	 * Used to process td text
	 * 
	 * @param character the character to check
	 * @return true if that charcacter is a number
	 */
	private boolean isNumeric(char character) {
		String numericRegex = "^[0-9]*$";
		String charString = String.valueOf(character);
		return Pattern.matches(numericRegex, charString); 
	}

	/**
	 * Checks whether or not a url exists
	 * May throw HttpStatusException
	 * 
	 * @param url the url to check
	 * @return true if the url exists
	 * @throws IOException if is not possible to connect to that url
	 */
	public boolean doesUrlExist(String languageVariant, String pageTitle) throws IOException {
		try {
			WikiRunner.getDocument(languageVariant, pageTitle);
			return true;
		} catch (UrlNotFoundException e) {
			//System.out.println(Constants.CONSOLE_RED_COLOR+"["+ pageTitle + "] does not exist!");
			return false;
		}
	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void convertAllToCSV() {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		Document doc = null;
		try {
			file = new File(Constants.WIKI_URLS_FILE_PATH);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String pageTitle = null;
			int number = 0;
			while ((pageTitle = br.readLine()) != null) {
				if (!doesUrlExist("en", pageTitle)) {
					// System.out.println(Constants.CONSOLE_RED_COLOR+"["+ pageTitle + "] does not exist!");
					if(!doesUrlExist("fr",  pageTitle))
						continue;
				}
				
				if(doesUrlExist("en",  pageTitle))
					doc = WikiRunner.getDocument("en", pageTitle);
				else if(doesUrlExist("fr", pageTitle))
					doc = WikiRunner.getDocument("fr", pageTitle);
		
			    convertToCsv(doc, Constants.EN_BASE_WIKIPEDIA_URL, pageTitle, Constants.WIKI_OUTPUT_DIR);
				number++;
			}
			//
			
//			while ((url = br.readLine()) != null) {
//				if (!doesUrlExist(Constants.EN_BASE_WIKIPEDIA_URL + url))
//					continue;
//
//				Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + url).get();
//				convertToCsv(doc, Constants.EN_BASE_WIKIPEDIA_URL, url, Constants.HTML_OUTPUT_DIR);
//			}
			//
			System.out.println("CSV serialization finished, "+number+" urls have been tested");
		} catch (Exception e) {
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				
			}
		}

	}

	/**
	 * Converts tables of a given url into a CSV-like file
	 * 
	 * @param doc the html representation of that page
	 * @param baseUrl the base url to use to make api calls
	 * @param pageTitle the title of the page
	 * @param filePath the path for to be stored in
	 * @return 
	 * @return 
	 * @throws HttpStatusException if the page does not exist
	 */
	// pour une url donne genere un nombre de csv en fonction du nombre de tableau
	public  HashMap<String, List<String>> convertToCsv(Document doc, String baseUrl, String pageTitle, String filePath) throws HttpStatusException {
//		if(!wikiData.containsKey(pageTitle)) {
		  wikiData = new HashMap<String, List<String>>() ;
			List<String> data = new ArrayList<String>();
			
			// HashMap<Integer, List<String>> data2 = new HashMap<Integer, List<String>>();
			// data2.put(1,data);
			String line = "";
			String filename;
			StringBuilder currentTdText;
			 filenameCounter = 1;
			// System.out.println(pageTitle);
			try {
				Elements tableElements = this.extractor.extractTables(doc, baseUrl + pageTitle);
				if (tableElements == null)
					System.out.println("Oups, something wrong happened");
				else {
					for (Element currentTable : tableElements) {
						
					   if(!wikiData.containsKey(pageTitle+filenameCounter)) {
						  // data.clear();
						  // System.out.println(pageTitle+filenameCounter);
						  List<String> data2 = new LinkedList<String>();
						  wikiData.put(pageTitle+filenameCounter, data2);
						Elements currentTableTrs = currentTable.select("tr");
						for (int i = 0; i < currentTableTrs.size(); i++) {
							Element currentTr = currentTableTrs.get(i);
							Elements currentRowTds = currentTr.select("td");
							Element currentTd;
							for (int j = 0; j < currentRowTds.size(); j++) {
								currentTd = currentRowTds.get(j);
								currentTdText = new StringBuilder(currentTd.text());
								currentTdText = processCurrentTDText(currentTdText);
								if (j == currentRowTds.size() - 1) {
									 line += currentTdText.toString().replaceAll("[-}+.{^ÀÁÂÄÇÈÉÊËÌÍÎÏ‰¥µÅÅÑÒÓÔÕÖÙÚÛÜÝàáâãäçèéêëìíîïñòóôõöùúûüýÿ:,]","");
						
								}
								else
									  line += currentTdText.toString().replaceAll("[-}+.{ÀÁÂÄÇÈÉÊËÌÍÎÏÑÒ‰¥µÅÅÓÔÕÖÙÚÛÜÝàáâãäçèéêëìíîïñòóôõöùúûüýÿ^:,]","")+separator;
							}
							if (line != "") {
								line = processLine(line);
								data.add(line) ;
								 wikiData.get(pageTitle+filenameCounter).add(line);
								line = "";
							}
						}
						
						
					}
				}
			  }

			} catch (IOException e) {
				e.printStackTrace();
			}
			return wikiData;
		}
	
	private String processLine(String line) {
		StringBuilder sb = new StringBuilder(line);
		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '\n') 
				sb.setCharAt(i, ' ');
		}
		return sb.toString();
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public StringBuilder processCurrentTDText(StringBuilder tdText) {
		for (int k = 0; k < tdText.length(); k++) {
			if (tdText.charAt(k) == separator) {
				if (k > 0) {
					if (!isNumeric(tdText.charAt(k - 1))) {
						tdText.setCharAt(k, ' ');
					} else {
						if ((k + 1) < tdText.length()) {
							if (isNumeric(tdText.charAt(k + 1))) {
								tdText.setCharAt(k, '.');
							} else
								tdText.setCharAt(k, ' ');
						}
					}
				}
				if (k == tdText.length() - 1)
					tdText.setCharAt(k, ' ');
			}
		}

		return tdText;
	}

}