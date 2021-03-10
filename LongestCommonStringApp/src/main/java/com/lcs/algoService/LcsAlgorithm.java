package com.lcs.algoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 
 * @author craig
 * Contract: Assumes set of strings is valid 
 * Returns: Alphabetized 
 */
public class LcsAlgorithm implements ILcsAccess {

	public List<String> getLongestCommonString(List<String> setOfStrings) {
		
		ArrayList<String> resultSet = new ArrayList<String>();
		String baseString = setOfStrings.get(0);
		String longestString = "";
		int lengthOfCommonString = 0;
		for(int i = 0;i<baseString.length();i++)
		{
			for(int j=i+1;j<=baseString.length();j++)
			{
				String currentSubString = baseString.substring(i, j);
				boolean allMatch = true;
				for(String thisString: setOfStrings)
				{
					
					if(!thisString.contains(currentSubString))
					{
						allMatch = false;
					}
					
				}
				
				if(allMatch)
				{
					if(currentSubString.length() > lengthOfCommonString)
					{
						resultSet.clear();
						lengthOfCommonString = currentSubString.length();
						resultSet.add(currentSubString);
					}
					else if(currentSubString.length() == lengthOfCommonString)
					{
						resultSet.add(currentSubString);					}
				}
			}
		}
		Collections.sort(resultSet);
		return resultSet;
	}

}
