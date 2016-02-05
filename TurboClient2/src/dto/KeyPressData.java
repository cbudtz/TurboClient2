package dto;
import java.io.Serializable;
import java.util.ArrayList;

public class KeyPressData implements Serializable{
	/**
	 * 
	 */
	public static final long serialVersionUID = -4355118281068967115L;
	private final long sessionID; //To distinguish players - socket might chance and more players might use the same socket
	private final ArrayList<Character> keys;
	
	public KeyPressData(long sessionID, ArrayList<Character> keys) {
		super();
		this.sessionID = sessionID;
		this.keys = keys;
	}

	public KeyPressData() {
		keys = new ArrayList<>();
		sessionID = Long.MIN_VALUE;
	}

	public long getSessionID() {
		return sessionID;
	}

	public ArrayList<Character> getKeys() {
		return keys;
	}
	
	
	
	

}
