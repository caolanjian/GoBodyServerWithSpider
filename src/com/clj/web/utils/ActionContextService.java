/*
 * 2015-04-16: Create By Cao Lanjian
 * This Class provide some service for ActionContext,  ActionContext is ThreadLocal
 */

package com.clj.web.utils;

import java.util.Map;
import java.util.Set;

import com.opensymphony.xwork2.ActionContext;

public class ActionContextService {

	private ActionContext context;
	
	public ActionContextService(ActionContext context)
	{
		this.context = context;
	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * Get String Parameter from ActionContext
	 */
	public String getStringParaFormContext(String paraName)
	{
		Map<?, ?> parameters = (Map<?, ?>)context.getParameters();
		Set<String> keys = (Set<String>) parameters.keySet();
		for(String key : keys)
		{
			System.out.println(key);
		}
		
		String returnedValue = "";
		try
		{
			String[] value = (String[])parameters.get(paraName);
			for(int i=0;i<value.length;i++)
			{
				returnedValue = returnedValue + value[i];
			}
			return returnedValue;
		}
		catch(Exception e)
		{
			//e.printStackTrace();
			return null;
		}

	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * Get Object Parameter from ActionContext
	 */
	public Object getParaFromContext(String paraName)
	{
		Map<?, ?> parameters = (Map<?, ?>)context.getParameters();
		
		return parameters.get(paraName);
	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * put a parameter into ActionContext's paramteres map
	 */
	public void setParaToParameters(String key, Object obj)
	{
		Map<String, Object> parameters = (Map)context.getParameters();
		parameters.put(key, obj);
	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * put a parameter into request
	 */
	public void setParaToRequest(String key, Object obj)
	{
		
		
	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * put a parameter into session
	 */
	public void setParaToSession(String key, Object obj)
	{
		context.getSession().put(key, obj);
	}
	
	/*
	 * 2015-04-16: Create By Cao Lanjian
	 * get a Object from session
	 */
	public Object getObjFromSession(String key)
	{
		return context.getSession().get(key);
	}
}
