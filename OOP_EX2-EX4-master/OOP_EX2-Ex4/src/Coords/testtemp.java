package Coords;

import Algorithems.MultiCSV;
import GIS.myGisProject;

//import com.sun.java.util.jar.pack.Package.File;
public class testtemp extends MyCoords{

	public static void main(String[] args) {
		
		// test add
//		Point3D gps=new Point3D(32.103315, 35.209039);
//		Point3D vector=new Point3D(337.699,-359.249);
//		MyCoords c = new MyCoords();
//		Point3D p = new Point3D(c.add(gps,vector));
//		System.out.println("x is "+p.x() + " and the y is "+p.y());
		
		// test vector
//		MyCoords c = new MyCoords();
//		Point3D gps1=new Point3D(32.103315, 35.209039);
//		Point3D gps2=new Point3D(32.106352, 35.205225);
//		Point3D ans = c.vector3D(gps1, gps2);
//		System.out.println(ans.x());
//		System.out.println(ans.y());
		
		// test distance
//		MyCoords c = new MyCoords();
//		Point3D gps1=new Point3D(32.103315, 35.209039);
//		Point3D gps2=new Point3D(32.106352, 35.205225);
//		double ans = c.distance3d(gps1, gps2);
//		System.out.println(ans);
		
		// test isValid
//		isValid_GPS_Point
		////[-180,+180],[-90,+90],[-450, +inf]
//		MyCoords c = new MyCoords();
//		Point3D gps1=new Point3D(100, 89.209039,-450);
//		System.out.println(c.isValid_GPS_Point(gps1));
		
		//test azimuth
//		Point3D gps1=new Point3D(32.1033,35.209);
//		Point3D gps2=new Point3D(32.1064,35.2052);
//		MyCoords c =new MyCoords();
//		double[] arr=c.azimuth_elevation_dist(gps1, gps2);
//		System.out.println("azimuth= "+arr[0]+" "+"elvation= "+arr[1]+" "+"distance= "+arr[2]);
		
//		Csv2kml c = new Csv2kml("WigleWifi_20171201110209.csv");
//		c.csvReader();
//		File file = new File("C:\\Users\\zzkmp\\eclipse-workspace\\OOP_EX2-EX4-master.zip_expanded");
	
		
	
//		Csv2kml cs=new Csv2kml("WigleWifi_20171201110209.csv");
//		Layer gisarr=new Layer("WigleWifi_20171201110209.csv");
//		gisarr.sendToCsv();
				
	myGisProject arr=new myGisProject();
	String str="C:\\Users\\zzkmp\\eclipse-workspace\\OOP_EX2-EX4-master.zip_expanded";
	arr.mulToKml(str);
	
	}
	
		
		

}
