latlngtotimezone
================

A simple java library that allows a latitude, longitude coordinates to be translated to a Java offset in hours. The library is released under an MIT License.

The API consists of a single object class that can be used to search for an offset in hours using a pair of WGS84 latitude, longitude coordinates. It uses an in-memory RTree to speed up the queries, and uses the following dataset - http://www.arcgis.com/home/item.html?id=1d14b1662a3c4375aa2e31008beb103b converted to a GeoJSON file.

Example 

```
	    TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();

		//loads the timezone data into memory
		timeZoneGeoTool.loadTimeOffsetData();
        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(new LatLngCoordinate(48.8567,2.3508 ));//Paris

		
		System.out.println("Offset in hours name of timezone found =  "+offsetHours);
```

this will return an offset in hours (1.0 for Paris), and could be then entered into the Java TimeZone or Calendar object of your choice (http://docs.oracle.com/javase/7/docs/api/java/util/TimeZone.html) to create the correct datetime from a UTC timestamp.

a pre-compliled jar can be found in the /compiled_jar folder which contians the library API and the data all rolled up into a JAR. The project can be built using Maven tools.

Dependencies:

- https://github.com/bjornharrtell/jts2geojson to read the GeoJSON data.

- The Java Topology Suite is used to build an rtree index to speed up queries, and perform geometric operations
