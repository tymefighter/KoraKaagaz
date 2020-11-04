package processing.visualize;

import java.util.ArrayList;

import processing.shape.CircleDrawer;
import processing.shape.ShapeHelper;
import processing.utility.*;

public class VisualizeCircleThick {
	public static void main(String[] args) {
		ArrayList<Pixel> pixels = null;
		Dimension dimension = new Dimension(1000, 1000);
		
		pixels = CircleDrawer.drawCircle(
			new Position(500, 500),
			new Radius(50),
			new Intensity(0, 0, 0)
		);
		
		pixels = ShapeHelper.postDrawProcessing(
			pixels,
			new BrushRadius(2),
			dimension
		);
		
		Visualize.visualize(pixels, dimension);
	}
}
