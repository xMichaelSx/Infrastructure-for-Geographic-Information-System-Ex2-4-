package JUnitTests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Algorithems.MultiCSV;
import Coords.MyCoords;
import File_format.Csv2kml;
import Geom.Point3D;

class MyCoordsTest {


	@Test
	void testAdd() { // for now instructed not to use the z-axis
		boolean flag = false;
		Point3D gps=new Point3D(32.103315, 35.209039);
		Point3D vector=new Point3D(337.699,-359.249);
		MyCoords c = new MyCoords();
		Point3D p = new Point3D(c.add(gps,vector));
		if ((p.x()>32.1 &&p.x()<32.2) && (p.y()>35.2 &&p.y()<35.3)) {
			flag = true;
		}
		assertEquals(true, flag);
	}

	@Test
	void distance3D(){

		boolean flag = false;
		MyCoords c = new MyCoords();
		Point3D gps1=new Point3D(32.103315, 35.209039);
		Point3D gps2=new Point3D(32.106352, 35.205225);
		double ans = c.distance3d(gps1, gps2);
		if (ans < 493.1 && ans > 493)
			flag = true;

		assertEquals(true, flag);
	}

	@Test
	void vector3Dtest() {

		boolean flag=false;
		Point3D gps1=new Point3D(32.103315,35.209039);
		Point3D gps2=new Point3D(32.106352,35.205225);
		MyCoords c=new MyCoords();
		Point3D vec = c.vector3D(gps1, gps2);
		//		System.out.println("x="+vec.x()+"y="+vec.y());
		if((vec.x()<337.7 && vec.x()>337.6) && (vec.y()<-359.2 && vec.y()>-359.3))
			flag=true;
		assertEquals(true, flag);


	}


	@Test
	void azimuth_elevation_dist() {

		boolean flag = false;
		Point3D gps1 = new Point3D(32.10332,35.20904,670);
		Point3D gps2 = new Point3D(32.10635,35.20523,650);
		MyCoords c = new MyCoords();
		double[] arr = c.azimuth_elevation_dist(gps1, gps2);
		double azimuth = arr[0];
		double elevation = arr[1];
		double distance = arr[2];
		if ( (azimuth > 133 && azimuth < 133.3)&&(elevation > 0.03 && elevation < 0.05)&&(distance > 492.1 && distance < 492.3) )
			flag = true;
		assertEquals(true, flag);
	}
	
	@Test
	void testIsEquals() {
		
		Point3D checkPoint = new Point3D(170,80,20);
		MyCoords c = new MyCoords();
		boolean ans = c.isValid_GPS_Point(checkPoint);
		assertEquals(true, ans);
		
	}
	
	@Test
	void csvToKml() {
		
		String fileName = "WigleWifi_20171201110209.csv";
		Csv2kml c = new Csv2kml(fileName);
		boolean checkName = c.isNameOk(fileName);
		assertEquals(true, checkName);
		
	}
	
	
	
	
	
	
}
