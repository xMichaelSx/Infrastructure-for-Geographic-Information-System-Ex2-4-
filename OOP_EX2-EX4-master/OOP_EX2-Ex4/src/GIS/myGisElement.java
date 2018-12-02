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
	 * @param strcsv the string array that we build with the element
	 */
	public myGisElement(String[] strcsv) {
		// TODO Auto-generated constructor stub
		if(strcsv.length!=11) {//if the size of the array != 11 than its not good
			System.out.println("invaild string array");
			point =null;
			data=null;
		}
		else {
			String pointstr=strcsv[6]+","+strcsv[7]+","+strcsv[8];//the 6,7,8 places in the array represent the point
			this.point=new Point3D(pointstr);
			this.data=new Mdata(strcsv[0], strcsv[1], strcsv[2], strcsv[3], strcsv[4], strcsv[5], strcsv[9], strcsv[10]);
//the rest array represent the meta data
		}
	}
	/** 
	 * return the point value of this element
	 */
	@Override
	public Geom_element getGeom() { // return the point
		return point;

	}
	
	/**
	 * returns all the data information for example UTC NAME etc.. except the (x,y,z) coordinates. 
	 *
	 */
	@Override
	public Mdata getData() { // return the meta data of this element
		return data;
	}

	/**
	 *  works similar to the coords_converter Point3D add function, we get a vector to move a point to its direction.
	 * @param vec the vector we add to this point
	 * 
	 */
	@Override
	public void translate(Point3D vec) { 
		// TODO Auto-generated method stub
		MyCoords trans = new MyCoords();
		point=trans.add(point, vec);
	}
	
	
	/**
	 *  checks if the point and the meta data of the giveb element is equal to the point and meta data of another point
	 * @param e the element we check if it equals to this element
	 * @return e the element we return
	 */
	public boolean equals(myGisElement e) {

		if(this.point.equals(e.point)&&this.data.equalsData(e.data)) {
			return true;
		}
		return false;
	}
	/**
	 * return the x value of this element point
	 * @return x the x value
	 */
	public double getx() {
		return point.x();
	}
	/**
	 * return the y value of this element point
	 * @return y the y value of the point
	 */
	public double gety() {
		return point.y();
	}
	/**
	 * return the z value of this element point
	 * @return z the z value of the point
	 */
	public double getz() {
		return point.z();
	}
	


}
