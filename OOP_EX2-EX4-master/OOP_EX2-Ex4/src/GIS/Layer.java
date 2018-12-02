package GIS;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import File_format.Csv2kml;
import File_format.layerToKml;

/**
 *  a class that implements the Gis_layer interface and is an array of Gis_element
 *
 */
public class Layer implements GIS_layer {
	ArrayList<String> stringArr = new ArrayList<>();
	ArrayList<GIS_element> layerSet = new ArrayList<GIS_element>();
	
	/**
	 *  this builder gets string elements from a csv file and builds with it a single layer
	 *
	 */
	public Layer(String fileName) {
		Csv2kml temp = new Csv2kml(fileName);
		this.stringArr = temp.csvReader();
		Iterator<String> it = stringArr.iterator();
		while (it.hasNext()) {
			String[] str = it.next().split(",");
			myGisElement elem = new myGisElement(str);
			layerSet.add(elem);
		}

	}
	public Layer() {};

	/**
	 *  arrayList functions
	 *
	 */
	@Override
	public boolean add(GIS_element e) { //TODO
		if(layerSet.contains(e))
			return false; // already exists so we don't add again
		else {
			return layerSet.add(e); // we add the element
		}
		
	}

	@Override //TODO
	public boolean addAll(Collection<? extends GIS_element> c) { // true if this collection changed as a result of the call
		int counter = 0;
		boolean flag = false;
		Iterator<? extends GIS_element> it = c.iterator();
		while(it.hasNext()) {
			GIS_element temp = it.next();
			flag = this.add(temp);
			if(flag == true)
				counter++;
			
			flag = false; // reseting the flag for further use
		}
		if(counter>0)
			return true; // collection changed
		else
			return false; // didn't add at all.
		
	}

	@Override
	public void clear() {

		layerSet.clear();
	}

	@Override
	public boolean contains(Object o) {
		return layerSet.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return layerSet.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return layerSet.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return layerSet.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return layerSet.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {

		return layerSet.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {

		return layerSet.retainAll(c);
	}

	@Override
	public int size() {

		return layerSet.size();
	}

	@Override
	public Object[] toArray() {

		return layerSet.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {

		return layerSet.toArray(a);
	}
	
	/**
	 *  returns the meta data of all the elements in the layer 
	 *
	 */
	@Override
	public Meta_data get_Meta_data() {

		Mdataarray arr=new Mdataarray();
		Iterator<GIS_element> it = layerSet.iterator();
		while(it.hasNext()) {
			GIS_element temp = it.next();
			arr.add((Mdata)temp.getData());

		}
		return arr;
	}
	
	/**
	 *  a function that translates the layer to a kml file
	 *
	 */
	public void sendToKml(){

		layerToKml temp=new layerToKml();
		temp.DataToKml(this);


	}
}
