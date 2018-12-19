package wikipedia.wiki;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import helper.Constants;
import helper.FileHandlerImpl;
import helper.UrlNotFoundException;
import helper.WikiRunner;
import interfaces.Converter;
import interfaces.FileHandler;

public class WikiConverter implements Converter {
	private WikiExtractor extractor;
	private FileHandler filehandler;
	private char separator = ',';
	private Logger logger;

	public WikiConverter() {
		this.extractor = new WikiExtractor();
		this.filehandler = new FileHandlerImpl();
		logger = Logger.getLogger(WikiConverter.class.getName());

	}

	private boolean isNumeric(char character) {
		String numericRegex = "^[0-9]*$";
		String charString = String.valueOf(character);
		return Pattern.matches(numericRegex, charString); 
	}

	private boolean doesUrlExist(String languageVariant, String pageTitle) throws IOException {
		try {
			WikiRunner.getDocument(languageVariant, pageTitle);
			return true;
		} catch (UrlNotFoundException e) {
			//System.out.println(Constants.CONSOLE_RED_COLOR+"["+ pageTitle + "] does not exist!");
			return false;
		}
	}
	
	
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
					System.out.println(Constants.CONSOLE_RED_COLOR+"["+ pageTitle + "] does not exist!");
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

	public void convertToCsv(Document doc, String baseUrl, String pageTitle, String filePath) throws HttpStatusException {
		List<String> data = new ArrayList<>();
		String line = "";
		String filename;
		StringBuilder currentTdText;
		int filenameCounter = 1;
		try {
			Elements tableElements = this.extractor.extractTables(doc, baseUrl + pageTitle);
			if (tableElements == null)
				System.out.println("Oups, something wrong happened");
			else {
				for (Element currentTable : tableElements) { 
					Elements currentTableTrs = currentTable.select("tr");
					for (int i = 0; i < currentTableTrs.size(); i++) {
						Element currentTr = currentTableTrs.get(i);
						Elements currentRowTds = currentTr.select("td");
						for (int j = 0; j < currentRowTds.size(); j++) {
							currentTdText = new StringBuilder(currentRowTds.get(j).text());
							currentTdText = processCurrentTDText(currentTdText);
							if (j == currentRowTds.size() - 1)
								line += currentTdText.toString();
							else
								line += currentTdText.toString() + separator;
						}
						if (line != "") {
							data.add(line);
							line = "";
						}
					}
					filename = this.filehandler.extractFilenameFromUrl(pageTitle, filenameCounter);
					this.filehandler.write(filePath, filename, data);
					System.out.println(Constants.CONSOLE_WHITE_COLOR+filename + " has been generated");
					filenameCounter++;
					data.clear();
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public StringBuilder processCurrentTDText(StringBuilder tdText) {
		tdText = new StringBuilder(tdText);
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
				if(k == tdText.length()-1)
					tdText.setCharAt(k, separator);
			}
		}
		
		return tdText;
	}

}