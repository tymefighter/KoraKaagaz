package processing.shape;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import processing.utility.*;

/**
 *
 * @author Ahmed Zaheer Dadarkar
 * @reviewer Rakesh Kumar
 */

public class ShapeHelper {
	
	protected static ArrayList<Pixel> postDrawProcessing(
		ArrayList<Pixel> pixels,
		BrushRadius brushRadius,
		Dimension boardDimension
	) {
		ArrayList<Pixel> magPixels = magnifyPixels(pixels, brushRadius);
		ArrayList<Pixel> magPixelWithoutDup = removeDuplicates(magPixels);
		ArrayList<Pixel> finalPixels = removeIllegalPoints(
			magPixelWithoutDup,
			boardDimension
		);
		
		return finalPixels;
	}
	
	protected static ArrayList<Pixel> magnifyPixels(
		ArrayList<Pixel> pixels,
		BrushRadius brushRadius
	) {
		ArrayList<Pixel> updatedPixels = new ArrayList<Pixel>();

		for(Pixel pixel : pixels) {
			
			ArrayList<Pixel> magnifiedPixels = CircleDrawer.drawCircleFill(
				pixel.position, 
				new Radius(brushRadius.brushRadius), 
				pixel.intensity
			);
			
			for(Pixel magPixel : magnifiedPixels)
				updatedPixels.add(magPixel);
		}
		
		return updatedPixels;
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
