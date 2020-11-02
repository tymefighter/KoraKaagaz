package processing.shape;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import processing.utility.*;

/**
 * Helper Functions for Shape Construction
 *
 * @author Ahmed Zaheer Dadarkar
 * @reviewer Rakesh Kumar
 */

public class ShapeHelper {
	
	/**
	 * Method for performing operations to magnify and clean
	 * up the shape border after the shape border was drawn,
	 * hence the mention of "post" in the name.
	 * 
	 * @param pixels The pixels of the drawn shape
	 * @param brushRadius Radius of the brush chosen
	 * @param boardDimension Dimension of the board
	 * @return the post processed pixels
	 */
	protected static ArrayList<Pixel> postDrawProcessing(
		ArrayList<Pixel> pixels,
		BrushRadius brushRadius,
		Dimension boardDimension
	) {
		// Magnify each pixels to the radius of the brush,
		// this gives an effect of the shape to be drawing
		// using a brush of radius given by brushRadius
		pixels = magnifyPixels(pixels, brushRadius);
		
		// Remove any duplicate pixels if present
		pixels = removeDuplicates(pixels);
		
		// Remove any illegal points present, like points
		// with negative coordinates, or coordinates outside
		// the board itself
		pixels = removeIllegalPoints(
			pixels,
			boardDimension
		);
		
		// Return the post processed pixels
		return pixels;
	}
	
	protected static ArrayList<Pixel> postFillProcessing(
		ArrayList<Pixel> pixels,
		Dimension boardDimension
	) {
		pixels = removeDuplicates(pixels);
		pixels = removeIllegalPoints(
			pixels,
			boardDimension
		);
		
		return pixels;
	}
	
	private static ArrayList<Pixel> magnifyPixels(
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
	
	private static ArrayList<Pixel> removeDuplicates(
		ArrayList<Pixel> pixels
	) {
		Set<Pixel> pixelSet = new HashSet<Pixel>(pixels);
		return new ArrayList<Pixel>(pixelSet);
	}
	
	private static ArrayList<Pixel> removeIllegalPoints(
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
