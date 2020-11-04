package processing.visualize;

import java.util.ArrayList;

import processing.shape.CircleDrawer;
import processing.utility.*;

public class VisualizeCircleFill {
	public static void main(String[] args) {
		ArrayList<Pixel> pixels = null;
		Dimension dimension = new Dimension(1000, 1000);
		
		// Devansh Algorithm
		CircleDrawer.setAlgorithmFill(CircleDrawer.AlgorithmFill.DEVANSH);
		pixels = CircleDrawer.drawCircleFill(
			new Position(500, 500),
			new Radius(50),
			new Intensity(0, 255, 0)
		);
		Visualize.visualize(pixels, dimension);
		
		// Mid Point Based Algorithm
		CircleDrawer.setAlgorithmFill(CircleDrawer.AlgorithmFill.MID_POINT_BASED);
		pixels = CircleDrawer.drawCircleFill(
			new Position(500, 500),
			new Radius(50),
			new Intensity(0, 0, 255)
		);
		
		Visualize.visualize(pixels, dimension);
	}
}
