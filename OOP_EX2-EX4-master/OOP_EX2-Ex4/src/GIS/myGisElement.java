package GIS;

import Geom.Geom_element;
import Geom.Point3D;
import Coords.MyCoords;

public class myGisElement implements GIS_element {
	Point3D point;
	Mdata data;

	/**
	 * this constructor gets a string array and checks if the size of the array is not equal 11 else throws exeption
	 * else we divide the data to two places: point and data.
	 * point gets the coordinates and data gets the meta data.
	 *
	 */
	public myGisElement(String[] strcsv) {
		// TODO Auto-generated constructor stub
		if(strcsv.length!=11) {
			System.out.println("invaild string array");
			point =null;
			data=null;
		}
		else {
			String pointstr=strcsv[6]+","+strcsv[7]+","+strcsv[8];
			this.point=new Point3D(pointstr);
			this.data=new Mdata(strcsv[0], strcsv[1], strcsv[2], strcsv[3], strcsv[4], strcsv[5], strcsv[9], strcsv[10]);

		}
	}
	@Override
	public Geom_element getGeom() { // return the point
		return point;

	}
	
	/**
	 * returns all the data information for example UTC NAME etc.. except the (x,y,z) coordinates. 
	 *
	 */
	@Override
	public Mdata getData() { // time color (point?)
		return data;
	}

	/**
	 *  works similar to the coords_converter Point3D add function, we get a vector to move a point to its direction.
	 *
	 */
	@Override
	public void translate(Point3D vec) { 
		// TODO Auto-generated method stub
		MyCoords trans=new MyCoords();
		point=trans.add(point, vec);
	}
	
	
	/**
	 *  checks if the point and the meta data of the giveb element is equal to the point and meta data of another point
	 *
	 */
	public boolean equals(myGisElement e) {

		if(this.point.equals(e.point)&&this.data.equalsData(e.data)) {
			return true;
		}
		return false;
	}
	public double getx() {
		return point.x();
	}
	public double gety() {
		return point.y();
	}
	public double getz() {
		return point.z();
	}
	


}
