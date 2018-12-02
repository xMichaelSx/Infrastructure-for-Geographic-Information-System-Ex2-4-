# Infrastructure-for-Geographic-Information-System-Ex2-4-
University assignment (2-4).

this project starts from an infrastructure of a GIS like system which will be extended in the following excersizes. 
Class MyCords - implements the coords converter interface and allows covertion of global coorinates to local coordinates.
also allows adding of a gps point with a vector and transforms it with a vector. allowes the alculation of distance, azimuth and elevation of two gps like points.

package gis includes geografic information which is devided to layers with referene to time,place,color and more...
Class myGisElement - represents an element that is a sibgle line in a csv file that containts 2 objects. one is point which represents the gps point coordinates and the other one is data which holds the metadata.
Class layer - it is an array of gis elements that in the end will represent a single csv file and translate to a kml file.
Class myGisProject - an array of layers which represent a directory that contains a few csv files and translates to a single kml file that has all of its data.
Class csvToKml - reads a file and puts its content in a layer.
Class layerToKml - writs a single layer to a new kml file.
Class multiCsv - runs on the directory and for each csv file it finds, inserts it into a list of layers which is an object of Class myProject.
Class mData - a class that represent all the information of a single gis element except the poins coordinates.
Class mDataArray - a class that represent an array of metadata and represent all the data in a single layer or my project.

the rest of the code will be used and explained in the next assignment
