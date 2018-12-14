/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Graph {
	private Map<Node,LinkedList<Edge>> adj;
	private Map<String, Node> vertices;
	private LinkedList<Node> path;
	private boolean noPath;
	protected static double maxLong = Double.NEGATIVE_INFINITY, maxLat = Double.NEGATIVE_INFINITY;
	protected static double minLong = Double.POSITIVE_INFINITY, minLat = Double.POSITIVE_INFINITY;
	
	
	public Graph() {
		adj = new HashMap<>();
		vertices = new HashMap<>();
		path = new LinkedList<>();
		noPath = false;
	}
	
	public boolean NoPath() {
		return noPath;
	}
	
	public boolean addVertex(Node ver) {
		if (adj.containsKey(ver)) 
			return false;
		adj.put(ver, new LinkedList<Edge>());
		return true;
	}
	
	public void addEdge(String id, Node node1, Node node2) {
		Edge edge1 = new Edge(id, node1, node2);
		Edge edge2 = new Edge(id, node2, node1);
		adj.get(node1).add(edge1);
		adj.get(node2).add(edge2);
	}
	
	public void dijkstra(String start, String end) {
		Queue<Node> pq = new PriorityQueue<>(20, new Compare());
		Node src = vertices.get(start);
		src.setDis(0.0);
		src.setVisited(true);
		pq.add(src);
		Node temp = null;
		while (!pq.isEmpty()) {
			temp = pq.poll();
			temp.setVisited(true);
			if (temp.equals(vertices.get(end))) {
				break;
			} else {
				for (Edge adjacent : adj.get(temp)) {
					if (adjacent.getNode2().isVisited() == false) {
						if ((temp.getDis() + adjacent.getWeight()) < adjacent.getNode2().getDis()) {
							adjacent.getNode2().setDis(temp.getDis() + adjacent.getWeight());
							adjacent.getNode2().setParent(temp);
						}
						if (!pq.contains(adjacent.getNode2()))
							pq.add(adjacent.getNode2());
					}
				}
			}
		}
		if (vertices.get(end).getDis() == Double.POSITIVE_INFINITY) {
			System.out.println("NO PATH AVAILABLE");
			noPath = true;
			return;
		}
		Node current = vertices.get(end);
		while (current != null) {
			path.addFirst(current);
			current = current.getParent();
		}
	}
	
	private class Compare implements Comparator<Node> {
		@Override
		public int compare(Node a, Node b) {
			if (a.getDis()<b.getDis())
				return -1;
			else if (a.getDis()>b.getDis())
				return 1;
			return 0;
		}
	}
	
	public void buildGraph(String file) {
		String str = "";
		try(BufferedReader br = new BufferedReader(new FileReader(new File(file)))) {
			//initiate readers
			while ((str = br.readLine()) != null) {//reads one line
				String[] data = str.split("\\s+");
				if (data[0].equals("i")) {
					if (Double.parseDouble(data[2])>maxLat)
						maxLat = Double.parseDouble(data[2]);
					else if (Double.parseDouble(data[2])<minLat)
						minLat = Double.parseDouble(data[2]);
					if (Double.parseDouble(data[3])>maxLong)
						maxLong = Double.parseDouble(data[3]);
					else if (Double.parseDouble(data[3])<minLong)
						minLong = Double.parseDouble(data[3]);
					Node temp = new Node(data[1],false,Double.parseDouble(data[2]),Double.parseDouble(data[3]));
					addVertex(temp);
					vertices.put(temp.getId(), temp);
					
				} else if (data[0].equals("r")) {
					addEdge(data[1],vertices.get(data[2]),vertices.get(data[3]));
				}
			}
			//close the readers
			br.close();
		} catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public void drawGraph(Graphics g, double w, double h) {
		g.setColor(Color.LIGHT_GRAY);
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(2));
		for (Node n1 : adj.keySet()) {
			Point p1 = new Point(n1.getLatitude(),n1.getLongitude(),w,h);
			for (Edge n2 : adj.get(n1)) {
				Point p2 = new Point(n2.getNode2().getLatitude(), n2.getNode2().getLongitude(),w,h);
				g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
			}
		}
	}
	
	public void drawPath(Graphics g, double w, double h, String str1, String str2) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setStroke(new BasicStroke(4));
		Image pin = ImageProcessor.loadImage("pin.png");
		
		g.setColor(Color.GRAY);
		if (noPath == false) {
			g.drawString("Distance: "+Double.toString(round(path.get(path.size()-1).getDis()))+" mi", 20, (int) (h-20));
		} else {
			g.drawString("NO PATH AVAILABLE", 20, (int) (h-20));
			return;
		}
		
		Point temp1 = null, temp2 = null;
		for (int i=0; i<path.size()-1; i++) {
			Point p1 = new Point(path.get(i).getLatitude(),path.get(i).getLongitude(),w,h);
			Point p2 = new Point(path.get(i+1).getLatitude(),path.get(i+1).getLongitude(),w,h);
			if (i==0) {
				temp1 = p1;
			} else if (i==path.size()-2) {
				temp2 = p2;
			}
			g.setColor(new Color(169, 148, 206));
			g2.drawLine((int)p1.getX(), (int)p1.getY(), (int)p2.getX(), (int)p2.getY());
		}
		g.drawImage(pin, (int)(temp1.getX()-pin.getWidth(null)/2), (int)(temp1.getY()-pin.getHeight(null)),null);
		g.drawImage(pin, (int)(temp2.getX()-pin.getWidth(null)/2), (int)(temp2.getY()-pin.getHeight(null)),null);
		
	}
	
	//round the total distance
	public double round(double value) {
	    double scale = Math.pow(10, 1);
	    return Math.round(value * scale) / scale;
	}
	
	public void printPath() {
		System.out.println("Intersections Travelled: ");
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i).getId());
		}
		System.out.println();
		System.out.println("Distance Travelled: ");
		System.out.println(round(path.get(path.size() - 1).getDis())+" mi");
	}

}
