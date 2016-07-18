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

import log.Log;
import log.Logger;

/**
 * The Class Series.
 */
public class Series {

	/** The path to series. */
	private String		path;
	/** The Series list. */
	private String[]	SeriesList;

	/** The log. */
	private Logger log;

	/**
	 * Instantiates a new series.
	 *
	 * @param iniFile
	 *            the ini file
	 * @param path
	 *            the path to series
	 * @param log
	 */
	public Series(String iniFile, String path, Logger log) {
		SeriesList = parse(iniFile);
		this.log = log;

		if (SeriesList == null) {
			this.log.add(Log.error("ini file not readable, no series to archive"));
			SeriesList = new String[0];
		} else if (SeriesList.length == 0) {
			this.log.add(Log.error("ini file empty, no series to archive"));
		}

		this.path = path;
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
					log.add(Log.info(file.getName() + " archived successfully"));
				} else {
					log.add(Log.info(file.getName() + " not archived correctly"));
				}

				return;
			}
		}

		log.add(Log.error("No series where found in your list to match the file \"" + file.getName() + "\""));
	}

	/**
	 * Parses the file.
	 *
	 * @param iniFile
	 *            the ini file
	 * @return the string[]
	 */
	private String[] parse(String iniFile) {
		String[] result = null;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(iniFile)));

			result = (String[]) br.lines().toArray();

			br.close();
		} catch (FileNotFoundException e) {
			log.add(Log.error(""));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

}
