package com.timezone_analysis.example.main;

import com.vividsolutions.jts.geom.MultiPolygon;

public class GeometryTimeZone {

	private MultiPolygon multiPolygon;
	private String timeZone;
	
	public GeometryTimeZone(MultiPolygon multiPolygon,String timeZone){
		this.multiPolygon=multiPolygon;
		this.timeZone=timeZone;
	}

	public MultiPolygon getMultiPolygon() {
		return multiPolygon;
	}

	public String getTimeZone() {
		return timeZone;
	}

}
