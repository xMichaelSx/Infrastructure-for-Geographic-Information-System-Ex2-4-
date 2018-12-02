package Algorithems;

import java.io.File;
import java.util.ArrayList;

//import Coords.temp;
import File_format.Csv2kml;
import GIS.myGisProject;


/**
 *   this class receives the names of the files needed to add to the list of string and returns an array that the
 *   class myGisProject will translate finally to kml.
 */
public class MultiCSV {
	
	ArrayList<String> list = new ArrayList<>();
	Csv2kml c = new Csv2kml();
	File file = new File("C:\\Users\\zzkmp\\eclipse-workspace\\OOP_EX2-EX4-master.zip_expanded"); // new file in the 
	String fileName;
	
	/**
	 *  constructor that gets the file name.
	 *   @param fileName the name of the file
	 */
	public MultiCSV(String fileName){
		this.fileName=fileName;
	}

	/**
	 *  method overloading - we want to get the file but we have a file(name) string,
	 *	so we make a new file with the wanted name and send it to the next function with an arraylist that will store the files name. 
	 *  @param file  name of the file
	 *  
	 *  @return list the array that will be return
	 */
	public ArrayList<String> fileReciever(String file) { 
		File file2 = new File (file);
		fileReciever(file2);
		return list;
	}
	
	/**
	 *  this function receives a file and checks if its a file or a directory, if is a directory, will 
	 *  get inside recursively and check again until finds a file. then checks if the file 
	 *  is valid which will then insert in the list of files which will return to the caller function (above).
	 *  @param file File: a file with the wanted name of file from the above function
	 */
	public void fileReciever(File file)
	{
		File[] fileArr = file.listFiles();
		for (File temp : fileArr)
		{
			if (temp.isDirectory()) fileReciever(temp);
			if (c.isNameOk(temp.getName())) {
				list.add(temp.getName());
			}
		}

	}

}

