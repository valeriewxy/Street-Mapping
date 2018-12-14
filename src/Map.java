/*
 * Name: Xuanyang Wang, Kehao Guo
 * NetID: xwang145, kguo
 * Project 4
 */

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Map extends JFrame {

	private Canvas canvas;

	public Map(String file, boolean drawMap, boolean drawPath, String src, String des) {
		super("Map");
		canvas = new Canvas(file, drawMap, drawPath, src, des);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		add(canvas);
		pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public static void main(String[] args) {
		boolean drawMap = false;
		boolean drawPath = false;
		String src = null;
		String des = null;
		if (args[1].equals("--show")) {
			if (args.length == 2) {
				drawMap = true;
			} else if (args[2].equals("--directions")) {
				drawMap = drawPath = true;
				src = args[3];
				des = args[4];
			}
		} else if (args[1].equals("--directions")) {
			drawPath = true;
			src = args[2];
			des = args[3];
		}
		Map map = new Map(args[0], drawMap, drawPath, src, des);
		map.setVisible(true);
		if (drawPath && (map.getCanvas().getGraph().NoPath()==false)) {
			map.getCanvas().getGraph().printPath();
		}
	}

}
