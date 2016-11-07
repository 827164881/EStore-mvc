package com.geng.factory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BasicFactory {
	private BasicFactory(){}
	private static BasicFactory factory=new BasicFactory();
	
	public static BasicFactory getFactory(){
		return factory;
	}
	
	private static Properties prop=null;
	static{
		prop=new Properties();
		try {
			prop.load(new FileReader(BasicFactory.class.getClassLoader().getResource("config.properties").getPath()));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public <T> T getInstence(Class<T> clazz){
		try {
		String className=clazz.getSimpleName();
		String implName=prop.getProperty(className);
		return (T) Class.forName(implName).newInstance();
		} catch(Exception e){e.printStackTrace();}
		return null;
		
		
	}
}
