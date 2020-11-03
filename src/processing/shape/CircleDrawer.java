package processing.shape;

import java.util.ArrayList;

import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class CircleDrawer {
	
	/** Algorithm for Circle Boundary */
	public enum AlgorithmCircle {MID_POINT};
	
	/** Algorithm for Circle Fill */
	public enum AlgorithmFill {MID_POINT_BASED, DEVANSH};
	
	/** Variable storing the algorithm for Circle Boundary */
	private static AlgorithmCircle circleAlgorithm =
		AlgorithmCircle.MID_POINT;
	
	/** Variable storing the algorithm for Circle Fill */
	private static AlgorithmFill fillAlgorithm =
		AlgorithmFill.MID_POINT_BASED;
	
	/** Sets the algorithm for Circle Boundary to be used */
	protected static void setAlgorithmCircle(AlgorithmCircle algorithm) {
		circleAlgorithm = algorithm;
	}
	
	/** Sets the algorithm for Circle Boundary to be used */
	protected static void setAlgorithmFill(AlgorithmFill algorithm) {
		fillAlgorithm = algorithm;
	}
	
	/**
	 * Draws a Circle based on the center and radius provided using
	 * the selected algorithm
	 * 
	 * @param center Center of the circle
	 * @param radius Radius of the circle
	 * @param intensity Intensity of each pixel of the circle
	 * @return the arraylist of pixels of the circle
	 */
	protected static ArrayList<Pixel> drawCircle(
		Position center,
        Radius radius,
        Intensity intensity
    ) {
		ArrayList<Pixel> pixels = null;
		
		switch(circleAlgorithm) {
			case MID_POINT:
			default:
				pixels = midPointCircleDraw(center, radius, intensity);
		}
		
		return pixels;
	}
	
	/**
	 * Draws a Filled Circle based on the center and radius provided
	 * using the selected algorithm
	 * 
	 * @param center Center of the filled circle
	 * @param radius Radius of the filled circle
	 * @param intensity Intensity of each pixel of the filled circle
	 * @return the arraylist of pixels of the filled circle
	 */
	protected static ArrayList<Pixel> drawCircleFill(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		ArrayList<Pixel> pixels = null;
		switch(fillAlgorithm) {
			case DEVANSH:
				pixels = devanshCircleFill(center, radius, intensity);
				break;
			
			case MID_POINT_BASED:
			default:
				pixels = midPointBasedCircleFill(center, radius, intensity);
		}

		return pixels;
	}
	
	private static ArrayList<Pixel> midPointCircleDraw(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		// Initialize arraylist of pixels
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		int rad = (int) Math.round(radius.radius);
		Position pos = new Position(0, rad);
		int decisionParam = 1 - rad;
		
		addSymmetricPoints(pixels, pos, intensity, center);

		while(pos.c > pos.r) {
			
			if(decisionParam < 0)
				decisionParam += 2 * pos.r + 3;
			else {
				decisionParam += 2 * pos.r - 2 * pos.c + 5;
				pos.c --;
			}
			
			pos.r ++;
			addSymmetricPoints(pixels, pos, intensity, center);
		}
		
		return pixels;
	}
	
	private static void addSymmetricPoints(
		ArrayList<Pixel> pixels,
		Position pos,
		Intensity intensity,
		Position center
	) {
		Position[] allSymmetricPos = new Position[] {
			new Position(pos.r, pos.c),
			new Position(pos.c, pos.r),
			new Position(pos.c, -pos.r),
			new Position(pos.r, -pos.c),
			new Position(-pos.r, -pos.c),
			new Position(-pos.c, -pos.r),
			new Position(-pos.c, pos.r),
			new Position(-pos.r, pos.c)
		};
		
		for(Position symmetricPos : allSymmetricPos) {
			pixels.add(new Pixel(
				new Position(
					symmetricPos.r + center.r, 
					symmetricPos.c + center.c
				),
				new Intensity(intensity)
			));
		}
	}
	
	private static ArrayList<Pixel> midPointBasedCircleFill(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		// Initialize arraylist of pixels
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		int rad = (int) Math.round(radius.radius);
		Position pos = new Position(0, rad);
		int decisionParam = 1 - rad;
		
		fillSymmetric(pixels, pos, intensity, center);

		while(pos.c > pos.r) {
			
			if(decisionParam < 0)
				decisionParam += 2 * pos.r + 3;
			else {
				decisionParam += 2 * pos.r - 2 * pos.c + 5;
				pos.c --;
			}
			
			pos.r ++;
			fillSymmetric(pixels, pos, intensity, center);
		}
		
		return pixels;
	}
	
	private static void fillSymmetric(
		ArrayList<Pixel> pixels,
		Position pos,
		Intensity intensity,
		Position center
	) {
		Position[] allFillSymmetricPos = new Position[] {
			new Position(pos.r, pos.c),
			new Position(pos.c, pos.r),
			new Position(pos.c, -pos.r),
			new Position(pos.r, -pos.c)
		};
		
		for(Position symmetricFillPos : allFillSymmetricPos) {
			for(int r = -symmetricFillPos.r;r <= symmetricFillPos.r;r++) {
				pixels.add(new Pixel(
					new Position(
						r + center.r,
						symmetricFillPos.c + center.c
					),
					new Intensity(intensity)
				));
			}
		}
	}
	
	/**
	 * Devansh Circle Filling Algorithm constructs a filled circle
	 * given the center and radius of the circle
	 * 
	 * @param center Center of the filled circle
	 * @param radius Radius of the filled circle
	 * @param intensity Intensity of each point in the filled circle
	 * @return arraylist of pixels of the filled circle
	 */
	private static ArrayList<Pixel> devanshCircleFill(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		// Initialize arraylist of pixels
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		// Convert radius to integer by rounding
		int rad = (int) Math.round(radius.radius);
		
		// Compute square of radius
		int radSquare = rad * rad;
		
		// Go over every pixel of the smallest square that encompasses
		// the circle (i.e. a square of side equal to the diameter of
		// the circle) and then if the point lies on or inside the circle
		// place it in our arraylist of pixels
		for(int r = center.r - rad;r <= center.r + rad;r++) {
			for(int c = center.c - rad;c <= center.c + rad;c++) {
				
				// If the point lies on or inside the circle, then place it
				// in our arraylist of pixels
				if(square(r - center.r) + square(c - center.c) <= radSquare) {
					pixels.add(new Pixel(
						new Position(r, c),
						new Intensity(intensity)
					));
				}
			}
		}
		
		// Return the computed pixel arraylist
		return pixels;
	}
	
	/** Function to square its input */
	private static int square(int x) {
		return x * x;
	}
}
