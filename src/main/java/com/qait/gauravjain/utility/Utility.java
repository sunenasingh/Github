
package com.qait.gauravjain.utility;

import java.io.*;
import java.util.*;
import org.yaml.snakeyaml.Yaml;

public class Utility {
	private static Map<String,String> testData;
	
	//reading config file
	public static String getConfigValue(String key) {
		Properties prop = new Properties();
		try(InputStream ip = new FileInputStream(new File("credentials.config"))){
			if(ip!=null)
				prop.load(ip);
		}catch (FileNotFoundException ex) {
			ex.printStackTrace();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		return (String)prop.getProperty(key);
	}

	//reading yaml file
	private static void yamlParser(){
		Yaml yaml = new Yaml();
		Reader reader = null;
		try {
			reader = new FileReader("src/test/resources/config.yml");
			testData = (Map<String,String>) yaml.load(reader);
		} catch (final FileNotFoundException fnfe) {
			System.err.println("We had a problem reading the YAML from the file because we couldn't find the file." + fnfe);
		} finally {
			if (null != reader) {
				try {
					reader.close();
				} catch (final IOException ioe) {
					System.err.println("We got the following exception trying to clean up the reader: " + ioe);
				}
			}
		}
	}
	
	public static String getYamlValues(String key) {
		yamlParser();
		return  testData.get(key);
	}
	
	//writing to file
	public static void writeToFile(String args, List<String> ls) {
		try {
			PrintWriter pw = new PrintWriter(args);
			for(String line: ls)
			{
				pw.println(line);
			}
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
