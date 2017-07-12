package com.cicdproject.BankPortal.utities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.core.io.ClassPathResource;

public class HTMLUtils {
	public static String renderErrorPage(String errorMessage) {
		StringBuilder sb = new StringBuilder();
		
		sb.append("<p>");
		sb.append(errorMessage);
		sb.append("</p>");
		
		return sb.toString();
	}
	
	public static String getHtmlFromFile(String filename) {
		StringBuilder sb = new StringBuilder();
		
		BufferedReader lineReader = null;
		ClassPathResource cpr = new ClassPathResource(filename);
		
		try {
			lineReader = new BufferedReader(new InputStreamReader(cpr.getInputStream()));
			
			String line;
			while ((line = lineReader.readLine()) != null) {
				sb.append(line);
				sb.append("\n");
			}
			
			if (lineReader != null) {
				lineReader.close();
			}
		} catch (FileNotFoundException e) {
			sb.append(renderErrorPage("File not found: " + filename));
		} catch (IOException e) {
			sb.append(renderErrorPage("File failed to read: " + filename));
		}
		
		return sb.toString();
	}
}
