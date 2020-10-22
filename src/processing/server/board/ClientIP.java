package processing.server.board;

import java.util.*;
import processing.utility.*;

/***
 * This class implements interface IClientDetails
 * 
 * @author Himanshu Jain
 *
 */

public class ClientIP implements IClientIP {
	
	@Override
	public Map<Username, IpAddress> getClientIP() {
		Map<Username, IpAddress> userToIp = new HashMap<Username, IpAddress>();
		return userToIp;
	}
	
}