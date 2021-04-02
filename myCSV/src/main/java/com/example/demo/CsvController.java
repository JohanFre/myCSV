package com.example.demo;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CsvController {

	// Assignment 1 - Converts CSV to JSON without pattern.
	
	@RequestMapping(value = "/showCSV", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<ArrayList<String>> showCSV(HttpServletResponse response) throws IOException {

		return ReadCSV.getWholeSheet();

	}
	
	// Assignment 1.1 - Converts the CSV-file into JSON. 
	
	@RequestMapping(value = "/CsvToJson", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public String showCSVListJSON() {

		return CsvMethods.CsvToJson();

	}
	
	// Assignment 2 - Converts to JSON and prints out specific column. Id = 0-8 

	@RequestMapping(value = "/showColumn/{column}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ArrayList<String> showCSVList(@PathVariable("column") String id) {
		
		return CsvMethods.SpecificColumn(id);
		
	}
	
	// Assignment 3 - Check for mistakes in total cost. 
	
	@RequestMapping(value = "/checkCost", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<String> mathCheck() {
		return CsvMethods.checkCost();
	}

}