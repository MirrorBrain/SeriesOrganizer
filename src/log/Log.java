/*
 * Some file written fast to kill some time... Do whatever you want with it ;)
 * But don't forget to mention my name somewhere at least !
 * @author Ma�l Nogues mael.nogues@outlook.com
 */
package log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class Log.
 */
public class Log {

	/** The Constant ERROR. */
	public static final int	ERROR	= -1;
	/** The Constant date format. */
	static final SimpleDateFormat hms = new SimpleDateFormat("HH:mm:ss");
	/** The Constant INFOS. */
	public static final int	INFOS	= 0;

	/** The Constant WARNING. */
	public static final int	WARNING	= 1;

	/**
	 * Instantiates an error log.
	 *
	 * @param m
	 *            the message
	 * @return the log
	 */
	public static Log error(String m) {
		return new Log(ERROR, m);
	}

	/**
	 * Instantiates an info log.
	 *
	 * @param m
	 *            the message
	 * @return the log
	 */
	public static Log info(String m) {
		return new Log(INFOS, m);
	}

	/**
	 * Instantiates a warning log.
	 *
	 * @param m
	 *            the message
	 * @return the log
	 */
	public static Log warning(String m) {
		return new Log(WARNING, m);
	}

	/** The date. */
	private Date date;

	/** The message. */
	private String message;

	/** The type. */
	private int type;

	/**
	 * Instantiates a new log.
	 *
	 * @param t
	 *            the type
	 * @param m
	 *            the message
	 */
	public Log(int t, String m) {
		type = t;
		date = new Date();
		message = m;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the type in a string.
	 *
	 * @return the type string
	 */
	private String getTypeString() {
		String ret = "";
		switch (type) {
			case INFOS:
				ret = "INFOS";
				break;

			case WARNING:
				ret = "WARNING";
				break;

			case ERROR:
				ret = "ERROR";
				break;
		}
		return ret;
	}

	/**
	 * Checks if is error.
	 *
	 * @return true, if is error
	 */
	public boolean isError() {
		return type == ERROR;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ret = "";

		ret += hms.format(date) + " - ";
		ret += getTypeString() + " : ";
		ret += message + "\n";

		return ret;
	}
}
