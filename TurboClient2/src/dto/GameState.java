package dto;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;

public class GameState implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4380710172237756601L;
	private PlayerData playerData = new PlayerData();
	private ArrayList<PlayerData> otherPlayersData = new ArrayList<>();
	
	public ArrayList<PlayerData> getOtherPlayersData() {
		return otherPlayersData;
	}
	public PlayerData getOwnData(){
		return playerData;
		
	}

	public void setShips(ArrayList<PlayerData> ships) {
		this.otherPlayersData = ships;
	}
	
	public class PlayerData implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = -8566225108680632406L;
		Point position = new Point(0, 0);
		Point velocity = new Point(0, 0);
		
		float rotation = 0;
		float angularSpeed = 0;
		
		String name = "";
		int lives = 5;
		int health = 100;
		
		
	}

	public static GameState getDefaultGameState() {
		// TODO Auto-generated method stub
		return new GameState();
	}

}
