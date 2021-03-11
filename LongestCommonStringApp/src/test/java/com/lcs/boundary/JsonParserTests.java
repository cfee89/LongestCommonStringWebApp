package com.lcs.boundary;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;

import com.lcs.boundary.JsonParser;

public class JsonParserTests {

	@Test
	public void test() {
		JsonParser parser = new JsonParser();
		
		HttpServletRequest mockReq = mock(HttpServletRequest.class);
		
		parser.convertRequestBodyToStringList(mockReq);
	}

}
