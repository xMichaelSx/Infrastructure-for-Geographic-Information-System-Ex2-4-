package Algorithems;

import java.io.File;
import java.util.ArrayList;

//import Coords.temp;
import File_format.Csv2kml;
import GIS.myGisProject;

public class MultiCSV {
	ArrayList<String> list = new ArrayList<>();
	Csv2kml c = new Csv2kml();
	File file = new File("C:\\Users\\zzkmp\\eclipse-workspace\\OOP_EX2-EX4-master.zip_expanded");
String fileName;
	public MultiCSV(String fileName){
		this.fileName=fileName;
	}
	
	public ArrayList<String> foo(String file)
	{
		File file2 = new File (file);
		foo(file2);
		return list;
	}
	public void foo(File file)
	{
		File[] fileArr = file.listFiles();
		for (File temp : fileArr)
		{
			if (temp.isDirectory()) foo(temp);
			if (c.isNameOk(temp.getName())) {
				list.add(temp.getName());
				//				System.out.println(temp.getName());
			}
		}
	
	}

}

