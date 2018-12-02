package GIS;

import Geom.Point3D;

public interface Meta_data {
	
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC();
	/** return a String representing this data */
	public String toString(); //time,color,point...
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation(); // next time so for now return null
}
