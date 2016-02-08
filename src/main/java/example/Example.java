package main.java.example;

import main.java.timezonetools.LatLngCoordinate;
import main.java.timezonetools.TimeZoneGeoTool;

import java.io.*;

public class Example {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		//example shows how to get the TimeZone string (e.g. Europe/Paris), and offset in hours for a lat,lng coordinate
	    System.out.println("running example....");

	    TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();
		
		timeZoneGeoTool.loadTimeOffsetGeoJsonData();
        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(new LatLngCoordinate(48.8567,2.3508 ));//Paris
        timeZoneGeoTool.unLoadTimeOffsetData();
		
		System.out.println("Offset in hours name of timezone found =  "+offsetHours);

	}

}
