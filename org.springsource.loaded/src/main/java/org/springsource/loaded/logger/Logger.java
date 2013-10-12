package org.springsource.loaded.logger;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A very basic Logging api replacement (Avoid conflicts with the running app's setup)
 * 
 * @author lgvier
 */
public class Logger {
	
	private static final Level LEVEL = Level.WARNING;
	private static final Map<String, Logger> INSTANCES = new HashMap<String, Logger>();
	
	public static synchronized Logger getLogger(String name) {
		Logger logger = INSTANCES.get(name);
		if (logger == null) {
			logger = new Logger(name);
			INSTANCES.put(name, logger);
		}
		return logger;
	}
	
	private String name;
	
	private Logger(String name) {
		this.name = name;
	}

	public boolean isLoggable(Level level) {
		return level.ordinal() <= LEVEL.ordinal();
	}

	public void entering(String sourceClass, String sourceMethod, Object... params) {
		
	}

	public void exiting(String sourceClass, String sourceMethod, Object... params) {

	}
	
	public void throwing(String sourceClass, String sourceMethod, Throwable thrown) {
		
	}
	
	public void log(Level level, Object message, Throwable t) {
		if (isLoggable(level)) {
			log(level, message + " - " + t.toString());
		}
	}

	public void log(Level level, Object message) {
		if (isLoggable(level)) {
			System.out.println(new Date() + " " + name + ": " + message);
		}
	}

	public void info(Object message) {
		log(Level.INFO, message);
	}
	
	public void finer(Object message) {
		log(Level.FINER, message);
	}
	
	public void finest(Object message) {
		log(Level.FINEST, message);
	}
	
	public void warning(Object message) {
		log(Level.WARNING, message);
	}

	public void severe(Object message) {
		log(Level.SEVERE, message);
	}

}
