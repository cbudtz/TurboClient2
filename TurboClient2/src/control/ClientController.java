package control;

import connection.ConnectionHandler;
import connection.IConnectionHandler;
import gui.Gui;
import gui.interfaces.IGui;

public class ClientController {
	private final String ec2Server = "52.30.89.247";
	
	public void start() {
		//Initiates gameEngine in separate thread
		IGameStateEngine engine = new GameStateEngine();
		Thread engineThread = new Thread(engine);
		engineThread.start();
		//Initiates connectionHandler in separate thread
		IConnectionHandler connector = new ConnectionHandler(ec2Server,5151);
		Thread connectorThread = new Thread(connector);
		connectorThread.start();
		//adds GameEngine as gameStateListener
		connector.registerConnectionListener(engine);
		//adds connector as KeyPresListener
		engine.registerKeyPressListener(connector);
		
	}

}
