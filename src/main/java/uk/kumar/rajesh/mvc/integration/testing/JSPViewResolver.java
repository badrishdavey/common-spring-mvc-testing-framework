package uk.kumar.rajesh.mvc.integration.testing;

import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

public class JSPViewResolver implements ViewResolver {

	public View resolveViewName(final String viewName, Locale locale) throws Exception {
        return new View() {
            public String getContentType() {
                return "charset=ISO-8859-1";
            }
            
            public void render(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception {
                response.getWriter().write("VIEW NAME IS: " + viewName + ", " +  convertMapModelToString(model));
            }
        };
    }
 
	private String convertMapModelToString(Map model)
	{
		String actualModelString = "";
		int index = 1;
		for(Object key: model.keySet())
		{
			String keyString = (String)key;
			String valueString = convertModelMapValueToString(model.get(keyString));
			//actualModelString = actualModelString + "KEYS IS: " + keyString + " and corresponding value is: " + valueString;
			actualModelString = actualModelString + index + ". KEY IS: " + keyString + "\n";
		}
		return actualModelString;
	}
	
	private String convertModelMapValueToString(Object modelMapValue)
	{
		if(modelMapValue instanceof List)
		{
			return convertAnyListToString((List)modelMapValue);
		}
		else if(modelMapValue instanceof Map)
		{
			return convertAnyGenralMapToString((Map)modelMapValue);
		}
		else
		{
			return modelMapValue.toString();
		}
		
	}
	
	private String convertAnyGenralMapToString(Map generalMap)
	{
		String mapString = "";
		for(Object key: generalMap.keySet())
		{
			if(mapString.equals(""))
			{
				mapString = key.toString() + " : " + generalMap.get(key);
			}
			else
			{
				mapString = mapString + " : " + key.toString() + " : " + generalMap.get(key);
			}
		}
		return mapString;
	}
	
	private String convertAnyListToString(List generalList)
	{
		String listObjectsString = "";
		for(Object listObject: generalList)
		{
			if(listObjectsString.equals(""))
			{
				listObjectsString = listObject.toString();
			}
			else
			{
				listObjectsString = listObjectsString + ", " + listObject.toString();
			}
		}
		return listObjectsString;
	}
	
	
	

}
