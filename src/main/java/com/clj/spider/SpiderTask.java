package com.clj.spider;

import java.util.TimerTask;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import com.clj.web.utils.FileLogHelper;


@Component
public class SpiderTask extends TimerTask{

	@Resource SpiderHolder spiderHolder;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		//FileLogHelper.writeLog("#############Spider Restart##############");

		try {
			spiderHolder.stopSpider();
			Thread.sleep(5000);
			spiderHolder.runSpider();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
