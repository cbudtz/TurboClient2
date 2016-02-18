package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import dto.GameState;
import dto.GameState.PlayerData;

public class GameStateReceiver implements Runnable {

	private Socket socket;
	private ConnectionHandler connectionHandler;
	private ObjectInputStream inStream;

	public GameStateReceiver(Socket socket, ConnectionHandler connectionHandler) {
		this.socket = socket;
		try {
			this.inStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.connectionHandler = connectionHandler;
	}

	@Override
	public void run() {
		while (true) {
			try {
				GameState gameState = (GameState)inStream.readObject();
				for (PlayerData p:gameState.getPlayers().values()){
					System.out.println(p.name + " " +p.health);
				}
				connectionHandler.receiveGameState(gameState);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}

}
