To start with!!
Go to https://github.com/Hibrahima/PDL_2018_2019_GR6 </br>
 Cloning the project from the Github
#Once the project is on the machine
Place it in the folder of your choice (in the workSpace Eclipse or Intellij récommender) </br>
 The Decompresser
#LET’S ASSUME THAT MAVEN IS ALREADY INSTALLED
# How do I run the application?
 To execute the application, open its terminale, place it at the root of the project and then type the following maven commands:
 NB: The launch of the tests also starts the extraction of the tables found in the wikipedias pages. </br>
 The results (file extracted in csv) are available in the Output Folder which contains 2 subdirectories:  </br>
 HTML, containing the tables go out acquired by the HTML extractor  </br>
 Wiki, Obtained by Wikitext Extractor  </br>
# video demo link
https://drive.google.com/open?id=1SEWEGzS-YkW1Ia2dG_sq7J0WN_6Gn5X
#To Execute All Test cases run this command:
 mvn test 
 There are 20 tests that must all be passed, which allows us to get the CSV files in the Output folder which is composed of 2 repertoir</br>
 One for the HTML extractor and the other for the Wiki extractor
 # HTML
 To run the html conversion test case </br>
 mvn -Dtest=Htmlconvertertest test </br>
 To run the extraction test case in html </br>
 mvn -Dtest=Htmlextractortest test </br>
 # Wikitext
 To run the wiki conversion test case </br>
 mvn -Dtest=Wikiconvertertest test </br>
 To run the wiki extraction test case </br>
 mvn -Dtest=Wikiextractortest test </br>
Make sure you have at least maven 3.8.0 installed before running the tests or configure the version of the maven compiler in the pom.xml file
The project Javadoc can be found in the documentation folder. To browse it, just open the index.html file in a browser. </br>
#THE MOST
To extract tables from a specific page simply create a file "wikiurls.txt" containing the title of the page to be processed. 
Place it in the input/data folder; but be careful you should save the "wikiurls.txt" file of this folder.
#STATISTICS
You can observe higher in the bark of your senior the statistics of the extracted tables and that of the ignored ones!!
#IF MAVEN IS NOT INSTALLED
#Installing Apache Maven
The installation of Apache Maven is a simple process of extracting the archive and adding the bin folder with the mvn command to the PATH.

Detailed steps are:

Assurer JAVA_HOME environment variable is set and points to your JDK installation

Extract distribution archive in any directory

unzip apache-maven-3.6.2-bin. zip
gold

tar xzvf apache-maven-3.6.2-bin.tar.gz
 #Alternatively use your preferred archive extraction tool.

 Add the bin directory of the created directory apache-maven-3.6.2 to the PATH environment variable

 Confirmer avec mvn -v dans une nouvelle shell. The result should look similar to

 Apache Maven 3.6.2 (40f52333136460af0dc0d7232c0dc0bcf0d9e117; 2019-08-27T17:06:16+02:00)
 Maven home: /opt/apache-maven-3.6.2
Java version: 1.8.0_45, vendor: Oracle Corporation
Java home: /Library/Java/Javavirtualmachines/jdk1.8.0_45.jdk/Contents/Home/jre
Default locale: en_US, platform encoding: UTF-8
OS name: "mac os x", version: "10.8.5", arch: "x86_64", family: "mac"
#Windows Tips
Check environment variable value e.g.
 echo %JAVA_HOME% 
 C: Program Files Java jdk1.7.0_51
 Adding to PATH: Add the unpacked distribution’s bin directory to your user PATH environment variable by opening up the system properties (Winkey + Pause), selecting the “Advanced” tab, and the “Environment Variables” button, then adding or selecting the PATH variable in the user variables with the value C: Program Files apache-maven-3.6.2 bin. The same dialog can be used to set JAVA_HOME to the location of your JDK, e.g. C: Program Files Java jdk1.7.0_51

Open a new command prompt (Winkey + R then type cmd) and run mvn -v to verify the installation.

#Unix-based Operating System (Linux, Solaris and Mac OS X) Tips
Check environment variable value
echo $JAVA_HOME
/Library/Java/Javavirtualmachines/jdk1.8.0_45.jdk/Contents/Home
Adding to PATH
export PATH=/opt/apache-maven-3.6.2/bin:$PATH

# Authors:
    As we have already said it this project has been develop by those students
    	Ibrahima HAIDARA
    	Mariam COULIBALY
    	Mahamadou SYLLA
    	Abdoul Hamide BA
    Now it's our turn to improve their work
	Othniel KONAN
    Fabrice KADIO
    MAIMOUNA SANE
    Issa KEITA
    Awa DOUMBIA
    Coumba MBOUP
