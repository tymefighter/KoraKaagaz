package processing.visualize;

import java.util.ArrayList;

import processing.shape.LineDrawer;
import processing.shape.ShapeHelper;
import processing.utility.*;

public class VisualizeLine {
	public static void main(String[] args) {
		ArrayList<Pixel> pixels = null;
		Dimension dimension = new Dimension(1000, 1000);
		
		LineDrawer.setAlgorithm(LineDrawer.Algorithm.DDA);
		pixels = LineDrawer.drawSegment(
			new Position(0, 0),
			new Position(700, 300),
			new Intensity(0, 255, 0)
		);
		
		pixels = ShapeHelper.postDrawProcessing(
			pixels,
			new BrushRadius(1),
			dimension
		);
		
		Visualize.visualize(pixels, dimension);
		
		
		LineDrawer.setAlgorithm(LineDrawer.Algorithm.BRESENHAM);
		pixels = LineDrawer.drawSegment(
			new Position(0, 0),
			new Position(700, 300),
			new Intensity(0, 0, 255)
		);
		
		pixels = ShapeHelper.postDrawProcessing(
			pixels,
			new BrushRadius(1),
			dimension
		);
		
		Visualize.visualize(pixels, dimension);
	}
}
