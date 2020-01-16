package htmlvswikitest;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
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
import wikipedia.html.HTMLExtractor;
import wikipedia.wiki.WikiExtractor;
import wikipedia.html.HTMLConverter;
import wikipedia.wiki.WikiConverter;

class ComparaisonOfWikiAndHtmlCsvTest {
	
	private WikiConverter wikiConverter;
	private HTMLConverter htmlConverter;
	private FileHandler filehandler;
	private  HashMap<String, List<String>> wikiData;
	
	
	@BeforeEach
	public void setup() {
		 this.wikiConverter = new WikiConverter();
		 this.htmlConverter = new HTMLConverter();
		 filehandler =  new FileHandlerImpl();
	}
	
	
	@Test
	@DisplayName("genererate html and wiki wiki csv and tests their content  ")
	@Tag("robustness")
	public void convertAllToCSV() {
		File file = null;
		FileReader fr = null;
		BufferedReader br = null;
		Document doc = null;
		String filename = null;
		
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
				HashMap<String, List<String>> htmlData = htmlConverter.convertToCsv(docH, Constants.EN_BASE_WIKIPEDIA_URL,pageTitle, Constants.HTML_OUTPUT_DIR);
			    HashMap<String, List<String>> wikiData = wikiConverter.convertToCsv(doc, Constants.EN_BASE_WIKIPEDIA_URL, pageTitle, Constants.WIKI_OUTPUT_DIR);
			    
			  
//			    wikiData.keySet().stream().forEach((e) -> {
//			    	// if(data.get(e).size() != 0) {
//			    		 System.out.println(e +" size new :"+ wikiData.get(e).size() +"data "+ wikiData.get(e));
//			    		 filename = this.filehandler.extractFilenameFromUrl(e );
//						 this.filehandler.write(Constants.WIKI_OUTPUT_DIR, e , wikiData.get(e));
//						 System.out.println(Constants.CONSOLE_WHITE_COLOR+e +" "+ "wiki has been generated");
//						// System.out.println(e+" size: "+wikiData.get(pageTitle+filenameCounter).size());
//					// }
//			   
//				 // System.out.println(wikiConverter.getWikiData().size()); 	
//			    });
			    for(String e :  wikiData.keySet() ) {
			    	 filename = this.filehandler.extractFilenameFromUrl(e);
			    	 if(wikiData.get(e) != null) {
			    		 this.filehandler.write(Constants.WIKI_OUTPUT_DIR,  filename , wikiData.get(e));
						 System.out.println(Constants.CONSOLE_WHITE_COLOR+e +" "+ "wiki has been generated");
			    	 }
			    }
			    
			    for(String e :  htmlData.keySet() ) {
			    	 filename = this.filehandler.extractFilenameFromUrl(e);
			    	 if(htmlData.get(e) != null) {
			    		 this.filehandler.write(Constants.HTML_OUTPUT_DIR,  filename , htmlData.get(e));
						 System.out.println(Constants.CONSOLE_WHITE_COLOR+e +" "+ "html has been generated"); 
			    	 }
				    
			    }
			  
//			    int i = 0,y = 0 ;
//			    double resultat = 0;
//			    System.out.println("taille html "+data.size());
//			    System.out.println("taille html "+data2.size());
//			   // if(data.size() == data2.size()) {
//			    	for(String line: data ) {
//				    	 try {
//				    		 
//				    		  if(data2.contains(line)) {
//				    			  assertTrue(data2.contains(line));
//				    		  }else {
//				    			  i++;
//				    		  }
//				    		  y++;
//				    		  
//				    	 } catch (Exception e) {};
//				    	
//				    }
//				    if(y!=0) {
//				    	 resultat = (100*i)/y;
//				    	 if(resultat <= 25) {
//				    		 z++;
//				    	 }
//				    }
			    // }
			    
			   
//			    System.out.println("------------------------------------------------------------------------------------------------\n");
//			    Logger.getGlobal().log(Level.INFO, "for this title :"+ pageTitle +" " +resultat+" %"+ " of content in csv and wiki files are not compliant");
//			    System.out.println("------------------------------------------------------------------------------------------------\n");
				// data.clear();
//				data2.clear();
				// number++;
				int totalExtacted = 0;
				for(Statistics s : HTMLExtractor.statisticsList) {
					totalExtacted += s.getExtractedTablesNumber();
				}
				System.out.println("Number of extracted pertinent tables : "+totalExtacted);
				Utils.displayInfo(HTMLExtractor.statisticsList, "HTML Extractor");
				
				int totalExtacted1 = 0;
				for(Statistics s : WikiExtractor.statisticsList) {
					totalExtacted1 += s.getExtractedTablesNumber();
				}
				System.out.println("Number of extracted pertinent tables : "+totalExtacted1);
				Utils.displayInfo(WikiExtractor.statisticsList, "WIKI Extractor");

			}
			
			//System.out.println("------------------------------------------------------------------------------------------------\n");
			//System.out.println("------------------------------------------------------------------------------------------------\n");
			//System.out.println("CSV serialization finished, "+number+" urls have been tested \n");
			//System.out.println( "there are "+z+ " urls that have all the compliant tables in their html and wiki files.\n");
			//System.out.println( "an error rate of " +(double)((number-z)*100)/number +"%");
			//System.out.println("------------------------------------------------------------------------------------------------\n");
			//System.out.println("------------------------------------------------------------------------------------------------\n");
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
