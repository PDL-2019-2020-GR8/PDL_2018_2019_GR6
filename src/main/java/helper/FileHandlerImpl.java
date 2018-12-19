package helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import interfaces.FileHandler;

/**
 * 
 * @author Ibrahima HAIDARA
 * @author Mariam Coulibaly
 * @author Mahamadou Sylla
 * @author Abdoul Hamide Ba
 *
 */

/**
 * Handles file-related operations
 *
 */

public class FileHandlerImpl implements FileHandler {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void write(String filePath, String filename, List<String> data) {
		FileWriter fw = null;
		BufferedWriter bw = null;
		File f = null;
		try {
			f = new File(filePath + File.separator + filename);
			if (!f.exists()) {
				fw = new FileWriter(f, true);
			} else {
				if (f.delete())
					fw = new FileWriter(f, true);
			}

			bw = new BufferedWriter(fw);
			for (String line : data) {
				bw.write(line);
				bw.newLine();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (f.exists()) {
					bw.close();
					fw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		}

	}

	/**
	 * {@inheritDoc}
	 */
	public String extractFilenameFromUrl(String url, int number) {
		return url + "-" + number + ".csv";

	}
	
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isCsvFileValid(File file, char separator) {
		CSVParser parser = null;
		BufferedReader br = null;
		FileReader reader;

		try {
			reader = new FileReader(file);
			br = new BufferedReader(reader);
			parser = new CSVParserBuilder().withSeparator(separator).build();
			CSVReader csvReader = new CSVReaderBuilder(br).withCSVParser(parser).build();

			csvReader.readAll();
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

}
