package connection;

import dto.ConnectionState;
import dto.GameState;

public interface IConnectionListener {
	void receiveConnectionState(ConnectionState state);
	void receiveGameState(GameState state);

}
