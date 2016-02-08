package main.java.timezonetools;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;

public class GeometryTimeOffset {

	private Geometry geom;
	private double timeCorrection;
	
	public GeometryTimeOffset(Geometry geom, double timeCorrection){
		this.geom=geom;
		this.timeCorrection=timeCorrection;
	}

	public Geometry getGeometry() {
		return geom;
	}

	public double getTimeCorrection() {
		return timeCorrection;
	}

}
