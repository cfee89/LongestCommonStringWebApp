package com.lcs.boundary;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IJsonParser {

	public List<String> convertRequestBodyToStringList(HttpServletRequest req);

	public String convertStringsToJson(List<String> result);
}
