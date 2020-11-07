package processing;

import java.util.*;
import networking.ICommunicator;
import processing.handlers.*;
import processing.boardobject.*;
import processing.utility.*;

/**
* This class contains all the info of the current board.
*
* @author Himanshu Jain
*/

public class ClientBoardState {
	
	//maps is an object of the BoardState containing both the maps
	public static BoardState maps = new BoardState();
	
	//port Number of the Board Server
	public static Port portNumber;
	
	//public static Map <Identifier identifier, IChanges handler>
	
	public static BoardId boardId;
	
	//undo and redo stacks
	public static ArrayList <BoardObject> undoStack = new ArrayList <BoardObject>();
	public static ArrayList <BoardObject> redoStack = new ArrayList <BoardObject>();
	
	/**
	 * Will remain empty on the client side, as there is no need to know all the users to the client.
	 * Server will use this users List to maintain the list of all the users connected to this Board.
	 */
	public static ArrayList <String> users = new ArrayList <String>();
	
	//to store the selected object
	private static PriorityQueueObject selectedObject;
	
	//to store the username and userId
	public static Username username;
	public static UserId userId;
	public static IpAddress userIP;
	public static Port userPort;
	
	public static IpAddress serverIp;
	
	public static BrushRadius brushSize;
	
	public static ICommunicator communicator;
	
	public static void start() {
		ClientBoardState.communicator.subscribeForNotifications("ProcessingObject", new ObjectHandler());
		ClientBoardState.communicator.subscribeForNotifications("ProcessingBoardState", new BoardStateHandler());
		ClientBoardState.communicator.subscribeForNotifications("ProcessingServerPort", new PortHandler());
		ClientBoardState.communicator.subscribeForNotifications("ProcessingBoardId", new BoardIdHandler());
		while(ClientBoardState.boardId != null) {
			// wait until we receive a boardId from the server
		}
	}
	
	public static synchronized void setSelectedObject(PriorityQueueObject selectedObject) {
		ClientBoardState.selectedObject = selectedObject;
	}
	
	public static synchronized PriorityQueueObject getSelectedObject() {
		return ClientBoardState.selectedObject;
	}
	
	public static Dimension boardDimension;
	
	public static Port serverPort;
	
}