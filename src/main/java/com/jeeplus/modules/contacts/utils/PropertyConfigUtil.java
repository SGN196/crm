package com.jeeplus.modules.contacts.utils;

import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.*;
import java.net.URL;
import java.util.*;

public class PropertyConfigUtil {
	private static final String SEP = ",";

	/**
	 * Logger for this class
	 */

	private static final String SEP2 = ":";

	@SuppressWarnings("rawtypes")
	private static Hashtable propertyConfigUtils = new Hashtable();

	private URL url;

	public String getPropertiesPath() {
		return url.getPath();
	}

	private PropertyConfigUtil(String propertiesPath) {
		this.url = PropertyConfigUtil.class.getClassLoader().getResource(propertiesPath);

	}

	@SuppressWarnings("unchecked")
	public static PropertyConfigUtil getInstance(String propertiesPath) {
		PropertyConfigUtil configUtil = (PropertyConfigUtil) propertyConfigUtils.get(propertiesPath);
		if (configUtil == null) {
			configUtil = new PropertyConfigUtil(propertiesPath);
			propertyConfigUtils.put(propertiesPath, configUtil);
		}
		return configUtil;
	}

	public int getIntValue(String key) {
		return Integer.parseInt(getValue(key));
	}

	public long getLongValue(String key) {
		return Long.parseLong(getValue(key));
	}


	public synchronized String getValue(String key) {
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			properties.load(inputStream);
			check(key, properties);
			String value = properties.getProperty(key);
			return value;
		} catch (FileNotFoundException e) {
			throw new RuntimeException("getValue(String)resourse:" + this.url, e);
		} catch (IOException e) {
			throw new RuntimeException("getValue(String)", e);
		} finally {
			try {
				if (inputStream == null) {
				} else {
					inputStream.close();
				}
			} catch (IOException e) {
			}
		}
	}

	/**
	      *
	       * @Title: printAllProperty
	 		* @Description: 输出所有配置信息
	 		* @return void
	 	     * @throws
	 */
	public  List<String> printAllProperty() {
		List<String> list=new ArrayList<String>();
		Properties properties = new Properties();
		InputStream inputStream = null;
		try {
			inputStream = url.openStream();
			properties.load(inputStream);
			//String keysp[] =new String[15];
			//Enumeration en = properties.propertyNames();
			//properties.keySet();
			// 通过资源包拿到所有的key
			//System.out.println(properties.keySet());
//			while (en.hasMoreElements())
//				{
//					//String key =en.nextElement().toString();
//					keysp[i]=key;
//					i++;
//					System.out.println(key);
//				}
			Iterator<String> it=properties.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				list.add(key);
				System.out.println(key);
				System.out.println(key+":"+properties.getProperty(key));
			}
			inputStream.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	protected boolean check(String key, Properties properties) {
		if (properties.containsKey(key)) {
			return true;
		} else {
			throw new RuntimeException("the property file[" + this.url + "] do not have the key:" + key);
		}
	}

	public synchronized void setValue(String key, String value) {
		Properties properties = new Properties();
		OutputStream os = null;
		InputStream resourceAsStream = null;
		try {
			resourceAsStream = this.url.openStream();
			properties.load(resourceAsStream);
			properties.put(key, value);
			String fileAbPath = url.getFile();
			os = new FileOutputStream(new File(fileAbPath));
			properties.store(os, this.url.getFile());
		} catch (IOException e) {
			throw new RuntimeException("setValue(String key, String value, String fileAbPath)resourse:" + this.url, e);
		} finally {
			try {
				resourceAsStream.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean getBoolean(String key) {
		return new Boolean(this.getValue(key)).booleanValue();
	}

	public String[] getArray(String key) {
		return this.getValue(key).split(SEP);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map getMap(String key) {
		String[] strings = this.getArray(key);
		HashMap hashMap = new HashMap();
		for (int i = 0; i < strings.length; i++) {
			String[] att_value = strings[i].split(SEP2);
			if (att_value.length != 2) {
				throw new IllegalArgumentException("\"" + strings[i] + "\"config error!");
			} else {
				hashMap.put(att_value[0], att_value[1]);
			}
		}
		return hashMap;
	}
}
