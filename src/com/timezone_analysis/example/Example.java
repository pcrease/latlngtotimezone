package com.timezone_analysis.example;

import java.io.IOException;

import com.timezone_analysis.timezonetools.LatLngCoordinate;
import com.timezone_analysis.timezonetools.TimeZoneGeoTool;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidShapeFileException 
	 */
	public static void main(String[] args) throws Exception {
		//example shows how to get the TimeZone string (e.g. Europe/Paris), and offset in hours for a lat,lng coordinate
	    TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();
		timeZoneGeoTool.loadAdminBoundaryData();
		String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new LatLngCoordinate(48.8567,2.3508));//Paris
		timeZoneGeoTool.unLoadAdminBoundaryData();
		
		timeZoneGeoTool.loadTimeOffsetData();
        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(new LatLngCoordinate(48.8567,2.3508 ));//Paris
        timeZoneGeoTool.unLoadTimeOffsetData();
		
		System.out.println("Admin name of timezone found = "+tz +". Offset in hours name of timezone found =  "+offsetHours);
	}

}
