package processing.shape;

import java.util.ArrayList;

import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class CircleDrawer {
	
	/** Algorithm for Circle Boundary */
	public enum AlgorithmCircle {BRESENHAM};
	
	/** Algorithm for Circle Fill */
	public enum AlgorithmFill {DEVANSH};
	
	/** Variable storing the algorithm for Circle Boundary */
	private static AlgorithmCircle circleAlgorithm =
		AlgorithmCircle.BRESENHAM;
	
	/** Variable storing the algorithm for Circle Fill */
	private static AlgorithmFill fillAlgorithm =
		AlgorithmFill.DEVANSH;
	
	/** Sets the algorithm for Circle Boundary to be used */
	protected static void setAlgorithmCircle(AlgorithmCircle algorithm) {
		circleAlgorithm = algorithm;
	}
	
	/** Sets the algorithm for Circle Boundary to be used */
	protected static void setAlgorithmFill(AlgorithmFill algorithm) {
		fillAlgorithm = algorithm;
	}
	
	protected static ArrayList<Pixel> drawCircle(
		Position center,
        Radius radius,
        Intensity intensity
    ) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		return pixels;
	}
	
	protected static ArrayList<Pixel> drawCircleFill(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		return devanshCircleFill(center, radius, intensity);
	}
	
	private static ArrayList<Pixel> devanshCircleFill(
		Position center,
        Radius radius,
        Intensity intensity
	) {
		
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		int rad = (int)radius.radius;
		int radSquare = rad * rad;
		
		for(int r = center.r - rad;r <= center.r + rad;r++) {
			for(int c = center.c - rad;c <= center.c + rad;c++) {
				if(square(r - center.r) + square(c - center.c) <= radSquare) {
					pixels.add(new Pixel(
						new Position(r, c),
						new Intensity(intensity)
					));
				}
			}
		}
		
		return pixels;
	}
	
	private static int square(int x) {
		return x * x;
	}
}
