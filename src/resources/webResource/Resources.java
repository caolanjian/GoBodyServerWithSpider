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
		channelIndexMap.put("fitness", "健身");
		channelIndexMap.put("running", "跑步");
		channelIndexMap.put("yoga", "瑜伽");
		channelIndexMap.put("prisoner", "囚徒");
		channelIndexMap.put("in", "圈内");
		channelIndexMap.put("sport", "体育");
		channelIndexMap.put("fight", "格斗");
		channelIndexMap.put("trainning", "训练");
		channelIndexMap.put("loseweight", "减肥");
		channelIndexMap.put("extreme", "极限");
	}
	
}
