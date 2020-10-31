package processing;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import processing.utility.Filepath;

public class PersistanceSupport {
	
	public static void storeState (
        BoardState boardState,
        Filepath filepath
    ) throws IOException {
		
		String boardStateString = 
			Serialize.serialize(boardState);
		
		File boardFile = new File(filepath.toString());
		if(!boardFile.exists())
			boardFile.createNewFile();
		
		FileWriter boardFileWriter = new FileWriter(boardFile);
		
		boardFileWriter.write(boardStateString);
		boardFileWriter.close();
	}
	
    public static BoardState loadState (
        Filepath filepath
    ) {
    	File boardFile = new File(filepath.toString());
    	
    	FileReader boardFileReader = new FileReader(boardFile);
    	return null;
    }
}
