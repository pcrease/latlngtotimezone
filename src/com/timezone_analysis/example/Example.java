package com.timezone_analysis.example;

import java.io.IOException;

import com.timezone_analysis.example.main.TimeZoneGeoTool;
import com.vividsolutions.jts.geom.Coordinate;

public class Example {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws InvalidShapeFileException 
	 */
	public static void main(String[] args) throws Exception {
		TimeZoneGeoTool timeZoneGeoTool = TimeZoneGeoTool.getTimeZoneGeoToolInstance();
		String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new Coordinate(2.3508, 48.8567));
		TimeZoneGeoTool timeZoneGeoTooal = TimeZoneGeoTool.getTimeZoneGeoToolInstance();
		String tza=timeZoneGeoTooal.getTimeZoneFromCoordinate(new Coordinate(2.6508, 48.8567));
	System.out.println(tz+" "+tza);
	}

}
