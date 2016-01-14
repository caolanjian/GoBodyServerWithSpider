package com.clj.web.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class FileLogHelper  {

	public static String logPath = "C:/Apache/htdocs/GoBodyImgServer/log.txt";
	
	public static void writeLog(String log)
	{
		File logFile = new File(logPath);
		if(!logFile.exists())
		{
			try 
			{
				logFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		BufferedWriter bw = null;
		
		try{
			bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFile, true)));
			bw.write(log);
			bw.newLine();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
