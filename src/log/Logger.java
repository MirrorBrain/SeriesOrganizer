/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package log;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * The Class Logger.
 */
public class Logger {

	/** The BufferedWriter. */
	private static BufferedWriter bw;

	/** The Constant date format. */
	private static final SimpleDateFormat dmy = new SimpleDateFormat("yyyy_MM_dd");

	/** The Constant logName. */
	private static String logName = "SeriesOrganizer_";

	/** The logs. */
	private static ArrayList<Log> logs = new ArrayList<>();

	/** The boolean variables. */
	private boolean write = true;

	/**
	 * Instantiates a new logger.
	 *
	 * @param path
	 *            the path to LOG
	 */
	public Logger(String path) {
		logName = new String(path + "/" + logName + dmy.format(new Date()) + ".log");

		try {
			createLogFile();
			bw = new BufferedWriter(new FileWriter(logName, true));

			bw.write("****************************** Execution Start ******************************\n");
			bw.flush();
		} catch (IOException e) {
			write = false;
			logs.add(Log.error("Error on log file opening : " + e.getStackTrace().toString()));
		}
	}

	/**
	 * Adds the given log.
	 *
	 * @param log
	 *            the log
	 */
	public void add(Log log) {
		if (write)
			writeLog(log);
		logs.add(log);
	}

	/**
	 * Closes the logger.
	 */
	public void close() {
		try {
			Date d = new Date(new Date().getTime() - logs.get(0).getDate().getTime() - 3599999);
			bw.write(
					"*************** Execution ending -- Execution time : " + Log.hms.format(d) + " ***************\n");
			bw.close();
		} catch (IOException e) {
			logs.add(Log.error("Error on log closing : " + e.getStackTrace().toString()));
		}
	}

	/**
	 * Creates the log file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	private void createLogFile() throws IOException {
		Path path = Paths.get(logName);

		Files.createDirectories(path.getParent());

		if (!Files.exists(path))
			Files.createFile(path);
	}

	/**
	 * Writes the given log.
	 *
	 * @param l
	 *            the l
	 */
	public void writeLog(Log l) {
		try {
			bw.write(l.toString());
			bw.flush();
		} catch (IOException e) {
			write = false;
			logs.add(Log.error("Error on log writing : " + e.getStackTrace().toString()));
		}
	}
}
