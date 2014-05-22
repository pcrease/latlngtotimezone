package com.timezone_analysis.timezonetools;

import java.io.File;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LinearRing;
import com.vividsolutions.jts.geom.MultiPolygon;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.index.strtree.STRtree;

import diewald_shapeFile.files.dbf.DBF_Field;
import diewald_shapeFile.files.shp.shapeTypes.ShpPolygon;
import diewald_shapeFile.files.shp.shapeTypes.ShpShape;
import diewald_shapeFile.shapeFile.ShapeFile;

public class TimeZoneGeoTool {

    private static TimeZoneGeoTool instance = null;

    public TimeZoneGeoTool() {

    }

    
    private static GeometryTimeZoneList geometryTimeZoneList = new GeometryTimeZoneList();
    private static GeometryTimeOffsetList geometryTimeOffsetList = new GeometryTimeOffsetList();

    public String getTimeZoneFromCoordinate(Coordinate coordinate) {
        return geometryTimeZoneList.searchIndex(coordinate);
    }
    
    public double getOffsetFromCoordinate(Coordinate coordinate) {
        return geometryTimeOffsetList.searchIndex(coordinate);
    }

    public void loadTimeOffsetData() throws Exception {

        // FileInputStream is = new FileInputStream(
        // "."+File.separator+"tz_data"+File.separator+"tz_gen_0.01.shp");

        GeometryFactory fact = new GeometryFactory();

        ShapeFile shapefile = new ShapeFile("." + File.separator + "tz_data", "timezone").READ();

        ShpShape.Type shape_type = shapefile.getSHP_shapeType();
        // System.out.println("\nshape_type = " + shape_type);

        int number_of_shapes = shapefile.getSHP_shapeCount();
        int number_of_fields = shapefile.getDBF_fieldCount();

        for (int i = 0; i < number_of_shapes; i++) {
            MultiPolygon jtsPolygon;

            ShpPolygon shape = shapefile.getSHP_shape(i);
            String[] shape_info = shapefile.getDBF_record(i);

            ShpShape.Type type = shape.getShapeType();
            int number_of_vertices = shape.getNumberOfPoints();
            int number_of_polygons = shape.getNumberOfParts();
            int record_number = shape.getRecordNumber();

            double[][][] listOfPolygons = shape.getPointsAs3DArray();

            Polygon[] polygons = null;
            for (int j = 0; j < listOfPolygons.length; j++) {
                if (j == 0) {
                    polygons = new Polygon[listOfPolygons.length];
                }
                Coordinate[] coordinates = null;

                for (int h = 0; h < listOfPolygons[j].length; h++) {
                    // Coordinate[] coordinates=null;
                    if (h == 0) {
                        coordinates = new Coordinate[listOfPolygons[j].length];
                    }
                    // System.out.println(listOfPolygons[j][h][0]+" "+listOfPolygons[j][h][1]);

                    coordinates[h] = (new Coordinate(listOfPolygons[j][h][0], listOfPolygons[j][h][1]));

                }
                LinearRing linear = fact.createLinearRing(coordinates);
                // System.out.println(fact.createPolygon(linear, null));
                polygons[j] = fact.createPolygon(linear, null);
                // System.out.println(polygons[i]);

            }

            jtsPolygon = fact.createMultiPolygon(polygons);

            // System.out.printf("\nSHAPE[%2d] - %s\n", i, type);
            // System.out
            // .printf("  (shape-info) record_number = %3d; vertices = %6d; polygons = %2d\n",
            // record_number, number_of_vertices,
            // number_of_polygons);

            double offset = Double.parseDouble(shape_info[0].trim());

            GeometryTimeOffset gto = new GeometryTimeOffset(jtsPolygon, offset);

            geometryTimeOffsetList.addToIndex(gto);

        }
        geometryTimeOffsetList.buildIndex();

    }

    public void unLoadTimeOffsetData() {
        geometryTimeOffsetList = null;
    }

    public void loadAdminBoundaryData() throws Exception {

        // FileInputStream is = new FileInputStream(
        // "."+File.separator+"tz_data"+File.separator+"tz_gen_0.01.shp");

        GeometryFactory fact = new GeometryFactory();

        ShapeFile shapefile = new ShapeFile("." + File.separator + "tz_data", "tz").READ();

        ShpShape.Type shape_type = shapefile.getSHP_shapeType();
        // System.out.println("\nshape_type = " + shape_type);

        int number_of_shapes = shapefile.getSHP_shapeCount();
        int number_of_fields = shapefile.getDBF_fieldCount();

        for (int i = 0; i < number_of_shapes; i++) {
            MultiPolygon jtsPolygon;

            ShpPolygon shape = shapefile.getSHP_shape(i);
            String[] shape_info = shapefile.getDBF_record(i);

            ShpShape.Type type = shape.getShapeType();
            int number_of_vertices = shape.getNumberOfPoints();
            int number_of_polygons = shape.getNumberOfParts();
            int record_number = shape.getRecordNumber();

            double[][][] listOfPolygons = shape.getPointsAs3DArray();

            Polygon[] polygons = null;
            for (int j = 0; j < listOfPolygons.length; j++) {
                if (j == 0) {
                    polygons = new Polygon[listOfPolygons.length];
                }
                Coordinate[] coordinates = null;

                for (int h = 0; h < listOfPolygons[j].length; h++) {
                    // Coordinate[] coordinates=null;
                    if (h == 0) {
                        coordinates = new Coordinate[listOfPolygons[j].length];
                    }
                    // System.out.println(listOfPolygons[j][h][0]+" "+listOfPolygons[j][h][1]);

                    coordinates[h] = (new Coordinate(listOfPolygons[j][h][0], listOfPolygons[j][h][1]));

                }
                LinearRing linear = fact.createLinearRing(coordinates);
                // System.out.println(fact.createPolygon(linear, null));
                polygons[j] = fact.createPolygon(linear, null);
                // System.out.println(polygons[i]);

            }

            jtsPolygon = fact.createMultiPolygon(polygons);

            // System.out.printf("\nSHAPE[%2d] - %s\n", i, type);
            // System.out
            // .printf("  (shape-info) record_number = %3d; vertices = %6d; polygons = %2d\n",
            // record_number, number_of_vertices,
            // number_of_polygons);

            GeometryTimeZone gtz = new GeometryTimeZone(jtsPolygon, shape_info[0].trim());

            geometryTimeZoneList.addToIndex(gtz);

        }
        geometryTimeZoneList.buildIndex();

    }

    public void unLoadAdminBoundaryData() {
        geometryTimeZoneList = null;
    }
}
