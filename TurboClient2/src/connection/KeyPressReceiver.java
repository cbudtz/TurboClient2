package connection;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

import dto.GameState;

public class KeyPressReceiver implements Runnable {

	private Socket socket;
	private ConnectionHandler connectionHandler;
	private ObjectInputStream inStream;

	public KeyPressReceiver(Socket socket, ConnectionHandler connectionHandler) {
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
