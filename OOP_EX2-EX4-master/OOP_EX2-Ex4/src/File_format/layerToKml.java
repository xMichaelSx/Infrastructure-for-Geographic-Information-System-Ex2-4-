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

	/**
	 * this function gets a layer object and writes its data to a new kml file
	 * @param data the layer that build
	 * @return File in kml
	 */
	public static File DataToKml(Layer data) {

		String linestr = "";
		String csvSplit = ",";
		String OutFile = "GisOut"+num+".kml";
		num++;                                //the number of the created file
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
			if(i==0) {pw.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");}  // reserved line for the title of the kml file, same for when i==1
			if(i==1) {pw.append("<kml xmlns=\"http://www.opengis.net/kml/2.2\"><Document><Style id=\"red\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href></Icon></IconStyle></Style><Style id=\"yellow\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/yellow-dot.png</href></Icon></IconStyle></Style><Style id=\"green\"><IconStyle><Icon><href>http://maps.google.com/mapfiles/ms/icons/green-dot.png</href></Icon></IconStyle></Style><Folder><name>Wifi Networks</name>\n");}
			if(i>1) {                          // starting to insert the metadata and coordinates where needed
				pw.append("<Placemark>\n");
				pw.append("<name><![CDATA["+element.getData().getSSId()+"]]></name> \n");
				pw.append("<description><![CDATA[BSSID: Capabilities:Timestamp: <b>"+element.getData().utcToString()+"</b><br/>Date: <b>"+element.getData().dateToString()+"</b>]]></description><styleUrl>#red</styleUrl>\n");
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

