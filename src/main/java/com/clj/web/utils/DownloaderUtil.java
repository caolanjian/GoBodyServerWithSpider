package com.clj.web.utils;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Set;

public class DownloaderUtil {

	    //1: �ļ��Ѵ���
		//0: ���سɹ�
		//-1: ����ʧ��
		public static int downloadFile(String httpAddress, String filePath)
		{
			int resultValue = 0;
			InputStream is = null;
			OutputStream os = null;
			
			try
			{
				File file = new File(filePath);
				if(file.exists())
				{
					resultValue = 1;
				}
				else
				{
					//file.createNewFile();
					URL url = new URL(httpAddress);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setConnectTimeout(3000);
					conn.setRequestMethod("GET");
					if(conn.getResponseCode() == 200)
					{
						is = conn.getInputStream();
						os = new FileOutputStream(file);
						byte[] buffer = new byte[4096];
						int len = 0;
						while((len = is.read(buffer)) != -1)
						{
							os.write(buffer, 0, len);
						}
						os.flush();
					}
					else
					{
						resultValue = -1;
						System.out.println("Response Code = " + conn.getResponseCode());
					}
					
				}
				
			}
			catch(Exception e)
			{
				resultValue = -1;
				e.printStackTrace();
			}
			finally
			{
				if(os != null)
				{
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(is != null)
				{
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return resultValue;
		}
		
		public static int downloadFileByPost(String httpAddress, String filePath, HashMap<String, String> parameters)
		{
			int resultValue = 0;
			InputStream is = null;
			OutputStream os = null;
			
			try
			{
				File file = new File(filePath);
				if(file.exists())
				{
					resultValue = 1;
				}
				else
				{
					URL url = new URL(httpAddress);
					HttpURLConnection conn = (HttpURLConnection)url.openConnection();
					conn.setConnectTimeout(3000);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");
					conn.setUseCaches(false);
					conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
					DataOutputStream out = new DataOutputStream(conn.getOutputStream());
					Set<String> keySet = parameters.keySet();
					StringBuffer content = new StringBuffer("");
					for(String key : keySet)
					{
						String value = parameters.get(key);
						content.append(key).append("=").append(value);
						content.append("&");
					}
					content.delete(content.length()-1, content.length());
					out.writeBytes(content.toString());
					out.flush();
					out.close();
					if(conn.getResponseCode() == 200)
					{
						is = conn.getInputStream();
						os = new FileOutputStream(file);
						byte[] buffer = new byte[4096];
						int len = 0;
						while((len = is.read(buffer)) != -1)
						{
							os.write(buffer, 0, len);
						}
						os.flush();
						conn.disconnect();
					}
					else
					{
						resultValue = -1;
						System.out.println("Response Code = " + conn.getResponseCode());
					}
					
				}
				
			}
			catch(Exception e)
			{
				resultValue = -1;
				e.printStackTrace();
			}
			finally
			{
				if(os != null)
				{
					try {
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(is != null)
				{
					try {
						is.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return resultValue;
		}
}

