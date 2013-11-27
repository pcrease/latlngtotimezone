latlngtotimezone
================

a java library that allows a latitude, longitude coordinates to be translated to a java timezone string. 

The national boundary polygon data for this library comes from http://efele.net/maps/tz/world/, although the polygons have been simplified (~1km using Douglas-Peuker) to remove nodes and shrink the .jar size. However, if  greater specificity is required the shapefile in the /data folder can be changed to one that is preferred
The API consists of a single object that can be used to serch for the time zone string using a pair of WGS84 longitude, latitude coordinates. NOTE: coordinate order is Longitude, Latitude

Example :

TimeZoneGeoTool timeZoneGeoTool = TimeZoneGeoTool.getTimeZoneGeoToolInstance();
String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new Coordinate(2.3508, 48.8567)); //coords for Paris

this will return Europe/Paris.

Dependencies:

- https://github.com/diwi/diewald_shapeFileReader to read the shapefile data. 

- The Java Topology Suite is used to build an rtree index to speed up queries, and perform geometric operations


TODO:

consider allowing other coordinate from other projection systems to be used