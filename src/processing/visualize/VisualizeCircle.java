package processing.visualize;

import java.util.ArrayList;

import processing.shape.CircleDrawer;
import processing.utility.*;

public class VisualizeCircle {
	public static void main(String[] args) {
		ArrayList<Pixel> pixels = null;
		Dimension dimension = new Dimension(1000, 1000);
		
		pixels = CircleDrawer.drawCircle(
			new Position(500, 500),
			new Radius(50),
			new Intensity(0, 0, 0)
		);
		
		Visualize.visualize(pixels, dimension);
	}
}
