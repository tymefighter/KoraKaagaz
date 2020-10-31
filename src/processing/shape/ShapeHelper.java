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
				new Radius(brushRadius.brushRadius), 
				pixels.get(i).intensity
			);
			
			for(Pixel pixel : magnifiedPixels)
				pixels.add(pixel);
		}
	}
	
	protected static ArrayList<Pixel> removeDuplicates(
		ArrayList<Pixel> pixels
	) {
		Set<Pixel> pixelSet = new HashSet<Pixel>(pixels);
		return new ArrayList<Pixel>(pixelSet);
	}
	
	protected static ArrayList<Pixel> removeIllegalPoints(
		ArrayList<Pixel> pixels,
		Dimension boardDimension
	) {
		ArrayList<Pixel> updatedPixels = new ArrayList<Pixel>();
		
		for(Pixel pixel : pixels) {
			if(
				pixel.position.r >= 0
				&&
				pixel.position.r < boardDimension.numRows
				&&
				pixel.position.c >= 0
				&&
				pixel.position.c < boardDimension.numCols
			)
				updatedPixels.add(new Pixel(pixel));
		}
		
		return updatedPixels;
	}
}
