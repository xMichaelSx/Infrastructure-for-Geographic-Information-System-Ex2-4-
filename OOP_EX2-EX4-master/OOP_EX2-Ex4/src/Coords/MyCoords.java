package Coords;

import Geom.Point3D;

/**
 * this class represents the 3D coordinates of a gps point
 * 
 */
public class MyCoords implements coords_converter {

	final int EarthR = 6371000; //earth`s radius.
	double LonNorm = 0.8470911745; // to the given start point (Building 9).

	@Override
	public  Point3D add(Point3D gps, Point3D local_vector_in_meter) {
		Point3D newpoint=new Point3D(gps); // will be the end point after going in the vectors direction.
		newpoint=fromGpsToMeters(newpoint); //converting gps coordinates to meters
		newpoint.add(local_vector_in_meter); // now that both points are in meters we sum them
		newpoint=toGpsFromMeters(newpoint); // converting the meters back to a gps point.
		return newpoint;
	}
	/**
	 * A helping function that transforms gps coordinates to meters.
	 * @Param Point3D gpspoint.
	 */
	private Point3D fromGpsToMeters(Point3D gpsPoint) {
		double x=gpsPoint.x();
		double y=gpsPoint.y();
		x=(x*Math.PI)/180; // converting angle to radian
		x=Math.sin(x)*EarthR; // converting radian to meters by multiplying earths radius with sin(lat)
		y=(y*Math.PI)/180; // converting angle to radian
		y=Math.sin(y)*EarthR*LonNorm; // converting radian to meters by multiplying earths radius with sin(lon) and lon Norm
		return new Point3D(x,y);		
	}

	/**
	 * A helping function that transforms gps coordinates to meters.
	 * @Param Point3D gpspoint.
	 */
	private Point3D toGpsFromMeters(Point3D newpoint) {
		double x=newpoint.x(); // the lat
		double y=newpoint.y(); // the lon
		x=Math.asin(x/EarthR);
		x=(x*180)/Math.PI;
		y=Math.asin(y/(EarthR*LonNorm));
		y=(y*180)/Math.PI;
		return new Point3D(x,y);
	}

	/**
	 * returns the air distance in meters between two gps points
	 * 
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D distance = vector3D(gps0, gps1);
		return Math.sqrt(distance.x()*distance.x()+distance.y()*distance.y());
	}

	/**
	 * returns the vector(in meters) between two gps points
	 * 
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double diff_RadLat = gps1.x()-gps0.x();
		double diff_RadLon = gps1.y()-gps0.y();
		Point3D vector = new Point3D(diff_RadLat,diff_RadLon);
		vector = fromGpsToMeters(vector);
		return vector;
	}

	/**
	 * computes the elevation, distance and gets the azimuth from the helping function below
	 * 
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double azimuth= azimuthFinder(gps0, gps1); // azimuth
		double distance=distance3d(gps0, gps1);
		double elevation = Math.asin(gps1.z()-gps0.z()/distance); // elevation pitch TODO
		double polarVector[] = {azimuth, elevation, distance};
		return polarVector;
	}

	/**
	 * a helping function that computes the azimuth
	 * 
	 */
	private double azimuthFinder(Point3D gps0, Point3D gps1) { 
		double x1=Math.toRadians(gps0.x());
		double x2=Math.toRadians(gps1.x());
		double deltaY = Math.toRadians(gps1.y())-Math.toRadians(gps0.y());
		double xatan2=Math.sin(deltaY)*Math.cos(x2);
		double yatan2=Math.cos(x1)*Math.sin(x2)-Math.sin(x1)*Math.cos(x2)*Math.cos(deltaY);
		double alpha =Math.atan2(xatan2, yatan2);
		alpha= Math.toDegrees(alpha);
		if(alpha<0)
			alpha=180+alpha;
		return alpha;

	}

	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf].
	 * 
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;

		if (p.x()<=180 && p.x()>=-180)
			flag1 = true;

		if (p.y()<=90 && p.y()>=-90)
			flag2 = true;

		if (p.z()>=-450)
			flag3 = true;

		if (flag1 && flag2 && flag3)
			return true;

		return false;
	}
}
