package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Properties;

public class ConfigurationDataProvider {

	Properties pro= new Properties();
	File src=new File("./Config/Config.properties");

	public ConfigurationDataProvider() {

		try {
			FileInputStream inputFile = new FileInputStream(src);
			//			pro = new Properties();
			pro.load(inputFile);
		} catch (Exception e) {
			System.out.println("Not able to load Config properties file");
		}
	}

	public String getFromConfig(String KeyToSearch) {
		return pro.getProperty(KeyToSearch);
	}

	//	public String getBrowser() {
	//		return pro.getProperty("Browser");
	//	}
	//	
	//	public String getTestUrl() {
	//		return pro.getProperty("testUrl");
	//	}

	public void setIntoConfig(String key, String valueToAdd) {
		try {
			OutputStream outputFile = new FileOutputStream(src);
			pro.setProperty(key, valueToAdd);
			pro.store(outputFile, null); // in place of "null" - comment can be added
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println(e.getCause());
			e.printStackTrace();
		}
	}

}

