package connection;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import dto.GameState;
import dto.KeyPressData;

public class ConnectionHandler implements IConnectionHandler {

	private String host;
	private int port;
	private LinkedList<IConnectionListener> connectionListeners = new LinkedList<>();

	public ConnectionHandler(String host, int port) {
		this.host = host;
		this.port = port;
	}

	@Override
	public void run() {
		// TODO establish connection and spawn KeypressSender and GameStateReceiver
		pingTest();
		
	}

	

	@Override
	public void sendKeyPress(KeyPressData keyPressData) {
		System.out.println("ConnectionHandler - Sending Keypress");
		//TODO ask KeyPress Sender to send keypresses to Server

	}

	@Override
	public void registerConnectionListener(IConnectionListener listener) {
		this.connectionListeners.add(listener);

	}
	/** Dummy test method to simulate incoming gamestate
	 * 
	 */
	private void pingTest() {
		new Timer().scheduleAtFixedRate(new TimerTask() {
			
			@Override
			public void run() {
				System.out.println("Hello from ConnectionHandler! - tickling Listeners!");
				for (IConnectionListener iConnectionListener : connectionListeners) {
					iConnectionListener.receiveGameState(GameState.getDefaultGameState());
				}
			}
		}, 0, 1000);
	}


}
