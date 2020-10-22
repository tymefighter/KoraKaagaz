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
		}
		
		return pixels;
	}
	
	public static ArrayList<Pixel> drawRectangleFill (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		return pixels;
	}
}
