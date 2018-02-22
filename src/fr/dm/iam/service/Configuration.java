package fr.dm.iam.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.LogManager;

public class Configuration  {

		private static Configuration instance;

		private final Properties properties;

		public static Configuration getInstance() {
			if (instance == null) {
				instance = new Configuration();
			}
			return instance;
		}

		private Configuration() {
			properties = new Properties();
			try {
				properties.load(new FileInputStream(System.getProperty("conf")));
			} catch (final IOException e) {
				System.out.println("error while loading the configuration");
			}
		}

		public String getProperty(String key) {

			return properties.getProperty(key);

		}

		public boolean containsProperty(String key) {
			return properties.containsKey(key);
		}


	}
