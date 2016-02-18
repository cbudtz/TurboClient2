package dto;

import java.awt.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import dto.GameState.PlayerData;

public class GameState implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5701679688155439523L;
	public static final long TESTSESSIONID = 0;
	private ConcurrentMap<Long, PlayerData> players = new ConcurrentHashMap<>();
	
	public static GameState getDefaultGameState() {
		GameState gameState = new GameState();
		PlayerData defaultPlayer = gameState.new PlayerData();
		defaultPlayer.sessionID=TESTSESSIONID;
		defaultPlayer.name= "TestPlayer1";
		gameState.putPlayer(defaultPlayer);
		return gameState;
	}
	public void putPlayer(PlayerData defaultPlayer) {
		players.put(defaultPlayer.sessionID,defaultPlayer);
		
	}
	/**
	 * Private class to store State for Each ship
	 */
	
	public class PlayerData implements Serializable{
		private static final long serialVersionUID = -7466632888539732015L;
		public Point position = new Point(0, 0);
		public Point velocity = new Point(0, 0);
		public float rotation = 0;
		public float angularSpeed = 0;
		
		public Point acceleration = new Point(0,0);
		public long sessionID;		
		//Player specific data
		public String name = "";
		public int lives = 5;
		public int health = 100;
		public ArrayList<String> menuItems;
		
		
	}
	public ConcurrentMap<Long,PlayerData> getPlayers() {
		return players;
		
	}



}
