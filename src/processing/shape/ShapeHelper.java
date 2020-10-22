package processing.shape;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class ShapeHelper {
	protected static void magnifyPixels(
		ArrayList<Pixel> pixels,
		BrushRadius brushRadius
	) {
		int numInitPixels = pixels.size();
		for(int i = 0;i < numInitPixels;i++) {
			ArrayList<Pixel> magnifiedPixels = CircleDrawer.drawCircle(
				pixels.get(i).position, 
				new Radius(brushRadius.radius), 
				pixels.get(i).intensity
			);
			
			for(Pixel pixel : magnifiedPixels)
				pixels.add(pixel);
		}
	}
	
	protected static void removeDuplicates(
		ArrayList<Pixel> pixels
	) {
		Set<Pixel> pixelSet = new HashSet<Pixel>(pixels);
		pixels.clear();
		pixels.addAll(pixelSet);
	}
}
