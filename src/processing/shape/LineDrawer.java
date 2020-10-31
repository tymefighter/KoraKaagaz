package processing.shape;

import java.util.ArrayList;
import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class LineDrawer {
	
	/** Algorithm Choice Enum */
	public enum Algorithm {DDA, BRESENHAM};
	
	/** Variable storing the algorithm which must be used */
	private static Algorithm lineAlgorithm =
		Algorithm.BRESENHAM; 
	
	/** Sets the algorithm to be used */
	protected static void setAlgorithm(Algorithm algorithm) {
		lineAlgorithm = algorithm;
	}
	
	protected static ArrayList<Pixel> drawSegment(
		Position pointA,
	    Position pointB,
	    Intensity intensity
	) {
		if(lineAlgorithm == Algorithm.DDA)
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
		
		// Initial Coordinates
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
		int drAbs = Math.abs(pointB.r - pointA.r);
		int dcAbs = Math.abs(pointB.c - pointA.c);
		
		ArrayList<Pixel> pixels = null;
		
		if(dcAbs >= drAbs) {
			if(pointB.c < pointA.c)
				swapPosition(pointA, pointB);
			
			pixels = drawLineLow(pointA, pointB, intensity);
		}
		else {
			if(pointB.r < pointA.r)
				swapPosition(pointA, pointB);
			
			pixels = drawLineHigh(pointA, pointB, intensity);
		}
		
		return pixels;
	}
	
	private static void swapPosition(
		Position a,
		Position b
	) {
		int temp;
		
		temp = a.r;
		a.r = b.r;
		b.r = temp;
		
		temp = a.c;
		a.c = b.c;
		b.c = temp;
	}
	
	private static ArrayList<Pixel> drawLineLow(
		Position start,
		Position end,
		Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		pixels.add(new Pixel(
			new Position(start), 
			new Intensity(intensity)
		));

		int dr = end.r - start.r;
		int dc = end.c - start.c;
		int decisionParam, rInc = 1;
		
		if(dr < 0) {
			dr = -dr;
			rInc = -1;
		}
		
		decisionParam = 2 * dr - dc;
		int prevRow = start.r;

		for(int c = start.c + 1;c <= end.c;c++) {
			Position pos = new Position(prevRow, c);

			if(decisionParam > 0) {
				pos.r += rInc;
				decisionParam += 2 * (dr - dc);
			}
			else
				decisionParam += 2 * dr;

			pixels.add(new Pixel(
				pos,
				new Intensity(intensity)
			));
		}

		return pixels;
	}
	
	private static ArrayList<Pixel> drawLineHigh(
		Position start,
		Position end,
		Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		pixels.add(new Pixel(
			new Position(start), 
			new Intensity(intensity)
		));

		int dr = end.r - start.r;
		int dc = end.c - start.c;
		int decisionParam, cInc = 1;
		
		if(dc < 0) {
			dc = -dc;
			cInc = -1;
		}
		
		decisionParam = 2 * dc - dr;
		int prevCol = start.c;

		for(int r = start.r + 1;r <= end.r;r++) {
			Position pos = new Position(r, prevCol);

			if(decisionParam > 0) {
				pos.c += cInc;
				decisionParam += 2 * (dc - dr);
			}
			else
				decisionParam += 2 * dc;

			pixels.add(new Pixel(
				pos,
				new Intensity(intensity)
			));
		}

		return pixels;
	}
}
