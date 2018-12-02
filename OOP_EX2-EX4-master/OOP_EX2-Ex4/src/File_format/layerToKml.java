package File_format;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import GIS.*;

/**
 * this class translate a single layer to a kml file
 * 
 */
public class layerToKml {
private static int num=0;
	// Step 1: CsvReader-->List
	//Step 2: List2Layer --> Layer
	//Step 3: list2kml --> turn the layer into kml

	/**
	 * this function gets a layer object and writes its data to a new kml file
	 * @param data
	 * @return File in kml
	 */
	public static File DataToKml(Layer data) {

		String line = "";
		String cvsSplitBy = ",";
		String OutFile = "GisOut"+num+".kml";
		num++;
		PrintWriter pw = null;
		File ans = new File(OutFile);
		try 
		{
			pw = new PrintWriter(ans);
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		Iterator<GIS_element> it = data.iterator();
		int i = 0;
		while(it.hasNext()) {
			myGisElement element = (myGisElement)it.next();
			if(i==0) {pw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");}
			if(i==1) {pw.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");}
			if(i>1) {
				pw.append("<Placemark>\n");
				pw.append("<name><![CDATA["+element.getData().getSSId()+"]]></name> \n");
				pw.append("<description><![CDATA[BSSID: Capabilities:Timestamp: <b>"+element.getData().getUTC()+"</b><br/>Date: <b>"+"</b>]]></description><styleUrl>#red</styleUrl>\n");
				pw.append("<Point> \n");
				pw.append("<coordinates>"+element.gety()+","+element.getx()+"</coordinates></Point>\n");
				pw.append("</Placemark>\n");
			}
			i++;
		}
		pw.append("</Folder>\n");
		pw.append("</Document></kml>");
		pw.close();
		System.out.println("done");
		return ans;
	}

}

