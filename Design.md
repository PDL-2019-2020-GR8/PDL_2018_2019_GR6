  ## Context
  This document aims at describing the architectural elements of the application.
  The architecture put in place is the two-third or "client-server" in the sense that the application requests a server service set up by mediawiki, the server receives this request, it performs a page processing, and returns the requested resource which is the last revision of the concerned Wikipedia page. At the end of this restitution, the data are extracted from these pages and then converted into csv.
  From a technical point of view, we first present the different UML diagrams, then giving details of the different functional specifications.


## Static models

# Organization of packages

Java packages are considered as a mechanism for organizing classes.
In this project, the packages are structured to make it easy for members to access methods. 
We can observe the following packages: 

##Package HELPER: it contains all the classes that will be shared by all the methods of the application especially specific classes like:
 ApiCaller which allows to connect (method: getResponse (String languageVariant, String pageTitle)) to the API and returns the wikitext (method: extractWikitextFromApiResponse (String languageVariant, String pageTitle)).

## FileHandlerImpl </br>
Which allows to create (method: extractFilenameFromUrl (Url string, int number), write (method: write (string filepath, string filename, list <string> data)) and check (method: isCsvValid (file file, char separator)) the validity of the CSV file.

## WikiRunner
Allows to parse the wikitext in html then to convert the html to a Jsoup object to perform the different processes (method: getDocument (String languageVariant, String pageTitle)).


## Package INTERFACE: 
It contains a description of all the functionalities of the application that will be developed, especially data extraction, data conversion to csv, creation of csv files and statistics.
Extractor: extract the data
Converter: convert to csv file
## Filehandler: 
Recover the extracted data and write it to a file.
Statistics: generate statistics

## Package WIKIPEDIA HTML: 
It contains concrete classes defining the implementation of data extraction and conversion methods of a html file.

## Package WIKIPEDIA WIKI 
It contains the concrete classes defining the implementation of methods for extracting and converting data from a wikitext file.

The launch of the application is done with maven test that allows executing a series of instructions contained in the following methods:
-	TestConvertAlltoCSV (): generates the csv files and lists the statistics. 

Test example:
            AssertDoesNotThrow (() ->wikiconverter.convertAlltoCSV()) ;

-	TestprocessTdTextTwithseparatorandnumber () tests column data with characters and numbers, to avoid conflicts.

-	Testarecsvfilesvalid () check the validity of all Csv files.


## Dynamic models:

This section describes the UML architecture of the application.
We did not consider it necessary to make a diagram as it does not reflect relevant interaction.
 As a result, we present only class and sequence diagrams to illustrate the relevant flows.

### Class diagram For V1
![Diagramme de classe](https://user-images.githubusercontent.com/45700033/66901199-8a262c80-effe-11e9-8481-563450130ee9.jpeg)

### Class diagram For V2
![new Diagram](https://user-images.githubusercontent.com/45700033/71295525-7f7c7080-237c-11ea-8ebb-853e9e8a0f06.png)

### Sequence diagram

After launching the application, it follows, the generation of CSV files by the main method ConvertAlltoCSV (). However, this operation is done in several steps depending on the type of file. 

Generating csv file from an html

![DShtml](https://user-images.githubusercontent.com/45700033/66901386-e426f200-effe-11e9-8a7d-ca514e793037.png)


-	 After connecting to the API and retrieving the html document, with the help of the extractTable (Document doc, string url) method of the htmlExtractor class, we treat the JSOUP document by extracting the tables.
-	Then, once the tables are extracted, the csv files are created using the extractFilenameFromURL (string url, int number) and write (string filepath, string filename, list <string> data) methods both of the Filehandler class.


•	Generating csv file from a wikitext

![DSwiki](https://user-images.githubusercontent.com/45700033/66901440-fef96680-effe-11e9-9752-dd58b8019be3.png)

- First, the JSOUP document is retrieved after the html has been parsed using the getDocument method (string LanguageVariant, string pagetitle) which is a method of the wikiRunner class depending on the variant of the language.
-	Then, using the extractable (Document doc, string url) method of the wikiExtractor class, we process the JSOUP document by extracting the tables.
-	Then, once the tables are extracted, csv files are created using the extractFilenameFromURL (string url, int number) and write (string filepath, string filename, list <string> data) methods both of the Filehandler class.

NB : These instructions are repeated until all titles are covered.
