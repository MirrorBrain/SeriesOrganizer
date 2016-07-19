/*
 * Some file written fast to kill some time... Do whatever you want with it ;)
 * But don't forget to mention my name somewhere at least !
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

	/**
	 * Ext.
	 *
	 * @return the string
	 */
	public String ext() {
		return ini.getProperty("format");
	}

}
