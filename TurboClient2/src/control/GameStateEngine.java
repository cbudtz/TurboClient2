package control;

import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

import connection.IConnectionListener;
import connection.IKeyPressListener;
import dto.ConnectionState;
import dto.GameState;
import dto.KeyPressData;

public class GameStateEngine implements IGameStateEngine{

	private volatile ConnectionState connectionState;
	private volatile GameState gameState = GameState.getDefaultGameState();
	private long lastSimTime;
	private volatile LinkedList<IKeyPressListener> keyPressListeners = new LinkedList<>();
	private final long SIMSPEED = 20;

	@Override
	public void run() {
		pingTest();
		lastSimTime = System.currentTimeMillis();
		//Start simulation
		new Timer().scheduleAtFixedRate(new TimerTask() {			
			@Override
			public void run() {
				gameSimulationLoop();				
			}
		}, 0, SIMSPEED);

	}
	/**
	 * Method for registering objects interested in keypresses;
	 */
	@Override
	public void registerKeyPressListener(IKeyPressListener listener) {
		this.keyPressListeners.add(listener);

	}
	//---- Callbacks from Remote simulation ---
	/**
	 * Called when changes in connection occurs.
	 */
	@Override
	public void receiveConnectionState(ConnectionState state) {
		this.connectionState = state;
		//Show connectionstate on gui.
		updateGuiConnectionStatus(state);
		//TODO some logic stopping simulation if connection is lost
	}	

	/**
	 * Called when a new gamestate is received
	 */
	@Override
	public void receiveGameState(GameState state) {
		this.gameState=state;
		//Force update of gameEngine
		System.out.println("GameStateEngine - New gameStaet received - correcting state!");
		gameSimulationLoop();		
	}
	//---- End of Callbacks ---


	/**
	 * Main loop for iterating gameLoop. May be invoked by timerTask or new gamestate
	 */
	private synchronized void gameSimulationLoop() {
		long now = System.currentTimeMillis();
		long timeStep = now - lastSimTime;
		lastSimTime = now;
		//System.out.println("GameEngine: updating gameState! timeStep:" + timeStep);
		//Update GUI TODO
		updateGuiMainPanel();

	}

///---- Gui update methods ---
	private void updateGuiConnectionStatus(ConnectionState state) {
		// TODO some gui thing!		
		System.out.println("GameEngine - Connection status Changed: " + state);

	}



	private void updateGuiMainPanel() {
		// TODO Auto-generated method stub


	}

///--- End of GUI update methods ---
	/** 
	 * Silly function to simulate keypresses
	 */
	private void pingTest() {
		new Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				System.out.println("Hello from GameEngine");
				for (IKeyPressListener iKeyPressListener : keyPressListeners) {
					System.out.println("GameEngine - Sending keypresses to KeypressListener: " + iKeyPressListener);
					iKeyPressListener.sendKeyPress(new KeyPressData());
				}

			}
		}, 0, 2300);
	}



}
