package main.java.timezonetools;

import com.vividsolutions.jts.geom.*;
import com.vividsolutions.jts.index.strtree.STRtree;

import java.util.ArrayList;
import java.util.List;

public class GeometryTimeOffsetList extends ArrayList<GeometryTimeOffset> {

    //searchable Rtree index of timezone offsets
    
	STRtree rtree = new STRtree();
	GeometryFactory fact = new GeometryFactory();

	public void addToIndex(GeometryTimeOffset geometryTimeOffset) {
		rtree.insert(geometryTimeOffset.getMultiPolygon().getEnvelopeInternal(),
		        geometryTimeOffset);
	}

	public void buildIndex() {
		rtree.build();
	}

	public double searchIndex(Coordinate coordinate) {

		Point p = fact.createPoint(coordinate);
		List hitList = rtree.query(p.getEnvelopeInternal());

		//only one result returned, return it!
		if (hitList.size() == 1) {
		    GeometryTimeOffset gtz = (GeometryTimeOffset) hitList.get(0);
			return gtz.getTimeCorrection();
		}

		//coord lies within two bounding boxes,
		//do a pointinpolygon to find which is most correct
		if (hitList.size() > 1) {
			for (Object obj : hitList) {
			    GeometryTimeOffset geometryTimeOffset = (GeometryTimeOffset) obj;
				MultiPolygon mp = geometryTimeOffset.getMultiPolygon();

				for (int o = 0; o < mp.getNumGeometries(); o++) {
					Polygon poly = (Polygon) mp.getGeometryN(o);
					if (p.within(poly)) {
						return geometryTimeOffset.getTimeCorrection();
					}
				}

			}
		} 
		
		//found nothing so returns nonsense
		return -999.0;
	}
}
