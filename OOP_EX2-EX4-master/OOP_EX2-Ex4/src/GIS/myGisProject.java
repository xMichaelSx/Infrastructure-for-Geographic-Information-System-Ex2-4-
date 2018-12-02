package GIS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import Algorithems.MultiCSV;
import File_format.Csv2kml;
import File_format.layerToKml;

/**
 *  an array of layers
 *
 */
public class myGisProject implements GIS_project {
	ArrayList<GIS_layer> list = new ArrayList<GIS_layer>();

	public  myGisProject() {} // empty builder

	/**
	 *  arrayList functions
	 *
	 */
	@Override
	public boolean add(GIS_layer e) {
		return list.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_layer> c) {
		return list.addAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return list.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public Iterator<GIS_layer> iterator() {
		return list.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return list.retainAll(c);
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public Object[] toArray() {
		return list.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return list.toArray(a);
	}
	
	/**
	 *  returns the Meta_data of all the layers that are in the array
	 * 
	 *  
	 */
	@Override
	public Meta_data get_Meta_data() {
		Mdataarray arr=new Mdataarray();
		Iterator<GIS_layer> it = list.iterator();
		while(it.hasNext()) {
			Meta_data temp=it.next().get_Meta_data();
			arr.add(temp);//add the meta data to arr
		}
		return arr;
	}
	
	/**
	 *  gets the path for the folder and for each csv ending file,
	 *  enters to the array as a layer and enters all to a single layer,
	 *  and creates a kml file that has all the data of all the layers from that array.
	 * @param name the directory url place
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void mulToKml(String name) {
		MultiCSV mult=new MultiCSV(name);
		ArrayList<String> listCsv = mult.fileReciever(name);//get a directory
		Iterator<String> it = listCsv.iterator();
		while(it.hasNext()) {
			String str=it.next();
			Layer templay=new Layer(str);//create a new layer
			list.add(templay);
		}
		Layer kml = new Layer();
		Iterator<GIS_layer> it2 = list.iterator();
		while(it2.hasNext()) {
			Layer temp=(Layer) it2.next();
			kml.addAll(temp);//add the current layer data to kml 
		}
		kml.sendToKml();//create a kml file


	}
}
