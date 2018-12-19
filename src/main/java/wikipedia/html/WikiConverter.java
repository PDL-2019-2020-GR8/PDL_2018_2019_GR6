package wikipedia.html;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import helper.Constants;
import helper.FileHandlerImpl;
import interfaces.Converter;
import interfaces.FileHandler;

public class WikiConverter implements Converter {
	private WikiExtractor extractor;
	private FileHandler filehandler;
	private char separator = ',';

	public WikiConverter() {
		this.extractor = new WikiExtractor();
		this.filehandler = new FileHandlerImpl();

	}

	private boolean isNumeric(char character) {
		String numericRegex = "^[0-9]*$";
		String charString = String.valueOf(character);
		return Pattern.matches(numericRegex, charString);
	}

	private boolean doesUrlExist(String url) throws IOException {
		try {
			Jsoup.connect(url).get();
			return true;
		} catch (HttpStatusException e) {
			System.out.println(Constants.CONSOLE_RED_COLOR+"[" + url + "] n'existe pas!");
			return false;
		}
	}

	@Override
	public void convertAllToCSV() {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			file = new File(Constants.WIKI_URLS_FILE_PATH);
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			String url = null;
			while ((url = br.readLine()) != null) {
				if (!doesUrlExist(Constants.EN_BASE_WIKIPEDIA_URL + url))
					continue;

				Document doc = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + url).get();
				convertToCsv(doc, Constants.EN_BASE_WIKIPEDIA_URL, url, Constants.HTML_OUTPUT_DIR);
			}
		} catch (Exception e) {
			// System.out.println("-------------------------------------" + e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void convertToCsv(Document doc, String baseUrl, String url, String filePath) throws HttpStatusException {
		List<String> data = new ArrayList<>();
		String line = "";
		String filename;
		StringBuilder currentTdText;
		int filenameCounter = 1;
		try {
			Elements tableElements = this.extractor.extractTables(doc, baseUrl + url);
			if (tableElements == null)
				System.out.println("Oups, smething wrong happened");
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
					filename = this.filehandler.extractFilenameFromUrl(url, filenameCounter);
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
						// System.out.println("current td text = " + currentTdText);
					} else {
						if ((k + 1) < tdText.length()) {
							if (isNumeric(tdText.charAt(k + 1))) {
								// System.out.println("current char "+currentTdText.charAt(k-1)+" td
								// text "+currentTdText);
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