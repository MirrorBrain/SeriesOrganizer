/*
 * Some file written fast to kill some time... Do whatever you want with it ;)
 * But don't forget to mention my name somewhere at least !
 * @author Maël Nogues mael.nogues@outlook.com
 */
package tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

// TODO: Auto-generated Javadoc
/**
 * The Class Series.
 */
public class Series {

	/** The Series list. */
	private String[]	SeriesList;
	/** The path to series. */
	private String		path;

	/**
	 * Instantiates a new series.
	 *
	 * @param iniFile
	 *            the ini file
	 * @param path
	 *            the path to series
	 */
	public Series(File iniFile, String path) {
		SeriesList = parse(iniFile);

		if (SeriesList == null) {
			System.err.println("ini file not readable, no series to archive");
			System.exit(0);
		} else if (SeriesList.length == 0) {
			System.err.println("ini file empty, no series to archive");
			System.exit(0);
		}

		this.path = path;
	}

	/**
	 * Parses the file.
	 *
	 * @param iniFile
	 *            the ini file
	 * @return the string[]
	 */
	private String[] parse(File iniFile) {
		String[] result = null;

		try {
			BufferedReader br = new BufferedReader(
					new InputStreamReader(new FileInputStream(iniFile.getAbsolutePath())));

			result = (String[]) br.lines().toArray();

			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * Archive the given file.
	 *
	 * @param file
	 *            the file
	 */
	public void archive(File file) {
		for (int i = 0; i < SeriesList.length; i++) {
			if (file.getName().contains(SeriesList[i])) {
				if (file.renameTo(new File(path + SeriesList[i] + "\\" + file.getName()))) {
					System.out.println(file.getName() + " archived successfully");
				} else {
					System.err.println(file.getName() + " not archived correctly");
				}

				return;
			}
		}

		System.err.println("No series where found in your list to match the file \"" + file.getName() + "\"");
	}

}
