package GIS;

import java.util.ArrayList;
import java.util.Date;

import Geom.Point3D;

/**
 *  this class implements the Meta_data interface and represents all the data of the gis_element except the coordinates
 *
 */
public class Mdata implements Meta_data  {
	ArrayList<Mdata> arr = new ArrayList<>();
	String MAC,SSID,AuthMode,TimeDate,Channel,RSSI,AccuracyMeters,Type;
	String[] array;
	public Mdata(String MAC,String SSID,String AuthMode,String TimeDate,String Channel,String RSSI,String AccuracyMeters,String Type) {

		this.MAC = MAC; this.SSID = SSID; this.AuthMode = AuthMode;this.TimeDate = TimeDate; 
		this.Channel = Channel;this.RSSI = RSSI; this.AccuracyMeters = AccuracyMeters; this.Type = Type;

		array=new String[8];

		String[] array1={MAC,SSID,AuthMode,TimeDate,Channel,RSSI,AccuracyMeters,Type};
		for(int i=0;i<array1.length;i++) {
			array[i]=array1[i];
		}

		
	}
	
	/**
	 *  returns the right time according to the given knowledge
	 * 
	 *  
	 * 
	 * 
	 */
	@Override 
	public long getUTC() {
		
		String temp = TimeDate.split(" ")[1];//take the second cell value in the array
		String temp2[] = temp.split(":");//split the string from :
		String timestr=temp2[0]+temp2[1];//add the strings together
		long time = Long.parseLong(timestr);//turn the string to long
		return time;
	}
	
	/**
	 *  a toString function
	 *
	 */
	public String toString() {
		String strdata=this.MAC+this.SSID+this.AuthMode+this.TimeDate+this.Channel+this.RSSI+this.AccuracyMeters+this.Type;
		return strdata;
	}
/**
 * this function return the Orientation of this data
 */
	@Override
	public Point3D get_Orientation() {  // next time
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  checking if the mData of a gis_element is equal to anothers 
	 * @param elm the mdata we check if equals to this Mdata
	 * @return elm the Mdata we return
	 */
	public boolean equalsData(Mdata elm) {
		for(int i=0;i<array.length;i++) {
			if(this.array[i]!=elm.array[i]) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 *  getters for the data of the mData except coordinates
	 *
	 */
	/**
	 * return ssid value of this data
	 * @return this value
	 */
	public String getSSId() {
		return this.SSID;
	}
	/**
	 * return get mac as string
	 * @return this value
	 */
	public String getMAC() {
		return this.MAC;
	}
	/**
	 * return the autmode string value
	 * @return this value
	 */
	public String getAutMode() {
		return this.AuthMode;
	}
	/**
	 * return channel string value
	 * @return this value
	 */
	public String getChannel() {
		return this.Channel;	
	}
	/**
	 * return rssi string value
	 * @return this rssi value
	 */
	public String getRSSI() {
		return this.RSSI;
	}
	/**
	 * return meters string value
	 * @return this meters 
	 */
	public String getmeters() {
		return this.AccuracyMeters;
	}
	/**
	 * return type string value
	 * @return this type
	 */
	public String getType() {
		return this.Type;
	}
	/**
	 * return utc string value
	 * 
	 * @return temp the utc string value
	 */
	public String utcToString() {
	String temp=TimeDate.split(" ")[1];
	return temp;
	}
	/**
	 * return date string value
	 * 
	 * @return temp the date
	 */
	public String dateToString() {
		String temp=TimeDate.split(" ")[0];
		return temp;
	}
}
