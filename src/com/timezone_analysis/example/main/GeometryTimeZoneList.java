package com.timezone_analysis.example.main;

import java.util.ArrayList;
import java.util.List;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.index.strtree.STRtree;

public class GeometryTimeZoneList extends ArrayList<GeometryTimeZone> {

	STRtree rtree = new STRtree();
	GeometryFactory fact = new GeometryFactory();

	public void addToIndex(GeometryTimeZone geometryTimeZone) {
		rtree.insert(geometryTimeZone.getMultiPolygon().getEnvelopeInternal(),
				geometryTimeZone);
	}

	public void buildIndex() {
		rtree.build();
	}

	public String searchIndex(Coordinate coordinate) {

		Point p = fact.createPoint(coordinate);
		List hitList = rtree.query(p.getEnvelopeInternal());
		System.out.println(hitList.size());

		//only one result returned, return it!
		if (hitList.size() == 1) {
			GeometryTimeZone gtz = (GeometryTimeZone) hitList.get(0);
			return gtz.getTimeZone();
		}

		//coord lies within two bounding boxes,
		//do a pointinpolygon to find which is most correct
		if (hitList.size() > 1) {
			for (Object obj : hitList) {
				GeometryTimeZone geometryTimeZone = (GeometryTimeZone) obj;
				MultiPolygon mp = geometryTimeZone.getMultiPolygon();

				for (int o = 0; o < mp.getNumGeometries(); o++) {
					Polygon poly = (Polygon) mp.getGeometryN(o);
					if (p.within(poly)) {
						return geometryTimeZone.getTimeZone();
					}
				}

			}
		} 
		
		//found nothing so returns null
		return null;
	}
}
