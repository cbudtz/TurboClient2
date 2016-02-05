package control;

import connection.IConnectionListener;
import connection.IKeyPressListener;

public interface IGameStateEngine extends Runnable, IConnectionListener{
	void registerKeyPressListener(IKeyPressListener listener);

}
