package com.w2a.rough;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class TestProperties {
	
	public static void main(String[] args) throws IOException {
		System.out.println(System.getProperty("user.dir"));
		String projPath = System.getProperty("user.dir");
		
		Properties config = new Properties();
		Properties orProp= new Properties();
		
		FileInputStream fis = new FileInputStream(projPath + "\\src\\test\\resources\\properties\\Config.properties");
		config.load(fis);
		System.out.println(config.getProperty("browser"));
		
		fis = new FileInputStream(System.getProperty("user.dir") +"\\src\\test\\resources\\properties\\OR.properties");
		orProp.load(fis);
		System.out.println(orProp.getProperty("bmlBtn"));
		
	}
}
