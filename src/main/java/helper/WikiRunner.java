package helper;

import java.io.IOException;
import org.eclipse.mylyn.wikitext.mediawiki.MediaWikiLanguage;
import org.eclipse.mylyn.wikitext.parser.MarkupParser;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import info.bliki.wiki.model.WikiModel;


/**
 * 
 * @author Ibrahima HAIDARA
 * @author Mariam Coulibaly
 * @author Mahamadou Sylla
 * @author Abdoul Hamide Ba
 *
 */

/**
 * 
 * Gets the wikitext and parses it to html using Mylyn 
 * Then a Jsoup document is created with the parsed html
 *
 */
public class WikiRunner {

	/**
	 * Creates a Jsoup document from the parsed html
	 * 
	 * @param languageVariant the languave to use to make api calls
	 * @param pageTitle the title of the page
	 * @return that document
	 * @throws IOException if the response from the api is not readable
	 * @throws UrlNotFoundException if the page does not exist
	 */
	public static Document getDocument(String languageVariant, String pageTitle)
			throws IOException, UrlNotFoundException {
		String wikitext = ApiCaller.extractWikitextFromApiResponse(languageVariant, pageTitle);
		String html = null;
		String htmlText = null;
		Document doc = null;
		if (wikitext != null) {
			try {

//				MarkupParser markupParser = new MarkupParser();
//				markupParser.setMarkupLanguage(new MediaWikiLanguage());
				htmlText = WikiModel.toHtml(wikitext);

			} catch (Exception e) {
				e.printStackTrace();
			}
			
			doc = Jsoup.parse(htmlText);
		}

		return doc;

	}

}
