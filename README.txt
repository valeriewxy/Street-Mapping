CSC172 Project 4
Lab Session: Tue., Thur., 12:30-1:45 PM

Name: Xuanyang Wang

Files:
Canvas.java
Edge.java
Graph.java
ImageProcessor.java (helper class for drawing images)
Map.java
Node.java
Point.java (helper class for enabling graph scaling)
pin.png (the pin icon on the map)

Synopsis:
Using the given data, we constructed the graph ADT with an adjacency list. Besides drawing the graph based on the adj list, we implemented the dijkstra's algorithm to
find the shortest path between two arbitrary intersections with a priority queue.

Runtime Analysis:
*E=edge V=vertex or node
Shortest path: O(|E| ln(|V|))
Plotting map: |V|^2

Extra Credit:
Please consider the dedicated design of the output for extra credits. We figured out a suitable and simplistic design to make the map as beautiful as it can with 
its colors design, while keeping the graph functional and legible. For example, we added an icon of pin as an element of the path drawn in order to show destination and 
source location clearer for better user experience.

Method Signatures:
class Canvas
 	private Graph graph;
 	private String src;
 	private String des;
 	private boolean drawMap;
 	private boolean drawPath;
 	public Graph getGraph()
 	public Canvas(String file, boolean drawMap, boolean drawPath, String src, String des)
 	protected void paintComponent(Graphics g)
 	public Dimension getPreferredSize()
class Edge
 	private Node node1;
 	private Node node2;
 	private double weight;
 	private String id;
 	public Edge(String id, Node node1, Node node2)
 	public double getDistance(Node node1, Node node2)
class Graph
 	private Map<Node,LinkedList<Edge>> adj;
 	private Map<String, Node> vertices;
 	private LinkedList<Node> path;
 	protected static double maxLong = Double.NEGATIVE_INFINITY, maxLat = Double.NEGATIVE_INFINITY;
 	protected static double minLong = Double.POSITIVE_INFINITY, minLat = Double.POSITIVE_INFINITY;
 	private boolean noPath;
 	public Graph()
 	public boolean addVertex(Node ver)
 	public void addEdge(String id, Node node1, Node node2)
 	public void dijkstra(String start, String end)
 	private class Compare implements Comparator<Node>
 	public void buildGraph(String file)
 	public void drawGraph(Graphics g, double w, double h)
 	public void drawPath(Graphics g, double w, double h, String str1, String str2)
 	public double round(double value) 
 	public void printPath()
class ImageProcessor
 	public static Image loadImage(String filename)
class Map
 	private Canvas canvas;
 	public Map(String file, boolean drawMap, boolean drawPath, String src, String des)
	public static void main(String[] args)
class Node
 	private String id;
 	private boolean visited;
 	private Node parent;
 	private double latitude;
 	private double longitude;
 	private double dis;
 	public Node(String id, boolean visited, double latitude, double longitude)
class Point
	private double x;
	private double y;
	public Point(double latitude, double longitude, double w, double h)