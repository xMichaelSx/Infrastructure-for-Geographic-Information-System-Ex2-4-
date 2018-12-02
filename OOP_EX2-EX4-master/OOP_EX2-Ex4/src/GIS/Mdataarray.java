package GIS;
import java.util.ArrayList;
import java.util.Iterator;

import Geom.Point3D;

/**
 *  this class implements Meta_data interface and is an array of Meta_data
 *
 */
public class Mdataarray implements Meta_data  {
	ArrayList<Meta_data> arr = new ArrayList<>();
	
	/**
	 *  adding a new Meta_data to the array
	 *
	 */
	public void add(Meta_data dataHolder) {
		arr.add(dataHolder);
	}
	public String tostring() {
		Iterator<Meta_data> it = arr.iterator();
		String str="";
		while(it.hasNext()) {
			Meta_data dataHolder = it.next();
			str = str + dataHolder.toString();
		}
		return str;
	}
	public long getUTC() {	
		return 0; // TODO
	}

	public Point3D get_Orientation() { // TODO next time
		
		return null;
	}

}
