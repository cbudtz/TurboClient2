package connection;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import dto.GameState;
import dto.KeyPressData;

public class ConnectionHandler implements IConnectionHandler {

	private String host;
	private int port;
	private LinkedList<IConnectionListener> connectionListeners = new LinkedList<>();
	private Socket socket;
	private KeyPressSender sender;
	private Thread senderThread;
	private KeyPressReceiver receiver;
	private Thread receiverThread;

	public ConnectionHandler(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			socket = new Socket(host, port);
			log("Connection Established");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void log(String string) {
		System.out.println("ConnectionHandler ->" + string);
		
	}

	@Override
	public void run() {
		// TODO establish connection and spawn KeypressSender and GameStateReceiver
		//Spawn KeypressSender in Own Thread 
		sender = new KeyPressSender(socket);
		 senderThread = new Thread(sender);
		 senderThread.start();
		 
		 receiver = new KeyPressReceiver(socket, this);
		 receiverThread = new Thread(receiver);
		 receiverThread.start();
		//pingTest();
		
	}

	

	@Override
	public void sendKeyPress(KeyPressData keyPressData) {
		System.out.println("ConnectionHandler - Sending Keypress");
		if (sender !=null)sender.sendKeyPress(keyPressData);

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

	public void receiveGameState(GameState gameState) {
		log("Received gameState" + gameState);
		for (IConnectionListener iConnectionListener : connectionListeners) {
			iConnectionListener.receiveGameState(gameState);
		}
		
	}


}
