package Coords;

import Geom.Point3D;

/**
 * this class represents the 3D coordinates of a gps point
 * 
 */
public class MyCoords implements coords_converter {

	final int EarthR = 6371000; //earth`s radius.
	double LonNorm = 0.8470911745; // to the given start point (Building 9).
/**
 * computes a new point which is the gps point transformed by a 3D vector (in meters)
 * @param  gps a 3D point as a gps
 * @param  local_vector_in_meter a 3D point that represent a vector in meters
 * 
 */
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
	 * @Param  gpspoint the gps point we want to tarnform to meters
	 * @param x the x value of gpspoint
	 * @param y the y value of gpspoint
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
	 * A helping function that transforms vector coordinates to gps coordinates.
	 * @Param newpoint the point we want to tranform to gps
	 * @param x the value of newpoint
	 * @param y the y value of newpoint
	 */
	private Point3D toGpsFromMeters(Point3D newpoint) {
		double x=newpoint.x(); // the x value we will tranlate to meters
		double y=newpoint.y(); // the y value we translate to meters
		x=Math.asin(x/EarthR);
		x=(x*180)/Math.PI;
		y=Math.asin(y/(EarthR*LonNorm));
		y=(y*180)/Math.PI;
		return new Point3D(x,y);
	}

	/**
	 * returns the air distance in meters between two gps points
	 * @param gps0 first gps point
	 * @param gps1 second gps point
	 * 
	 */
	@Override
	public double distance3d(Point3D gps0, Point3D gps1) {
		Point3D distance = vector3D(gps0, gps1);
		return Math.sqrt(distance.x()*distance.x()+distance.y()*distance.y());//the distance value we return
	}

	/**
	 * returns the vector(in meters) between two gps points
	 * @param gps0 first gps point
	 * @param gps1 second gps point
	 *
	 * 
	 * 
	 */
	@Override
	public Point3D vector3D(Point3D gps0, Point3D gps1) {
		double diff_RadLat = gps1.x()-gps0.x();//difrent of x0 and x1
		double diff_RadLon = gps1.y()-gps0.y();//disfrent of y0 and y1
		Point3D vector = new Point3D(diff_RadLat,diff_RadLon);
		vector = fromGpsToMeters(vector);
		return vector;
	}

	/**
	 * computes the elevation, distance and gets the azimuth from the helping function below
	 * 
	 * 
	 * 
	 * @param gps0 the first gps point
	 * @param gps1 second gps point
	 * 
	 */
	@Override
	public double[] azimuth_elevation_dist(Point3D gps0, Point3D gps1) {
		
		double azimuth= azimuthFinder(gps0, gps1); // azimuth
		double distance=distance3d(gps0, gps1);//call distance function
		double elevation = Math.asin(Math.abs(gps0.z()-gps1.z())/distance); // elevation pitch TODO
		double polarVector[] = {azimuth, elevation, distance};
		return polarVector;
	}

	/**
	 * a helping function that computes the azimuth
	 * @param gps0 first gps point
	 * @param gps1 second gps point
	 * @param x1 gps0 x value as radian
	 * @param x2 gps1 x value as radian
	 * @papram deltay the difrent between the two points
	 * @param xatan2 the x arctangs2 value with 
	 * @param ytan2  the y arctngs2 value
	 * @param alpha the angle we return
	 */
	private double azimuthFinder(Point3D gps0, Point3D gps1) { 
		double x1=Math.toRadians(gps0.x());//turn x1 to radian
		double x2=Math.toRadians(gps1.x());//turn x2 to radian
		double deltaY = Math.toRadians(gps1.y())-Math.toRadians(gps0.y());
		double xatan2=Math.sin(deltaY)*Math.cos(x2);
		double yatan2=Math.cos(x1)*Math.sin(x2)-Math.sin(x1)*Math.cos(x2)*Math.cos(deltaY);
		double alpha =Math.atan2(xatan2, yatan2);
		alpha= Math.toDegrees(alpha);
		if(alpha<0)//if alpha < 0 than its negative so we calculate the poisitive value 
			alpha=180+alpha;//add 180 to alpha
		return alpha;

	}

	/**
	 * return true iff this point is a valid lat, lon , lat coordinate: [-180,+180],[-90,+90],[-450, +inf].
	 * @param p the point we cheak
	 */
	@Override
	public boolean isValid_GPS_Point(Point3D p) {
		boolean flag1 = false;
		boolean flag2 = false;
		boolean flag3 = false;

		if (p.x()<=180 && p.x()>=-180)//if true than flag1 true
			flag1 = true;

		if (p.y()<=90 && p.y()>=-90)//if true than flag2 true
			flag2 = true;

		if (p.z()>=-450)//if true then flag3 true
			flag3 = true;

		if (flag1 && flag2 && flag3)//if all true then is not valid 
			return true;

		return false;
	}
}
