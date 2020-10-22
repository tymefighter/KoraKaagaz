package processing.shape;

import java.util.ArrayList;
import processing.utility.*;

/**
*
* @author Ahmed Zaheer Dadarkar
*/

public class TriangleDrawer {
	public static ArrayList<Pixel> drawTriangle (
		Position vertA,
        Position vertB,
        Position vertC,
        Intensity intensity
	) {
		ArrayList<Pixel> pixels = new ArrayList<Pixel>();
		
		pixels.addAll(LineDrawer.drawSegment(vertA, vertB, intensity));
		pixels.addAll(LineDrawer.drawSegment(vertB, vertC, intensity));
		pixels.addAll(LineDrawer.drawSegment(vertC, vertA, intensity));
		
		return pixels;
	}
}
