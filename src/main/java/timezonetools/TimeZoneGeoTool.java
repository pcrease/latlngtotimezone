package main.java.timezonetools;



import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Polygon;
import org.wololo.geojson.Feature;
import org.wololo.geojson.FeatureCollection;
import org.wololo.geojson.GeoJSONFactory;
import org.wololo.jts2geojson.GeoJSONReader;

import java.io.InputStream;

public class TimeZoneGeoTool {

    private final static String ZONE_KEY = "ZONE";
    private final static String ID_KEY = "OID";


    private static TimeZoneGeoTool instance = null;

    public TimeZoneGeoTool() {

    }


    private static GeometryTimeOffsetList geometryTimeOffsetList = new GeometryTimeOffsetList();



    public double getOffsetFromCoordinate(LatLngCoordinate coordinate) {
        return geometryTimeOffsetList.searchIndex(coordinate.getCoordinate());
    }

    public void loadTimeOffsetGeoJsonData() throws Exception {

            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("timezone_data/timezone.geojson");
            String geoJsonString = convertStreamToString(is);

            FeatureCollection geoJSON=(FeatureCollection) GeoJSONFactory.create(geoJsonString);

            for (Feature f:geoJSON.getFeatures()){
                Double offsetHours = (Double)f.getProperties().get(ZONE_KEY);
                GeoJSONReader reader = new GeoJSONReader();
                Geometry g = reader.read(f.getGeometry());
                GeometryTimeOffset gto = new GeometryTimeOffset(g, offsetHours);
                geometryTimeOffsetList.addToIndex(gto);
            }

        geometryTimeOffsetList.buildIndex();



    }

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }



    public void unLoadTimeOffsetData() {
        geometryTimeOffsetList = null;
    }


}
