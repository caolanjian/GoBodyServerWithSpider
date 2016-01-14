package resources.webResource;

import java.util.HashMap;
import java.util.Map;

public class Resources {

	public static String SERVER_IP = "http://10.185.50.234:8080";
	public static String SERVER_NAME = "/GoBodyServerTest";
	
	public static String IMG_SERVER_IP = "http://10.185.50.234:81";
	public static String IMG_SERVER_NAME = "/GoBodyImgServer/img/";
	
	public static Map<String, String> channelIndexMap;
	static
	{
		channelIndexMap = new HashMap<String, String>();
		channelIndexMap.put("fitness", "����");
		channelIndexMap.put("running", "�ܲ�");
		channelIndexMap.put("yoga", "�٤");
		channelIndexMap.put("prisoner", "��ͽ");
		channelIndexMap.put("in", "Ȧ��");
		channelIndexMap.put("sport", "����");
		channelIndexMap.put("fight", "��");
		channelIndexMap.put("trainning", "ѵ��");
		channelIndexMap.put("loseweight", "����");
		channelIndexMap.put("extreme", "����");
	}
	
}
