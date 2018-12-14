/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

public class Point {
	private double x;
	private double y;
		
	public Point(double latitude, double longtitude, double w, double h) {
		double scaleLong = w/(Graph.maxLong-Graph.minLong);
		double scaleLat = h/(Graph.maxLat-Graph.minLat);
		x = (longtitude-Graph.minLong)*scaleLong;
		y = h-(latitude-Graph.minLat)*scaleLat;
	}

	//getters
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}
}
