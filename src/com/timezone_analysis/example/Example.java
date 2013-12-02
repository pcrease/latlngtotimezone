package com.timezone_analysis.example;

import java.io.IOException;

import com.timezone_analysis.timezonetools.TimeZoneGeoTool;
import com.vividsolutions.jts.geom.Coordinate;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidShapeFileException 
	 */
	public static void main(String[] args) throws Exception {
		TimeZoneGeoTool timeZoneGeoTool = TimeZoneGeoTool.getTimeZoneGeoToolInstance();
		String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new Coordinate(2.3508, 48.8567));//Paris
		System.out.println("timezone found = "+tz);
	}

}
