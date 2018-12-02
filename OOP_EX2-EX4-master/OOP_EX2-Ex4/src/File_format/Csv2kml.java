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
 */
public class Csv2kml {
	ArrayList<String> A = new ArrayList<String>();
	private String fileName = null; 	
	private static int number=0; // will be used upon reading
	public Csv2kml() {}

	public Csv2kml(String fileName) {

		if (isNameOk(fileName))
			this.fileName = fileName;	

	}

	/**
	 *  checking if the name of the file is as expected
	 *
	 */
	public boolean isNameOk(String fileName) {
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
	 */
	public ArrayList<String> csvReader() {  //csv to java

		try {	
			//			System.out.println(fileName);
			BufferedReader br = new BufferedReader(new FileReader(fileName));
			String reciever = "";
			br.readLine();
			br.readLine();
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





