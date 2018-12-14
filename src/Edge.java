/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

public class Edge {
	private Node node1;
	private Node node2;
	private double weight;
	private String id;
	
	public Edge(String id, Node node1, Node node2) {
		this.id = id;
		this.node1 = node1;
		this.node2 = node2;
		weight = getDistance(node1, node2);
	}

	//getters
	public Node getNode1() {
		return node1;
	}

	public Node getNode2() {
		return node2;
	}
	
	public String getId() {
		return id;
	}
	
	public double getWeight() {
		return weight;
	}
	
	public static double getDistance(Node node1, Node node2) {
		final double r = 3959;
		double φ1 = Math.toRadians(node1.getLatitude());
		double φ2 = Math.toRadians(node2.getLatitude());
		double Δφ = Math.toRadians(node1.getLatitude()-node2.getLatitude());
		double Δλ = Math.toRadians(node1.getLongitude()-node2.getLongitude());
		double a = Math.sin(Δφ/2) * Math.sin(Δφ/2) + Math.cos(φ1) * Math.cos(φ2) * Math.sin(Δλ/2) * Math.sin(Δλ/2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return r*c;
	}
	
	

	
}
