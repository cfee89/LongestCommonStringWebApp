package com.lcs.boundary;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import com.lcs.algoService.ILcsAccess;

public class LcsJsonWebBoundaryTests {
	
	private LcsJsonWebBoundary servlet;
	IJsonParser mock_jsonParser;
	ILcsAccess mock_lcsAccess;
	
	@Before
	public void setup()
	{
		mock_jsonParser = mock(IJsonParser.class);
		mock_lcsAccess = mock(ILcsAccess.class);
		servlet = new LcsJsonWebBoundary(mock_jsonParser,mock_lcsAccess);
	}
	
	@Test
	public void GivenNoBodyInPOSTRequest_WhenPOSTIsReceived_SendErrorResponse() throws ServletException, IOException
	{
		
		//preconditions
		
		HttpServletRequest expectedRequest = mock(HttpServletRequest.class);
		HttpServletResponse expectedResponse = mock(HttpServletResponse.class);
		
		servlet.doPost(expectedRequest, expectedResponse);
		verify(expectedResponse).sendError(400, "MISSING BODY");
	
	}
	
	@Test
	public void GivenJsonMisformattedPOSTRequest_WhenPOSTIsReceived_SendErrorResponse() throws ServletException, IOException
	{
		
		//preconditions


		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse expectedResponse = mock(HttpServletResponse.class);
		
		ArrayList<String> expectedSetOfStrings = new ArrayList<String>();
		expectedSetOfStrings.add("comcast");
		expectedSetOfStrings.add("comcastic");
		expectedSetOfStrings.add("broadcaster");
		
		when(mockRequest.getContentLength()).thenReturn(16);
		doThrow(new JSONException("INVALID JSON FORMAT")).when(mock_jsonParser).convertRequestBodyToStringList(mockRequest);
		
		servlet.doPost(mockRequest, expectedResponse);

		verify(expectedResponse).sendError(400, "JSON NOT FORMATTED CORRECTLY IN REQUEST");
	
	}
	
	@Test
	public void GivenDuplicateStringsPOSTRequest_WhenPOSTIsReceived_SendErrorResponse() throws ServletException, IOException
	{
		
		//preconditions


		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse expectedResponse = mock(HttpServletResponse.class);
		
		ArrayList<String> expectedSetOfStrings = new ArrayList<String>();
		expectedSetOfStrings.add("comcast");
		expectedSetOfStrings.add("comcast");
		expectedSetOfStrings.add("broadcaster");
		
		when(mockRequest.getContentLength()).thenReturn(16);
		when(mock_jsonParser.convertRequestBodyToStringList(mockRequest)).thenReturn(expectedSetOfStrings);
		
		servlet.doPost(mockRequest, expectedResponse);

		verify(expectedResponse).sendError(400, "STRINGS IN SET ARE NOT UNIQUE");
	
	}
	
	@Test
	public void GivenValidPOSTRequest_WhenPOSTIsReceived_getResultFromLCSService() throws ServletException, IOException
	{
		
		//preconditions


		HttpServletRequest mockRequest = mock(HttpServletRequest.class);
		HttpServletResponse mockResponse = mock(HttpServletResponse.class);
		
		ArrayList<String> expectedSetOfStrings = new ArrayList<String>();
		expectedSetOfStrings.add("comcast");
		expectedSetOfStrings.add("comcastic");
		expectedSetOfStrings.add("broadcaster");
		
		
		
		when(mockRequest.getContentLength()).thenReturn(16);
		when(mock_jsonParser.convertRequestBodyToStringList(mockRequest)).thenReturn(expectedSetOfStrings);
		
		ArrayList<String> expectedLongestStrings = new ArrayList<String>();
		expectedLongestStrings.add("cast");
		when(mock_lcsAccess.getLongestCommonString(expectedSetOfStrings)).thenReturn(expectedLongestStrings);
		
		String expectedJsonString = "{lcs: [ {result: 'cast'} ]}";
		when(mock_jsonParser.convertStringsToJson(expectedLongestStrings)).thenReturn(expectedJsonString);
		
		PrintWriter mockWriter = mock(PrintWriter.class);
		when(mockResponse.getWriter()).thenReturn(mockWriter);
		
		servlet.doPost(mockRequest, mockResponse);
		
		verify(mockResponse).setContentType("application/json");
		verify(mockWriter).print(expectedJsonString);
		verify(mockWriter).flush();
		
		

		
	}


}
