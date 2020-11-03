package processing.shape;

import processing.boardobject.BoardObject;
import processing.utility.*;

/**
 * Static methods for constructing board objects from shapes and
 * performing the necessary updates to the global board state of
 * the current board
 *
 * @author Ahmed Zaheer Dadarkar
 * @reviewer Rakesh Kumar
 */

public class BoardObjectBuilder {
	/**
	 * Constructs a Circle based on the center, radius and intensity
	 * of each pixel, performs clean up and then makes necessary
	 * changes to the board state
	 * 
	 * @param center Center of the circle to be constructed
	 * @param radius Radius of the circle to be constructed
	 * @param intensity Intensity of each pixel of the circle
	 * @return the circle as a Board Object
	 */
    public static BoardObject drawCircle (
        Position center,
        Radius radius,
        Intensity intensity
    ) {
    	return null;
    }
    
    /**
	 * Constructs a Filled Circle based on the center,
	 * radius and intensity of each pixel, performs clean up
	 * and then makes necessary changes to the board state
	 * 
	 * @param center Center of the filled circle to be constructed
	 * @param radius Radius of the filled circle to be constructed
	 * @param intensity Intensity of each pixel of the filled circle
	 * @return the filled circle as a Board Object
	 */
    public static BoardObject drawCircleFill (
        Position center,
        Radius radius,
        Intensity intensity
    ) {
    	return null;
    }
    
    /**
     * Constructs an Axis Aligned Rectangle based on the top left
     * and bottom right coordinates
     * 
     * @param topLeft Coordinate of the top left pixel of the rectangle
     * @param bottomRight Coordinate of the bottom right pixel of the rectangle
     * @param intensity Intensity of each pixel of the rectangle
     * @return the rectangle as a Board Object
     */
    public static BoardObject drawRectangle (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
    ) {
    	return null;
    }
    
    /**
     * Constructs an Axis Aligned Filled Rectangle based on the top left
     * and bottom right coordinates
     * 
     * @param topLeft Coordinate of the top left pixel of the filled rectangle
     * @param bottomRight Coordinate of the bottom right pixel of the filled rectangle
     * @param intensity Intensity of each pixel of the filled rectangle
     * @return the filled rectangle as a Board Object
     */
    public static BoardObject drawRectangleFill (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
    ) {
    	return null;
    }
    
    /**
     * Constructs a Triangle based on the three vertices provided
     * 
     * @param vertA First vertex of the triangle
     * @param vertB Second vertex of the triangle
     * @param vertC Third vertex of the triangle
     * @param intensity Intensity of each pixel of the triangle
     * @return the triangle as a Board Object 
     */
    public static BoardObject drawTriangle (
        Position vertA,
        Position vertB,
        Position vertC,
        Intensity intensity
    ) {
    	return null;
    }
    
    /**
     * Constructs a Line Segment based on the two endpoints of the
     * line segment which are provided as input
     * 
     * @param pointA First end point of the line segment
     * @param pointB Second end point of the line segment
     * @param intensity Intensity of each pixel of the segment
     * @return the line segment as a Board Object 
     */
    public static BoardObject drawSegment (
        Position pointA,
        Position pointB,
        Intensity intensity
    ) {
    	return null;
    }
}
