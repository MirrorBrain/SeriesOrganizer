/*
 * Some file written fast to kill some time... Do whatever you want with it ;)
 * But don't forget to mention my name somewhere at least !
 * @author Maël Nogues mael.nogues@outlook.com
 */
package main;

import java.io.File;
import java.io.FilenameFilter;

import log.Log;
import log.Logger;
import tools.IniFile;
import tools.Series;

/**
 * The Class SeriesOrganizer.
 */
public class SeriesOrganizer {

	/** The files. */
	private static File[]	files;
	/** The ini file. */
	private static File		iniFile	= new File("SeriesOrganizer.ini");

	/** The path to your files. */
	private static String path;

	/** Where to archive. */
	private static String output;

	/** The logging. */
	private static String logging;

	/** The log. */
	private static Logger log;

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 */
	public static void main(String[] args) {
		readIni();

		listFiles();

		organizeFiles();
	}

	/**
	 * Read ini.
	 */
	private static void readIni() {
		IniFile ini = new IniFile("");

		path = ini.path();
		output = ini.output();
		logging = ini.log();

		log = new Logger(logging);
	}

	/**
	 * Organize files.
	 */
	private static void organizeFiles() {
		Series s = new Series(iniFile, output);

		for (int i = 0; i < files.length; i++) {
			s.archive(files[i]);
		}
	}

	/**
	 * List files.
	 */
	private static void listFiles() {
		files = new File(path).listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".sas");
			}
		});

		if (files.length == 0) {
			log.add(Log.warning("File list is empty"));
			return;
		}

		log.add(Log.info("SQL file listed"));
	}

}
