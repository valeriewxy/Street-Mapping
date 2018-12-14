/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

public class Node {
	private String id;
	private boolean visited;
	private Node parent;
	private double latitude;
	private double longitude;
	private double dis;
	
	public Node(String id, boolean visited, double latitude, double longitude) {
		this.id = id;
		this.visited = visited;
		this.latitude = latitude;
		this.longitude = longitude;
		dis = Double.POSITIVE_INFINITY;
		parent = null;
	}

	//getters
	public String getId() {
		return id;
	}

	public boolean isVisited() {
		return visited;
	}

	public Node getParent() {
		return parent;
	}

	public double getLatitude() {
		return latitude;
	}

	public double getLongitude() {
		return longitude;
	}
	
	public double getDis() {
		return dis;
	}

	//setters
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}
	
	public void setDis(double dis) {
		this.dis = dis;
	}
	
	
	
}
