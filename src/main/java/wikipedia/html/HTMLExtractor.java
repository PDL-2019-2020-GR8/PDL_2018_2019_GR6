package wikipedia.html;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.regex.Pattern;
import org.jsoup.HttpStatusException;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import helper.Constants;
import helper.Constrains;
import helper.StatisticsImpl;
import interfaces.Extractor;
import interfaces.Statistics;

/**
 *
 * @author Ibrahima HAIDARA
 * @author Mariam Coulibaly
 * @author Mahamadou Sylla
 * @author Abdoul Hamide Ba
 *
 */

public class HTMLExtractor implements Extractor {
	private static final String wiki_regex = "^https:\\//[a-z]{2}\\.wikipedia\\.org\\/wiki/.+";
	private Statistics statistics;
	public static Collection<Statistics> statisticsList;

	public HTMLExtractor() {
		HTMLExtractor.statisticsList = new ArrayList<>();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isWikipediaUrl(String url) {
		return Pattern.matches(wiki_regex, url);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Elements extractTables(Document doc, String url) throws IOException, HttpStatusException {
		Elements tableElements = null;
		statistics = new StatisticsImpl();
		if (!isWikipediaUrl(url)) {
			System.out.println(url + " is not valid");
		} else {
			tableElements = doc.select("table");
			System.out.println(tableElements);
			int initialSize = tableElements.size();
			tableElements = convertThsToTds(tableElements);
			tableElements = formatTables(tableElements);
			tableElements = fillEmptyTds(tableElements);
			tableElements = ignoredElements("div", tableElements);
			tableElements = ignoredElements("code", tableElements);
			tableElements = ignoredElements("ul", tableElements);
			tableElements = ignoredClasses(tableElements);
			//tableElements = ignoredElements("img", tableElements);
			tableElements = ignoredElements("p", tableElements);
			//tableElements = ignoredElements(url, "br", tableElements);
			//tableElements = ignoreTablesWithLessRows(url, tableElements, 3);
			int finalSize = tableElements.size();
			statistics.setUrl(url);
			statistics.setIgnoredTablesNumber(initialSize - finalSize );
			statistics.setExtractedTablesNumber(finalSize);
			HTMLExtractor.statisticsList.add(statistics);
		}
		return tableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Elements ignoredClasses(Elements tableElements) {
		String[] currentTableClasses;//on creer un tableau qui va contenir la declaration des differentes classes
		for (Element currentTable : tableElements) {
			/* on retrounre le nom de la classe, si ce nom contient des espaces on divise le nom de la classe en sous chaine
			 * qu'on met dans le tableau currentTableClasses
			 * input = "othniel  Konan
			 * input.split(" ")
			 * output = [othniel], [Konan]   */
			currentTableClasses = currentTable.className().split(" ");
			for (String cs : currentTableClasses) {
				if (cs.startsWith(Constrains.INFOBOX.getConstrainName())) {
					currentTable.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE);
				}

			}
			if (currentTable.hasClass(Constrains.NAVBOX.getConstrainName())
					&& currentTable.hasClass(Constrains.COLLAPSBLE.getConstrainName())
					&& currentTable.hasClass(Constrains.NOPRINT.getConstrainName())
					&& currentTable.hasClass(Constrains.AUTOCOLLAPSE.getConstrainName())) {
				currentTable.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE);
			}


		}


		return tableElements.not("."+Constants.GENERIC_CLASS_NAME_TO_REMOVE);
	}

	/**
	 * Converts ths to tds
	 *
	 * @param tableElements the tables whose ths are transformed to tds
	 * @return the processed tables
	 */
	private Elements convertThsToTds(Elements tableElements) {
		for (Element currentTable : tableElements) {
			Elements currentTableRowElements = currentTable.select("tr");
			for (int i = 0; i < currentTableRowElements.size(); i++) {
				Element currentRow = currentTableRowElements.get(i);
				for (Element ex : currentRow.children()) {
					if (ex.is("th"))
						ex.tagName("td");
				}
			}
		}
		return tableElements;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Elements ignoredElements(String tag, Elements tableElements) {
		for (Element currentTable : tableElements) {
			Elements currentTableRowElements = currentTable.select("tr");
			Elements currentTdTags;
			for (int i = 0; i < currentTableRowElements.size(); i++) {
				Element currentRow = currentTableRowElements.get(i);
				Elements currentRowItems = currentRow.select("td");
				Elements TdInnerTables =  currentRowItems.select("table"); //selectionne si il y a une balise table a l'interieur d'un td
				if(TdInnerTables.size() > 0) {
					// si c'est le cas alors
					/*Elements	addClass​(String className)	Ajoutez le nom de la classe à l' class attribut de chaque élément correspondant */
					TdInnerTables.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE); //on ajout a l'attribut table la constante GENERIC_CLASS_NAME_TO_REMOVE
					currentRow.remove();
				}

				for (int j = 0; j < currentRowItems.size(); j++) {
					currentTdTags = currentRowItems.get(j).select(tag); // pour chaque td  on selection le Tag
					//if (currentRowItems.get(j).hasAttr(Constants.ROW_SPAN_ATTRIBUTE)// s'il a un rowSpan
					//		|| currentRowItems.get(j).hasAttr(Constants.COL_SPAN_ATTRIBUTE)// ou un colSpan
					//		|| currentRowItems.get(j).hasClass(Constants.MBOX_IMAGE_CLASS)) //ou un Mbox_Image
					//{
					//	currentTable.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE); //on ajoute un attribut de type GENERIC_CLASS_NAME_TO_REMOVE
					//	}

					if(currentRowItems.get(j).hasClass("extra_td_to_remove"))
						currentRowItems.get(j).remove(); // on supprime tous les td qui on un attribut de class "extra_td_to_remove"


					if (!currentTdTags.isEmpty()) {
						/*Si le tag founis en paramettre à la fonction ignoredElements() est égale à 'div' ou 'code'
						 * alors add */
						if (tag.equals("p") || tag.equals("br")) {
							//currentRowItems.get(j).remove();
						}
						if(tag.equals("div") || tag.equals("code"))
							currentTable.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE);
					}
				}

			}
		}
		/*ici on supprime tous les tableElemetns qui on un attribut de type GENERIC_CLASS_NAME_TO_REMOVE*/
		return tableElements.not("."+Constants.GENERIC_CLASS_NAME_TO_REMOVE);
		/*Element.not(String Query) --> Supprimez de la liste les éléments correspondant à la Selectorrequête.*/
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Elements ignoreTablesWithLessRows(Elements tableElements, int numberOfRows) {
		for (Element currentTable : tableElements) {
			Elements currentTableRowElements = currentTable.select("tr");
			if (currentTableRowElements.size() < numberOfRows) {
				currentTable.addClass(Constants.GENERIC_CLASS_NAME_TO_REMOVE);
			}

		}
		return tableElements.not("."+Constants.GENERIC_CLASS_NAME_TO_REMOVE);
	}

	private Elements fillEmptyTds(Elements tableElements) {
		for (Element currentTable : tableElements) {
			Elements currentTableRowElements = currentTable.select("tr");
			for (Element currentRow : currentTableRowElements) {
				for (Element currentTd : currentRow.select("td")) {
					if (currentTd.text().equals("") || currentTd.text().length() == 0)
						currentTd.text("[empty]");
				}
			}
		}
		return tableElements;
	}

	private void addClassToRemoveExtraTds(int firstRowTdsCount, Element tr) {
		int trTdsCount = tr.select("td").size();
		for(int i=firstRowTdsCount; i<trTdsCount; i++) {
			tr.select("td").get(i).addClass("extra_td_to_remove");
		}
	}

	private void addTdsTofitFirstRow(Element tr, int firstRowTdsCount) {
		int trTdsCount = tr.select("td").size();
		int diffCount = firstRowTdsCount - trTdsCount;
		for(int i=0; i<diffCount; i++)
			tr.appendChild(new Element("td").text("[added]"));
	}

	private Elements formatTables(Elements tableElements) {
		Element firstRow;
		int firstRowTdsCount;
		for (Element currentTable : tableElements) {
			firstRow = currentTable.select("tr").get(0);
			firstRowTdsCount = firstRow.select("td").size();
			Elements currentTableRowElements = currentTable.select("tr");
			for (int i=1; i<currentTableRowElements.size(); i++) {
				if(currentTableRowElements.get(i).select("td").size() < firstRowTdsCount)
					addTdsTofitFirstRow(currentTableRowElements.get(i), firstRowTdsCount);
				else if(currentTableRowElements.get(i).select("td").size() > firstRowTdsCount)
					addClassToRemoveExtraTds(firstRowTdsCount, currentTableRowElements.get(i));
			}
		}
		return tableElements;
	}

}