/*
 * The GNU General Public License does not permit incorporating your program
 * into proprietary programs. If your program is a subroutine library, you may
 * consider it more useful to permit linking proprietary applications with the
 * library. If this is what you want to do, use the GNU Lesser General Public
 * License instead of this License. But first, please read
 * <http://www.gnu.org/philosophy/why-not-lgpl.html>.
 * @author Maël Nogues mael.nogues@outlook.com
 */
package tools;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * The Class IniFile.
 */
public class IniFile {

	/** The ini. */
	private Properties ini = new Properties();

	/**
	 * Instantiates a new ini file.
	 *
	 * @param file
	 *            the file
	 */
	public IniFile(String file) {
		try {
			ini.load(new FileReader(file));
		} catch (IOException e) {
			System.err.println("Unable to open ini file");
			e.printStackTrace();
			System.exit(0);
		}

	}

	/**
	 * Ext.
	 *
	 * @return the string
	 */
	public String ext() {
		return ini.getProperty("format");
	}

	/**
	 * Log.
	 *
	 * @return the string
	 */
	public String log() {
		return ini.getProperty("log");
	}

	/**
	 * Output.
	 *
	 * @return the string
	 */
	public String output() {
		return ini.getProperty("output");
	}

	/**
	 * Path.
	 *
	 * @return the string
	 */
	public String path() {
		return ini.getProperty("files");
	}

}
