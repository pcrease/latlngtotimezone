package main.java.example;

import main.java.LatLngCoordinate;
import main.java.TimeZoneGeoTool;

import java.io.IOException;
import java.io.InputStream;

public class Example {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws Exception {
		//example shows how to get the TimeZone string (e.g. Europe/Paris), and offset in hours for a lat,lng coordinate
	    System.out.println("running example....");

		String str = "";
		StringBuffer buf = new StringBuffer();
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream result =  classloader.getResourceAsStream( "timezone_data/test.txt" );

        String s = convertStreamToString(result);
        System.out.println(s);
        result.close();
        //File file = new File( classloader.getSystemResource("timezone_data/test.txt").toString());


		/*FileReader reader = null;
		try {
			reader = new FileReader(file);
			char[] chars = new char[(int) file.length()];
			reader.read(chars);
			System.out.println(new String(chars));
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(reader !=null){reader.close();}
		}*/

	    TimeZoneGeoTool timeZoneGeoTool = new TimeZoneGeoTool();
		timeZoneGeoTool.loadAdminBoundaryData();
		String tz=timeZoneGeoTool.getTimeZoneFromCoordinate(new LatLngCoordinate(48.8567,2.3508));//Paris
		timeZoneGeoTool.unLoadAdminBoundaryData();
		
		timeZoneGeoTool.loadTimeOffsetData();
        double offsetHours=timeZoneGeoTool.getOffsetFromCoordinate(new LatLngCoordinate(48.8567,2.3508 ));//Paris
        timeZoneGeoTool.unLoadTimeOffsetData();
		
		System.out.println("Offset in hours name of timezone found =  "+offsetHours);


	}

    static String convertStreamToString(java.io.InputStream is) {
        java.util.Scanner s = new java.util.Scanner(is).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
