package com.lcs.boundary;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class JsonParser implements IJsonParser{

	public List<String> convertRequestBodyToStringList(HttpServletRequest req)
	{
		
		ArrayList<String> setOfStrings = new ArrayList<String>();
		
		StringBuffer jb = new StringBuffer();
		  String line = null;
		  try {
		    BufferedReader reader = req.getReader();
		    while ((line = reader.readLine()) != null)
		      jb.append(line);
		  } catch (Exception e) { /*report an error*/ }
		  
		  System.out.println(jb.toString());
		  
		  
//		  Gson gson = new Gson();
//		  Type collectionType = new TypeToken<ArrayList<String>>(){}.getType();
//		  Collection<String> jsonStrings = gson.fromJson(jb.toString(), collectionType);
//		  for(String thisString: jsonStrings)
//		  {
//			  setOfStrings.add(thisString);
//		  }

		    JSONObject jsonObject =  new JSONObject(jb.toString());
		    JSONArray jsonArray = jsonObject.getJSONArray("setOfStrings");
		    for(Object thisJSONObject : jsonArray)
		    {
		    	if(thisJSONObject instanceof JSONObject)
		    	{
		    		String thisString =  ((JSONObject) thisJSONObject).getString("value");
		    		setOfStrings.add(thisString);
		    	}
		    	else
		    	{
		    		System.out.println("Not an array of value pairs");
		    		throw new JSONException("JSONObject not a string");
		    	}
		    	
		    }
		  
		  return setOfStrings;
	}

	public String convertStringsToJson(List<String> result) {
		String jsonString ="{\"lcs\":[";
		for(String thisString: result)
		{
			jsonString = jsonString+"{\"value\":\""+thisString+"\"},";
		}
		jsonString = removeExtraCommaFromEnd(jsonString);
		jsonString = jsonString + "]}";
		System.out.println(jsonString);
		return jsonString;
	}

	private String removeExtraCommaFromEnd(String jsonString) {
		return jsonString.substring(0, jsonString.length()-1);
	}

}
