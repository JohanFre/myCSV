package com.example.demo;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

public class CsvMethods {

	static String CsvToJson() {

		ArrayList<String> orderDate = new ArrayList<String>();
		ArrayList<String> region = new ArrayList<String>();
		ArrayList<String> rep1 = new ArrayList<String>();
		ArrayList<String> rep2 = new ArrayList<String>();
		ArrayList<String> item = new ArrayList<String>();
		ArrayList<String> units = new ArrayList<String>();
		ArrayList<String> unitCost = new ArrayList<String>();
		ArrayList<String> total = new ArrayList<String>();

		String json = null;

		for (ArrayList<String> row : ReadCSV.getWholeSheet()) {

			orderDate.add(row.get(1));
			region.add(row.get(2));
			rep1.add(row.get(3));
			rep2.add(row.get(4));
			item.add(row.get(5));
			units.add(row.get(6));
			unitCost.add(row.get(7));
			total.add(row.get(8));

			String pattern = "{\"order\": {\"orderDate\": \"%s\", \"region\": \"%s\", \"rep1\": \"%s\", \"rep2\": \"%s\", \"item\": \"%s\", \"units\": \"%s\", \"unitCost\": \"%s\", \"total\": \"%s\"}}";
			json = String.format(pattern, orderDate, region, rep1, rep2, item, units, unitCost, total);

		}

		return json;

	}

	// Method for checking just 1 out the columns. I used dates in this one.

	static ArrayList<String> SpecificColumn(String id) {

		int i = Integer.parseInt(id);
		ArrayList<String> showCol = new ArrayList<String>();
		for (ArrayList<String> row : ReadCSV.getWholeSheet()) {
			// Changing
			showCol.add(row.get(i));
		}
		
		// Sorts row number.
		if(i == 0) {
			showCol.sort(Comparator.comparing(Double::parseDouble));
		}
		
		// Sorts orderDates.
		else if (i == 1) {
			ArrayList<String> dateString = new ArrayList<String>();
			dateString.add(showCol.get(1));
			
			Collections.sort(dateString, new Comparator<String>() {
		        DateFormat f = new SimpleDateFormat("MM/dd/yyyy");
		        @Override
		        public int compare(String date1, String date2) {
		            try {
		                return f.parse(date1).compareTo(f.parse(date2));
		            } catch (ParseException e) {
		                throw new IllegalArgumentException(e);
		            }
		        }
		    });
		}
		
		// Sorts units, unitCosts and total.
		else if(i > 5) {
			showCol.sort(Comparator.comparing(Double::parseDouble));
		}
		// Sorts region, Rep1, Rep2, item.
		else {
		showCol.sort(null);
		}
		return showCol;

	}

	// Method that checks that the total cost equals too amount * price.
	static ArrayList<String> checkCost() {
		ArrayList<String> ControlCheck = new ArrayList<String>();
		for (ArrayList<String> row : ReadCSV.getWholeSheet()) {
			String units = (row.get(6));
			String unitCost = (row.get(7));
			String total = (row.get(8));

				BigDecimal unit = new BigDecimal(units);
				BigDecimal unitcost = new BigDecimal(unitCost);
				BigDecimal totalCost = new BigDecimal(total);
				BigDecimal result = unit.multiply(unitcost);

				if (result.compareTo(totalCost) == 0) {
				} else {
					ControlCheck.add("Units: " + row.get(6) + " " + "UnitCost: " + row.get(7) + " " + "Total: " + row.get(8));
				}
		}
		return ControlCheck;
	}
	
}
