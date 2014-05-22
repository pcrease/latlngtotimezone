latlngtotimezone
================

a java library that allows a latitude, longitude coordinates to be translated to a java timezone string or offset in hours. 

The polygon data for this library comes from http://efele.net/maps/tz/world/, although the polygons have been simplified to remove nodes and shrink the .jar size (see NOTE below).
The API consists of a single object class that can be used to search for the time zone string (Europe/London) or the offset in hours (+3.5) using a pair of WGS84 longitude, latitude coordinates. NOTE: coordinate order is XY (Longitude, Latitude)

Example :

```//example shows how to get the TimeZone string (e.g. Europe/Paris), and offset in hours for a lat,lng coordinate
	    TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();
		timeZoneGeoTool.loadAdminBoundaryData();
		String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new Coordinate(2.3508, 48.8567));//Paris
		timeZoneGeoTool.unLoadAdminBoundaryData();
		
		timeZoneGeoTool.loadTimeOffsetData();
        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(new Coordinate(2.3508, 48.8567));//Paris
        timeZoneGeoTool.unLoadTimeOffsetData();
		
		System.out.println("Admin name of timezone found = "+tz +". Offset in hours name of timezone found =  "+offsetHours);```

this will return Europe/Paris, and could be then entered into a Java TimeZone object (http://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html) to create the correct datetime from a UTC timestamp.

Dependencies:

- https://github.com/diwi/diewald_shapeFileReader to read the shapefile data. 

- The Java Topology Suite is used to build an rtree index to speed up queries, and perform geometric operations

NOTE : the admin boundary data have been simplified using topojson with 0.4 preservation parameter, and converted back
to shapefile. This could result in small imprecisions. To use your own data source, just add a shapefile boundary to the tz_data folder
and alter the reference to the filename found in the TimeZoneGeoTool.java

command to simplify admin boundary data used was = (topojson --cartesian -p --simplify-proportion 0.4 -o output.json tz.shp)

TODO:

consider allowing other coordinate from other projection systems to be used
