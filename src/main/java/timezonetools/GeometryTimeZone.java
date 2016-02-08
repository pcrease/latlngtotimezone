package main.java.timezonetools;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;

public class GeometryTimeZone {

	private Geometry geom;
	private String timeZone;
	
	public GeometryTimeZone(Geometry geom,String timeZone){
		this.geom=geom;
		this.timeZone=timeZone;
	}

	public Geometry getGeometry() {
		return geom;
	}

	public String getTimeZone() {
		return timeZone;
	}

}
