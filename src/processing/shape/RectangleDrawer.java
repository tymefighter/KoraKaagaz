package processing.shape;

import java.util.ArrayList;
import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class RectangleDrawer {
	public static ArrayList<Pixel> drawRectangle (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		for(int r = topLeft.r;r <= bottomRight.r;r++) {
			pixels.add(new Pixel(
				new Position(r, topLeft.c),
				new Intensity(intensity)
			));
			
			pixels.add(new Pixel(
				new Position(r, bottomRight.c),
				new Intensity(intensity)
			));
		}
		
		for(int c = topLeft.c + 1;c <= bottomRight.c - 1;c++) {
			pixels.add(new Pixel(
				new Position(topLeft.r, c),
				new Intensity(intensity)
			));
			
			pixels.add(new Pixel(
				new Position(bottomRight.r, c),
				new Intensity(intensity)
			));
		}
		
		return pixels;
	}
	
	public static ArrayList<Pixel> drawRectangleFill (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();

		for(int r = topLeft.r;r <= bottomRight.r;r++) {
			for(int c = topLeft.c;c <= bottomRight.c;c++) {
				pixels.add(new Pixel(
					new Position(r, c),
					new Intensity(intensity)
				));
			}
		}
		
		return pixels;
	}
}
