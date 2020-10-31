package processing;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;

import processing.utility.Filepath;

public class PersistanceSupport {
	
	public static void storeState (
        BoardState boardState,
        Filepath filepath
    ) throws IOException, UnsupportedEncodingException {
		
		String boardStateString = "";
			Serialize.serialize(boardState);
		
		File boardFile = new File(filepath.toString());
		if(!boardFile.exists())
			boardFile.createNewFile();
		
		Files.write(
			boardFile.toPath(), 
			boardStateString.getBytes("ISO-8859-1")
		);
	}
	
    public static BoardState loadState (
        Filepath filepath
    ) throws IOException, UnsupportedEncodingException, ClassNotFoundException {
    	File boardFile = new File(filepath.toString());
    	
    	byte[] boardStateBytes = Files.readAllBytes(boardFile.toPath());
    	String boardStateString = new String(boardStateBytes, "ISO-8859-1");
    	
    	BoardState boardState =
    		(BoardState) Serialize.deSerialize(boardStateString);
    	
    	return boardState;
    }
}
