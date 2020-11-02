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
	 * @param pixels The pixels of the drawn shape border
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
	
	/**
	 * Method for performing operations to clean up the filled shape
	 * after the filled shape was drawn, hence the mention of "post"
	 * in the name.
	 * 
	 * An important difference between current function and {@link #postDrawProcessing}
	 * is that our current function performs post processing on a
	 * filled shape, which is completely dense and visible, but
	 * the other function performs on borders of shapes, which
	 * need to be magnified since single pixel width is not quite
	 * visibles.
	 * 
	 * @param pixels The pixels of the drawn filled shape
	 * @param boardDimension Dimension of the board
	 * @return Dimension of the boards
	 */
	protected static ArrayList<Pixel> postFillProcessing(
		ArrayList<Pixel> pixels,
		Dimension boardDimension
	) {
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
	
	/**
	 * Magnifies each pixel present in the input arraylist
	 * by constructing a filled circle of the provided
	 * brush radius about it (i.e. keeping that pixel as the
	 * center)
	 * 
	 * @param pixels Arraylist of pixels whose pixels are to be modified
	 * @param brushRadius Radius of circle drawn about each pixel (gives
	 * the feel of being drawn using a brush of the provided radius, hence
	 * the name brushRadius)
	 * @return The magnified arraylist of pixels
	 */
	private static ArrayList<Pixel> magnifyPixels(
		ArrayList<Pixel> pixels,
		BrushRadius brushRadius
	) {
		// Initialize updated arraylist
		ArrayList<Pixel> updatedPixels = new ArrayList<Pixel>();

		// Iterate over each pixel in the input arraylist and
		// "magnify" it (i.e. draw a filled circle about it)
		for(Pixel pixel : pixels) {
			
			// Draw a filled circle about pixel with radius given by
			// the brush radius
			ArrayList<Pixel> magnifiedPixels = CircleDrawer.drawCircleFill(
				pixel.position, 
				new Radius(brushRadius.brushRadius), 
				pixel.intensity
			);
			
			// Place each of the pixel corresponding to the filled circle
			// into our updated arraylist
			for(Pixel magPixel : magnifiedPixels)
				updatedPixels.add(magPixel);
		}
		
		// Return the updated arraylist of pixels
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
