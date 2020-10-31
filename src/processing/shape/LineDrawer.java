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
	
	/**
	 * Draws a Line Segment from pointA to pointB
	 * 
	 * @param pointA First end point of the line segment
	 * @param pointB Second end point of the line segment
	 * @param intensity Intensity of each point on the segment
	 * @return The arraylist of pixels of the line segment 
	 */
	protected static ArrayList<Pixel> drawSegment(
		Position pointA,
	    Position pointB,
	    Intensity intensity
	) {
		// If DDA algorithm is to be used, then use it
		if(lineAlgorithm == Algorithm.DDA)
			return digitalDifferentialAnalyser(
				pointA,
				pointB,
				intensity
			);
		else // Else if Bresenham algorithm is to be used, then use it
			return bresenhamLineDraw(
				pointA,
				pointB,
				intensity
			);
	}
	
	/**
	 * Digital Differential Analser Algorithm
	 * 
	 * This algorithm constructs a line segment between two given
	 * points. It approximates the continuous line segment on the
	 * discrete surface (bitmap).
	 * 
	 * Algorithm Link:
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/
	 * Digital_differential_analyzer_(graphics_algorithm)</a>
	 * 
	 * @param pointA First end point of the line segment
	 * @param pointB Second end point of the line segment
	 * @param intensity Intensity of each point on the segment
	 * @return The arraylist of pixels of the line segment 
	 */
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
	
	/**
	 * Bresenham's Line Drawing Algorithm
	 * 
	 * This algorithm constructs a line segment between two given
	 * points. It approximates the continuous line segment on the
	 * discrete surface (bitmap). It does not perform any floating
	 * point calculations, and optimally selects points which are
	 * close to the actual line.
	 * 
	 * Algorithm Link:
	 * 
	 * @see <a href="https://en.wikipedia.org/wiki/Bresenham%27s_line_algorithm</a>
	 * 
	 * @param pointA First end point of the line segment
	 * @param pointB Second end point of the line segment
	 * @param intensity Intensity of each point on the segment
	 * @return The arraylist of pixels of the line segment 
	 */
	private static ArrayList<Pixel> bresenhamLineDraw(
		Position pointA,
	    Position pointB,
	    Intensity intensity
	) {
		int drAbs = Math.abs(pointB.r - pointA.r); // Differnece along row axis
		int dcAbs = Math.abs(pointB.c - pointA.c); // Difference along col axis
		
		// Pixel arraylist which must be returned
		ArrayList<Pixel> pixels = null;
		
		// If difference along col axis is more, we can sample more points
		// along col axis (in increments of 1) compared to row axis
		if(dcAbs >= drAbs) {
			if(pointB.c < pointA.c)
				swapPosition(pointA, pointB);
			
			// Use drawLineLow to draw a "low" line, i.e. line with slope
			// having absolute value between 0 and 1
			pixels = drawLineLow(pointA, pointB, intensity);
		}
		else {
			// If difference along row axis is more, we can sample more
			// points along row axis compared to col axis
			if(pointB.r < pointA.r)
				swapPosition(pointA, pointB);
			
			// Use drawLineHigh to draw a "high" line, i.e. line with slope
			// having absolute value greater than 1
			pixels = drawLineHigh(pointA, pointB, intensity);
		}
		
		// Return the computed pixel arraylist
		return pixels;
	}
	
	/** Swap member values in a {@link Position} object */
	private static void swapPosition(
		Position a,
		Position b
	) {
		int temp;
		
		// Swap row values
		temp = a.r;
		a.r = b.r;
		b.r = temp;
		
		// swap col values
		temp = a.c;
		a.c = b.c;
		b.c = temp;
	}
	
	/**
	 * Draws Line which have the absolute value of slope less than
	 * or equal to 1, i.e. absolute change in column is more than 
	 * or equal to the absolute change in row. Also, the start
	 * point must have a lower or equal col than end point
	 * 
	 * @param start Start point of the line
	 * @param end End point of the line
	 * @param intensity Intensity of each point in the line
	 * @return The arraylist of pixels of the line segment 
	 */
	private static ArrayList<Pixel> drawLineLow(
		Position start,
		Position end,
		Intensity intensity
	) {
		// Pixel arraylist which must be returned
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		// Add start point
		pixels.add(new Pixel(
			new Position(start), 
			new Intensity(intensity)
		));

		int dr = end.r - start.r; // Difference in row
		int dc = end.c - start.c; // Difference in col
		
		// Decision parameter is used to decide whether to increment
		// (or decrement) the row for the next iteration
		int decisionParam;
		
		// Value to be added to row on an iteration when decision
		// parameter indicates it to be added
		int rInc = 1;
		
		// If change in row is less than 0, then our line is heading
		// from a higher row value to a lower row value, so our increment
		// in row is actually negative
		if(dr < 0) {
			dr = -dr;
			rInc = -1;
		}
		
		// Compute initial decision parameter
		decisionParam = 2 * dr - dc;
		int prevRow = start.r; // Starting point's row value

		// Start from the 1st point (0-based) since we have
		// already taken the 0th point
		for(int c = start.c + 1;c <= end.c;c++) {
			
			// Compute position assuming row remains the same
			Position pos = new Position(prevRow, c);

			// If decision parameter is greater than zero, we increment
			// row value and update decision parameter else we only
			// update the decision parameter
			if(decisionParam > 0) {
				pos.r += rInc;
				decisionParam += 2 * (dr - dc);
			}
			else
				decisionParam += 2 * dr;

			// Add the pixel to our arraylist
			pixels.add(new Pixel(
				pos,
				new Intensity(intensity)
			));
		}

		// Return the computed pixel arraylist
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
