package fr.dm.iam.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
	
	/** The connection object. */
	private static Connection con;
	
	/** The properties file. */
	private static Properties properties;
	
	private static final String CONF="conf";
	
	private static final String DRIVER="conf";
	
	private static final String DB_URL="url";
	
	private static final String DB_USER="user";
	
	private static final String DB_PASSWORD="password";
	

	static {
		properties = initialiseProperties();
	}
	
	
	/**
	 * Private Constructor.
	 * 
	 */
	private DBConnection() {
		
	}
	
	/**
	 * Your Story
	 * 
	 * @return Properties the property
	 */
	private static Properties initialiseProperties() {
		properties = new Properties();
		InputStream iStream;
		try {
			iStream = new FileInputStream(System.getProperty(CONF));
			properties.load(iStream);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return properties;
	}

	/**
	 * Method to create and return a database connection
	 * 
	 * @return Connection The connection object
	 */
	public static Connection getConnection(){
		
			try {
				Class.forName(properties.getProperty(DRIVER)).newInstance();
				con=DriverManager.getConnection(properties.getProperty(DB_URL), properties.getProperty(DB_USER), 
						properties.getProperty(DB_PASSWORD));
				
			} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			}
			
		return con;
	}

}
