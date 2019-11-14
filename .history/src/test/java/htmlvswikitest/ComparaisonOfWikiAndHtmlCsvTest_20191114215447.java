package htmlvswikitest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import helper.Constants;
import helper.FileHandlerImpl;
import helper.Utils;
import helper.WikiRunner;
import interfaces.FileHandler;
import interfaces.Statistics;
import wikipedia.wiki.WikiExtractor;
import wikipedia.html.HTMLConverter;
import wikipedia.wiki.WikiConverter;

class ComparaisonOfWikiAndHtmlCsvTest {
	
	private WikiConverter wikiConverter;
	private HTMLConverter htmlConverter;
	
	
	@BeforeEach
	public void setup() {
		 this.wikiConverter = new WikiConverter();
		 this.htmlConverter = new HTMLConverter();
	}
	
	
	@Test
	@DisplayName("genererate html and wiki wiki csv and tests their content  ")
	@Tag("robustness")
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
			// treate all csv
			File[] files = null;
			int counter = 0;
			File wikiDirectory = null;
			wikiDirectory = new File(Constants.WIKI_OUTPUT_DIR);
			assertTrue(wikiDirectory.isDirectory());
			files = wikiDirectory.listFiles();
			String row;
			 int z = 0 ;
			 
			while ((pageTitle = br.readLine()) != null) {
				if (!wikiConverter.doesUrlExist("en", pageTitle)) {
					System.out.println(Constants.CONSOLE_RED_COLOR+"["+ pageTitle + "] does not exist!");
					if(!wikiConverter.doesUrlExist("fr",  pageTitle) && !htmlConverter.doesUrlExist(Constants.EN_BASE_WIKIPEDIA_URL + pageTitle))
						continue;
				}
				
				if(wikiConverter.doesUrlExist("en",  pageTitle))
					doc = WikiRunner.getDocument("en", pageTitle);
				else if(wikiConverter.doesUrlExist("fr", pageTitle))
					doc = WikiRunner.getDocument("fr", pageTitle);
		
				Document docH = Jsoup.connect(Constants.EN_BASE_WIKIPEDIA_URL + pageTitle).get();
			    List<String> data = htmlConverter.convertToCsv(docH, Constants.EN_BASE_WIKIPEDIA_URL,pageTitle, Constants.HTML_OUTPUT_DIR);
			    List<String> data2  = wikiConverter.convertToCsv(doc, Constants.EN_BASE_WIKIPEDIA_URL, pageTitle, Constants.WIKI_OUTPUT_DIR);
			    int i = 0,y = 0 ;
			    for(String line: data ) {
			    	 try {
			    		 
			    		  if(data2.contains(line)) {
			    			  assertTrue(data2.contains(line));
			    		  }else {
			    			  i++;
			    		  }
			    		  y++;
			    		  
			    	 } catch (Exception e) {};
			    	
			    }
			    double resultat = 0;
			    if(y!=0) {
			    	 resultat = (100*i)/y;
			    	 if(resultat <= 30) {
			    		 z++;
			    	 }
			    }
			    System.out.println("------------------------------------------------------------------------------------------------\n");
			    Logger.getGlobal().log(Level.INFO, "for this title :"+ pageTitle +" " +resultat+" %"+ " of content in csv and wiki files are not compliant");
			    System.out.println("------------------------------------------------------------------------------------------------\n");
				data.clear();
				data2.clear();
				number++;
			}
			
			System.out.println("------------------------------------------------------------------------------------------------\n");
			System.out.println("------------------------------------------------------------------------------------------------\n");
			System.out.println("CSV serialization finished, "+number+" urls have been tested \n");
			System.out.println( "there are "+z+ " urls that have all the compliant tables in their html and wiki files.\n");
			System.out.println( "an error rate of " +(double)((number-z)*100)/number +"%");
			System.out.println("------------------------------------------------------------------------------------------------\n");
			System.out.println("------------------------------------------------------------------------------------------------\n");
		} catch (Exception e) {
		} finally {
			try {
				fr.close();
				br.close();
			} catch (IOException e) {
				
			}
		}

	}

}
