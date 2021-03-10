package com.lcs.factories;

import com.lcs.algoService.ILcsAccess;
import com.lcs.algoService.LcsAlgorithm;
import com.lcs.boundary.IJsonParser;
import com.lcs.boundary.JsonParser;

public class WebBoundaryFactory {
	
	public WebBoundaryFactory()
	{
		
	}
	
	public ILcsAccess getAlgorithmService()
	{
		return new LcsAlgorithm();
	}
	
	public IJsonParser getJsonParser()
	{
		return new JsonParser();
	}

}
