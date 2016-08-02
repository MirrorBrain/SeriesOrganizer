/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
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
	private static File[] files;

	/** The log. */
	private static Logger log;

	/** The ini file. */
	private static String	iniFile	= "SeriesOrganizer.ini";
	/** The logging. */
	private static String	logging;
	/** Where to archive. */
	private static String	output;
	/** The path to your files. */
	private static String	path;
	/** The ext. */
	protected static String	ext;

	/**
	 * List files.
	 */
	private static void listFiles() {
		files = new File(path).listFiles((FilenameFilter) (dir, name) -> name.endsWith(ext));

		if (files.length == 0) {
			log.add(Log.warning("file list is empty"));
			return;
		}

		log.add(Log.info("file listed"));
	}

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

		log.close();
	}

	/**
	 * Organize files.
	 */
	private static void organizeFiles() {
		Series s = new Series("series", output, log);

		for (File file : files)
			s.archive(file);
	}

	/**
	 * Read ini.
	 */
	private static void readIni() {
		IniFile ini = new IniFile(iniFile);

		path = ini.path();
		output = ini.output();
		ext = ini.ext();
		logging = ini.log();

		log = new Logger(logging);
	}

}
