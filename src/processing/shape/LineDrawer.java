package processing.shape;

import java.util.ArrayList;
import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class LineDrawer {
	
	public enum Algorithm {DDA, BRESENHAM};
	
	protected static ArrayList<Pixel> drawSegment(
		Position pointA,
	    Position pointB,
	    Intensity intensity,
	    Algorithm algorithm
	) {
		if(algorithm == Algorithm.DDA)
			return digitalDifferentialAnalyser(
				pointA,
				pointB,
				intensity
			);
		else
			return bresenhamLineDraw(
				pointA,
				pointB,
				intensity
			);
	}
	
	private static ArrayList<Pixel> digitalDifferentialAnalyser(
		Position pointA,
	    Position pointB,
	    Intensity intensity
	) {
		// Initialize arraylist of pixels
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		// Compute difference in row and column coordinates
		int dr = pointB.r - pointA.r;
		int dc = pointB.c - pointA.c;
		
		// Compute number of steps to be taken
		int steps = Math.max(Math.abs(dr), Math.abs(dc));
		
		double rInc = dr / (double) steps; // Increment in row direction
		double cInc = dc / (double) steps; // Increment in col direction
		
		// Intial Coordinates
		double r = (double) pointA.r, c = (double) pointB.c;
		
		// We iterate one more than the number of steps to take
		// since we must also cover the initial coordinate
		for(int i = 0; i <= steps; i++, r += rInc, c += cInc) {
			// Round the double position to integer
			Position pos = new Position(
				(int) Math.round(r), 
				(int) Math.round(c)
			);
			
			// Add the computed pixel
			pixels.add(new Pixel(
				pos,
				new Intensity(intensity)
			));
		}
		
		// Return the computed pixel arraylist
		return pixels;
	}
	

	private static ArrayList<Pixel> bresenhamLineDraw(
		Position pointA,
	    Position pointB,
	    Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		return pixels;
	}
}
