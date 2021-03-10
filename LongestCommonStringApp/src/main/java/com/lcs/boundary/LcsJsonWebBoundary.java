package com.lcs.boundary;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;

import com.lcs.algoService.ILcsAccess;
import com.lcs.factories.WebBoundaryFactory;

@WebServlet(
        name = "longestCommonString",
        urlPatterns = "/LCS"
)

public class LcsJsonWebBoundary extends HttpServlet
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IJsonParser jsonParser;
	private ILcsAccess lcsAccess;

	public LcsJsonWebBoundary() {
		WebBoundaryFactory factory = new WebBoundaryFactory();
		this.jsonParser = factory.getJsonParser();
		this.lcsAccess = factory.getAlgorithmService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
	{
		
		if(req.getContentLength()<1)
		{
			resp.sendError(400,"MISSING BODY");
			return;
		}
		List<String> setOfStrings;
		try {
			setOfStrings = jsonParser.convertRequestBodyToStringList(req);
		}
		catch (JSONException e) 
		{
			resp.sendError(400, "JSON NOT FORMATTED CORRECTLY IN REQUEST"); 
			return;
		}
		
		HashSet<String> set = new HashSet<String>(setOfStrings);
		if(setOfStrings.size() > set.size())
		{
			resp.sendError(400, "STRINGS IN SET ARE NOT UNIQUE");
			return;
		}
		
		List<String> result = lcsAccess.getLongestCommonString(setOfStrings);
		String resultJson = jsonParser.convertStringsToJson(result);
		
		resp.setContentType("application/json");
		PrintWriter writer = resp.getWriter();
		writer.print(resultJson);
		writer.flush();
		
		
	}

}