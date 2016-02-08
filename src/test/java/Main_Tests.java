package test.java;

import main.java.timezonetools.LatLngCoordinate;
import main.java.timezonetools.TimeZoneGeoTool;
import org.junit.Test;

import static junit.framework.Assert.assertNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by paulcrease on 08/02/16.
 */
public class Main_Tests {

    private final static LatLngCoordinate ParislatLngCoordinate = new LatLngCoordinate(48.8567,2.3508 );
    private final static LatLngCoordinate outsideExtentlatLngCoordinate = new LatLngCoordinate(488.8567,2.3508 );
    private final static double parisOffset = 1.0;

    @Test
    public void basicTest() {


        TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();

        try {
            timeZoneGeoTool.loadTimeOffsetGeoJsonData();
        } catch (Exception e) {
            e.printStackTrace();
        }

        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(ParislatLngCoordinate);//Paris
        assertEquals("Paris has +1.0 offset", parisOffset, timeZoneGeoTool.getOffsetFromCoordinate(ParislatLngCoordinate));
        //System.out.println(timeZoneGeoTool.getOffsetFromCoordinate(outsideExtentlatLngCoordinate));
        assertNull("Coord outside extents....should be Null",  timeZoneGeoTool.getOffsetFromCoordinate(outsideExtentlatLngCoordinate));


        timeZoneGeoTool.unLoadTimeOffsetData();

    }
}
