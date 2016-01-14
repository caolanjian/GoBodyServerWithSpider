package com.clj.resources;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Resources {
	
    public String APACHE_HOME_SERVER_IP;
	
	public String APACHE_IMG_SERVER_IP;
	
	public String MYSQL_CONNECTION_URL;
	
	public String IMG_URI_HEADER;
	
	public String APACHE_RESOURCE_PATH;
	
	public String TOMCAT_SERVER1_IP;
	
	public String TOMCAT_SERVER2_IP;
	
	public String TOMCAT_SERVER_APP_NAME;
	
	public String IMG_DOWNLOAD_PATH;
	
	public String IMG_SERVER_URL_HEADER;
	
	public Map<String, String> channelIndexMap;
	
	
	
	public String getAPACHE_HOME_SERVER_IP() {
		return APACHE_HOME_SERVER_IP;
	}

	public void setAPACHE_HOME_SERVER_IP(String aPACHE_HOME_SERVER_IP) {
		APACHE_HOME_SERVER_IP = aPACHE_HOME_SERVER_IP;
	}

	public String getAPACHE_IMG_SERVER_IP() {
		return APACHE_IMG_SERVER_IP;
	}

	public void setAPACHE_IMG_SERVER_IP(String aPACHE_IMG_SERVER_IP) {
		APACHE_IMG_SERVER_IP = aPACHE_IMG_SERVER_IP;
	}

	public String getMYSQL_CONNECTION_URL() {
		return MYSQL_CONNECTION_URL;
	}

	public void setMYSQL_CONNECTION_URL(String mYSQL_CONNECTION_URL) {
		MYSQL_CONNECTION_URL = mYSQL_CONNECTION_URL;
	}

	public String getIMG_URI_HEADER() {
		return IMG_URI_HEADER;
	}

	public void setIMG_URI_HEADER(String iMG_URI_HEADER) {
		IMG_URI_HEADER = iMG_URI_HEADER;
	}

	public String getAPACHE_RESOURCE_PATH() {
		return APACHE_RESOURCE_PATH;
	}

	public void setAPACHE_RESOURCE_PATH(String aPACHE_RESOURCE_PATH) {
		APACHE_RESOURCE_PATH = aPACHE_RESOURCE_PATH;
	}

	public String getTOMCAT_SERVER1_IP() {
		return TOMCAT_SERVER1_IP;
	}

	public void setTOMCAT_SERVER1_IP(String tOMCAT_SERVER1_IP) {
		TOMCAT_SERVER1_IP = tOMCAT_SERVER1_IP;
	}

	public String getTOMCAT_SERVER2_IP() {
		return TOMCAT_SERVER2_IP;
	}

	public void setTOMCAT_SERVER2_IP(String tOMCAT_SERVER2_IP) {
		TOMCAT_SERVER2_IP = tOMCAT_SERVER2_IP;
	}

	public String getTOMCAT_SERVER_APP_NAME() {
		return TOMCAT_SERVER_APP_NAME;
	}

	public void setTOMCAT_SERVER_APP_NAME(String tOMCAT_SERVER_APP_NAME) {
		TOMCAT_SERVER_APP_NAME = tOMCAT_SERVER_APP_NAME;
	}

	public String getIMG_DOWNLOAD_PATH() {
		return IMG_DOWNLOAD_PATH;
	}

	public void setIMG_DOWNLOAD_PATH(String iMG_DOWNLOAD_PATH) {
		IMG_DOWNLOAD_PATH = iMG_DOWNLOAD_PATH;
	}

	public String getIMG_SERVER_URL_HEADER() {
		return IMG_SERVER_URL_HEADER;
	}

	public void setIMG_SERVER_URL_HEADER(String iMG_SERVER_URL_HEADER) {
		IMG_SERVER_URL_HEADER = iMG_SERVER_URL_HEADER;
	}

	public Map<String, String> getChannelIndexMap() {
		return channelIndexMap;
	}

	public void setChannelIndexMap(Map<String, String> channelIndexMap) {
		this.channelIndexMap = channelIndexMap;
	}

	
}
