package processing.shape;

import processing.utility.*;

// This functions in `BoardObjectBuilder` class
// are responsible for computing the pixels
// from the given parameters, updating
// the board state maps, inserting into
// the list of objects in the board state
// and returning the built board object to the
// caller.
public class BoardObjectBuilder {
    // Construct a circle based on the
    // center, radius and color
    public static BoardObject drawCircle (
        Position center,
        Radius radius,
        Intensity intensity
    ) {}
    
    // Construct a rectangle based on the
    // top left, bottom right positions and
    // the color
    public static BoardObject drawRectangle (
        Position topLeft,
        Position bottomRight,
        Intensity intensity
    ) {}
    
    // Construct a triangle based on three
    // verticex coordinates and the color
    public static BoardObject drawTriangle (
        Position vertA,
        Position vertB,
        Position vertC,
        Intensity intensity
    ) {}
    
    // Construct a line segment based on end
    // point coordinates of the segment 
    // and the color
    public static BoardObject drawSegment (
        Position pointA,
        Position pointB,
        Intensity intensity
    ) {}
}
