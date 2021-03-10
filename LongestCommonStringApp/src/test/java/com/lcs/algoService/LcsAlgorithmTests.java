package com.lcs.algoService;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LcsAlgorithmTests {

	@Test
	public void GivenValidSetWithOneCommonString_WhenLongestCommonStringFound_ReturnAsSet() {
		
		LcsAlgorithm lcs = new LcsAlgorithm();
		ArrayList<String> expectedInput = new ArrayList<String>();
		expectedInput.add("comcast");
		expectedInput.add("comcastic");
		expectedInput.add("broadcaster");
		
		String expectedResult = "cast";
		
		List<String> actualResult = lcs.getLongestCommonString(expectedInput);
		assertEquals(expectedResult,actualResult.get(0));
	}
	
	@Test
	public void GivenValidSetWitMultipleCommonString_WhenLongestCommonStringFound_ReturnAsSetAlphabetically() {
		
		LcsAlgorithm lcs = new LcsAlgorithm();
		ArrayList<String> expectedInput = new ArrayList<String>();
		expectedInput.add("comcastblue");
		expectedInput.add("combluecastic");
		expectedInput.add("bluebroadcaster");
		
		ArrayList<String> expectedResult = new ArrayList<String>();
		expectedResult.add("blue");
		expectedResult.add("cast");
	
		
		List<String> actualResult = lcs.getLongestCommonString(expectedInput);
		assertEquals(expectedResult.get(0),actualResult.get(0));
		assertEquals(expectedResult.get(1),actualResult.get(1));
		
	}

}
