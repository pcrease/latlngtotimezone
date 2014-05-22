package com.timezone_analysis.timezonetools;

import com.vividsolutions.jts.geom.MultiPolygon;

public class GeometryTimeOffset {

	private MultiPolygon multiPolygon;
	private double timeCorrection;
	
	public GeometryTimeOffset(MultiPolygon multiPolygon,double timeCorrection){
		this.multiPolygon=multiPolygon;
		this.timeCorrection=timeCorrection;
	}

	public MultiPolygon getMultiPolygon() {
		return multiPolygon;
	}

	public double getTimeCorrection() {
		return timeCorrection;
	}

}
