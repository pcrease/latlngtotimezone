package main.java.timezonetools;

import com.vividsolutions.jts.geom.Coordinate;

public class LatLngCoordinate {

   
    private Coordinate coord;
    
    
    public LatLngCoordinate( double latitude,double longitude){
        //X,Y order
        setCoordinate(new Coordinate(longitude,latitude));
    }


    public Coordinate getCoordinate() {
        return coord;
    }


    public void setCoordinate(Coordinate coord) {
        this.coord = coord;
    }
    
    
    
}
