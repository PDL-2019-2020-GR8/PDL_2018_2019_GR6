# General context

Every project requires regular evaluation, to see the progress made and the work done 
according to the plan of action. It is in this context that we must take up the project of our 
colleagues by looking at the organization and the quality of the code. Thereafter, the limits of 
the project allow us to analyse its performance in order to initiate a process of improvement. 
These steps concern the improvement of the quality of the code while realising the automatic 
tests, the addition of new functionalities to overcome the limits of the project.

# Project definition

The project is named Wikipedia matrix or truth. The goal of the project is to set up a tool for 
automatic extraction of tabular data in the form of CSV from Wikipedia URLs. As a matter of 
fact, these obtained CSV files can facilitate the exploitation of these data with data 
visualization software, in order to analyse them.

# Technologies used

The programming language used for this project is java. So several technologies are used to
accomplish the project, we can list:

##### Bliki 
in the existing project, they used mylyn for wiki extraction in html. However we decided to use bliki because it collects more data than Mylyn.
  *Bliki* is a parser library for converting Wikipedia wikitext notation to HTML
  
##### Jsoup
 *Jsoup* is an open source Java library designed to parse, extract, and manipulate data stored in 
HTML documents. This library is used to parse and process the content of the HTML pages 
obtained during the parsing of Mylyn. The version 1.11.3 of Jsoup is used in the project. In 
this project, Jsoup uses the DOM, CSS and Jquery methods to extract and manipulate the
tables of the different contents of the HTML pages. Indeed, it turns into HTML content.

##### JUnit 5 
*JUnit 5* is an open source framework used to develop and run automatic unit tests. This 
framework is used to test the extractors and converters of the project.

##### Apache Maven
 *Apache Maven* is a Java project management tool that manages the dependency of our project 
and automates its construction such as compilation, tests and production deliverable.

##### Open CSV
*Open CSV* is an open source Java library and a CSV file parser. The version 4 is used in the 
project to test the validity of generated CSV files.

##### API action media wiki
*API action media wiki* is a web service that allows access to certain functions of the wiki. 
This service made it possible to connect to the wiki server and to retrieve the latest version of the Wikipedia pages from the media wiki server.

The first version of the project supports several features for extraction of the tables; however 
this project also sets out limits which will be the object of improvement in order to add new 
functionalities.

# Supported features

The project supports several features namely, the analysis of the content of the tables 
extracted through an algorithm. The project also offers as a feature, the search for the 
Wikipedia page in the French version if it does not exist in English. Also when running tests, 
URLs that do not exist in the English and French versions are put in a red colour. Then the 
corresponding csv files are deleted if they already exist and then the new files are generated. 
For reasons of relevance, statistical tables are generated, these tables will allow us to know
the number of tables in percentage which were extracted and those that could not be extracted 
in the Wikipedia pages. Finally the tool offers the possibility to change the separation 
character in CSV files.

# Features to be developed in the future

After analysing the project, we see that there are improvements to be made by creating new 
features.

# Improvement

Since with Mylyn we have data loss during the parsing, it would be interesting to set up a 
library that would reduce the loss of information when parsing wikitext in html.
For this we used Bliki which allows to recover more data than Mylyn.

# New features
*       To test the contents of two extractors to see if the texts are the same

*        Try to have the same number of tables between two extractors

*        To test if what we have locally corresponds to what we have on Wikipedia

*        An editor to manually correct CSV file data if they are not inserted correctly

*        Extract tables from a single Wikipedia page into a CSV file.

# Developped features

After fixing some functionalities and issues to develop, here is what we managed to settle.

*       To test the contents of two extractors to see if the texts are the same

For this test case, it is difficult to have identical content because the extractors do not have the same way of generating the CSV files. For html it recovers the raw data and generates the CSV files and for wiki it also recovers the data, then goes through a parsing step and it is during this step that it  generates special characters

*        To test if what we have locally corresponds to what we have on Wikipedia

To test this case we take a specific URL then we choose an online cell corresponding to this URL and we test with a cell our local data of this same URL.

*        Extract tables from a single Wikipedia page into a CSV file.

To solve this problem, we just set a specific URL in the input file.

*        Try to have the same number of tables between two extractors

This problem was solved by ignoring the constraints of nested arrays on Html, because we deemed it relevant.


# Project License

The software developed is open source software.

# Authors

 ### As we have already said it this project has been develop by those students
	Ibrahima HAIDARA
	Mariam COULIBALY
	Mahamadou SYLLA
	Abdoul Hamide BA
 ### Now it's our turn to improve their work
    Othniel KONAN
    Fabrice KADIO
    MAIMOUNA SANE
    Issa KEITA
    Awa DOUMBIA
    Coumba MBOUP
