/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JComponent;

public class Canvas extends JComponent{
	private Graph graph;
	private String src;
	private String des;
	private boolean drawMap;
	private boolean drawPath;
	
	public Canvas(String file, boolean drawMap, boolean drawPath, String src, String des) {
		graph = new Graph();
		graph.buildGraph(file);
		this.drawMap = drawMap;
		this.drawPath = drawPath;
		if (drawPath) {
			graph.dijkstra(src, des);
		}
	}
	
	public Graph getGraph() {
		return graph;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(new Color(224, 233, 249));
		g.fillRect(0, 0, getWidth(), getHeight());
		if(drawMap) {
			graph.drawGraph(g, getWidth(),getHeight());
		}
		if(drawPath) {
			graph.drawPath(g, getWidth(),getHeight(),src,des);
		}
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(1000, 700);
	}
}
