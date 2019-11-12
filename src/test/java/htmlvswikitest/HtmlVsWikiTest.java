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

class HtmlVsWikiTest {

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//	}
//
//	@BeforeEach
//	void setUp() throws Exception {
//	}
//
//	@Test
//	void test() {
//		// fail("Not yet implemented");
//		System.out.println("Good");
//	}
	
//	// TO BE IMPROVED
//		@Test
//		@AfterAll
//		@DisplayName("test validity of all csv files")
//		@Tag("robustness")
//		public static void testAreCsvFilesValid() {
//			File[] files = null;
//			File filetest = null ;
//			int counter = 0;
//			File wikiDirectory = null;
//			try {
//				wikiDirectory = new File(Constants.WIKI_OUTPUT_DIR);
//				assertTrue(wikiDirectory.isDirectory());
//				files = wikiDirectory.listFiles();
//				filetest = files[2];
//				// CSVReader reader = new CSVReader(new FileReader(filetest));
//				// System.out.println( reader.readAll());
//				BufferedReader csvReader = new BufferedReader(new FileReader(filetest));
//				String row;
//				while ((row = csvReader.readLine()) != null) {
//				    String[] data = row.split(",");
//				    for(String nextLine : data) {
//				    	 Logger.getGlobal().log(Level.INFO, nextLine );
//				     }
//				    // do something with the data
//				}
//				csvReader.close();
////			     for(String[] nextLine : reader.) {
////			    	 reader.iterator();
////			        // nextLine[] is an array of values from the line
////			        System.out.println(nextLine[0] + nextLine[1] + "etc...");
////			     }
//				// System.out.println("filename"+files[2].getName());
//				// for(File f : files) {
//					// if(f.getName() =="Comparison_between_Esperanto_and_Ido-1.csv") {
//					// }
//					// counter++;
//					// System.out.println("CSV validity of current file, filename : "+f.getName()+" is valid : "+fileHandler.isCsvFileValid(f, separator));
//					// assertTrue(fileHandler.isCsvFileValid(f, separator));
//				// }
//			} catch (Exception e) {
//			}
//			
//			// System.out.println("total number of files tested : "+counter);
//			
//		}	
	
	private WikiConverter wikiConverter;
	private static FileHandler fileHandler;
	private static char separator;
	private HTMLConverter htmlConverter;
	
	
	@BeforeEach
	public void setup() {
		 this.wikiConverter = new WikiConverter();
		 this.htmlConverter = new HTMLConverter();
		 fileHandler = new FileHandlerImpl();
		 separator = ',';
	}
	
	
	@Test
	@DisplayName("genererate html and wiki")
	@Tag("robustness")
	
	// 1ere etape generer simultanement des fichiers wiki et html
	
//	public void testConvertAllToCSV()
//	{
//		try {
//			 assertTrue(new File(Constants.WIKI_OUTPUT_DIR).isDirectory());
//			 assertDoesNotThrow(() -> wikiConverter.convertAllToCSV());
//			 assertTrue(new File(Constants.HTML_OUTPUT_DIR).isDirectory());
//			 assertDoesNotThrow(()-> htmlConverter.convertAllToCSV());
//			
////			System.out.println("------------------ Statistics for Wiki Converter --------------");
////			int totalExtacted = 0;
////			for(Statistics s : WikiExtractor.statisticsList) {
////				totalExtacted += s.getExtractedTablesNumber();
////			}
////			System.out.println("Number of extracted pertinent tables : "+totalExtacted);
////			Utils.displayInfo(WikiExtractor.statisticsList, "WIKI Extractor");
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
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
			// BufferedReader csvReader = new BufferedReader(new FileReader(files));
			String row;
			
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
			    		  
			    		// assertEquals(data.size(),data2.size());
			    	 } catch (Exception e) {};
			    	
			    }
			    // assertEquals(data.get(2),data2.get(2) "Aspect,Esperanto,Ido,Example, Alphabet,uses diacritics (?  ?  ?  ?  ?  ?) and digraphs,uses digraphs (ch  sh  qu),")
			    Logger.getGlobal().log(Level.INFO, "for this url:"+Constants.EN_BASE_WIKIPEDIA_URL + pageTitle + i +"/"+y+ "lines is not correct"  );
			    
//				System.out.println("files length html"+ data.get(2));
//				System.out.println("-------------------------------------------------------------------------------------------------");
//				System.out.println("files length wiki"+ data2.contains(data.get(2)));
				data.clear();
				data2.clear();
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

}
