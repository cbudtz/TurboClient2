package control;

import connection.ConnectionHandler;
import connection.IConnectionHandler;

public class ClientController {

	public void start() {
		//Initiates gameEngine in separate thread
		IGameStateEngine engine = new GameStateEngine();
		Thread engineThread = new Thread(engine);
		engineThread.start();
		//Initiates connectionHandler in separate thread
		IConnectionHandler connector = new ConnectionHandler("localhost",5151);
		Thread connectorThread = new Thread(connector);
		connectorThread.start();
		//adds GameEngine as gameStateListener
		connector.registerConnectionListener(engine);
		//adds connector as KeyPresListener
		engine.registerKeyPressListener(connector);
		
	}

}
