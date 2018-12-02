package File_format;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import GIS.Layer;
/**
 *  a class that reads from a csv file
 *  
 * 
 */
public class Csv2kml {
	ArrayList<String> A = new ArrayList<String>();
	private String fileName = null; 	
	private static int number=0; // will be used upon reading
	public Csv2kml() {}

	/**
	 *  constructor that gets the file name.
	 *  @param fileName String 
	 */
	public Csv2kml(String fileName) { // checking if the whole file is ok

		if (isNameOk(fileName))
			this.fileName = fileName;	
	}

	/**
	 * checking if the name of the file is as expected
	 * @param fileName String: the name of the file for reading
	 * 
	 * 
	 * @return this true or false
	 */
	public boolean isNameOk(String fileName) { // checking if the name of the file has the appropriate beginning and ending
		if (fileName.length()<11) return false;
		String check1 = fileName.substring(0, 10); // only valid when the data is provided inside the project
		String check2 = fileName.substring(fileName.length()-4, fileName.length());
		if (check1.equals("WigleWifi_") && check2.equals(".csv"))
			return true;
		return false;
	}

	/**
	 *  reads a csv(exel) file and can be used as java data
	 *  
	 *  
	 *  @return A the array of file names
	 */
	public ArrayList<String> csvReader() {  //csv to java

		try {	
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String reciever = "";
			br.readLine();               // not interested in the 2 first lines as they do not hold valuable data,
			br.readLine();               // instead skip 2 lines.
			while ((reciever = br.readLine())!=null) {
				A.add(reciever);

			}
			br.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return A;
	}
}
