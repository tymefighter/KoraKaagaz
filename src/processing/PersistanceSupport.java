package processing;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import processing.utility.Filepath;

/**
 * PersistanceSupport Class which contains static methods for
 * saving and retrieving board state from the filesystem
 * 
 * @author Ahmed Zaheer Dadarkar
 * @reviewer Rakesh
 */

public class PersistanceSupport {
	
	/**
	 * Stores the provided Board State as a Serialized
	 * String at the provided file location
	 * 
	 * @param boardState The board state object
	 * @param filepath Path where to store the board state
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void storeState (
        BoardState boardState,
        Filepath filepath
    ) throws IOException, UnsupportedEncodingException {
		
		// Serialize the board state to obtain a string
		String boardStateString =
			Serialize.serialize(boardState);
		
		// Create a file corresponding to the path provided
		// if it does not already exist
		File boardFile = new File(filepath.toString());
		if(!boardFile.exists())
			boardFile.createNewFile();
		
		// Write the serialized string to the board state
		// after converting to an array of bytes using the
		// ISO-8859-1 encoding
		Files.write(
			boardFile.toPath(), 
			boardStateString.getBytes("ISO-8859-1")
		);
	}
	
	/**
	 * Loads the Board State from the file containing the
	 * Serialized String corresponding to the board state
	 * 
	 * @param filepath Filepath where the Seerialized String is stored
	 * @return the Board State object reconstructed from the Serialized String
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 * @throws ClassNotFoundException
	 */
    public static BoardState loadState (
        Filepath filepath
    ) throws IOException, UnsupportedEncodingException, ClassNotFoundException {
    	// Get the file corresponding to the path provided
    	File boardFile = new File(filepath.toString());
    	
    	// Read all the bytes from the file and construct the serialized string
    	// using the ISO-8859-1 encoding
    	byte[] boardStateBytes = Files.readAllBytes(boardFile.toPath());
    	String boardStateString = new String(boardStateBytes, "ISO-8859-1");
    	
    	// Get the board state from the serialized string
    	BoardState boardState =
    		(BoardState) Serialize.deSerialize(boardStateString);
    	
    	// Return the constructed board state
    	return boardState;
    }
}
