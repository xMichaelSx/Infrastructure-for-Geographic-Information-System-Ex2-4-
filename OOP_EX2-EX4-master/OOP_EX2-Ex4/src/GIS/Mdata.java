package GIS;

import java.util.ArrayList;

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

		//				MAC;array[1]=SSID;array[2]=AuthMode;array[3]=TimeDate
	}
	
	/**
	 *  returns the right time according to the given knowledge
	 *
	 */
	@Override //TODO need to repair the colors
	public long getUTC() {
		String temp = TimeDate.split(" ")[1];
		String temp2[] = temp.split(":");
		String timestr=temp2[0]+temp2[1];
		long time = Long.parseLong(timestr);
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

	@Override
	public Point3D get_Orientation() {  // next time
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 *  checking if the mData of a gis_element is equal to anothers 
	 *
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
	public String getSSId() {
		return this.SSID;
	}
	public String getMAC() {
		return this.MAC;
	}
	public String getAutMode() {
		return this.AuthMode;
	}
	public String getChannel() {
		return this.Channel;	
	}
	public String getRSSI() {
		return this.RSSI;
	}
	public String getmeters() {
		return this.AccuracyMeters;
	}
	public String getType() {
		return this.Type;
	}
}
