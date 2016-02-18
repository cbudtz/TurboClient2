package control;

import java.awt.event.KeyEvent;

import connection.IConnectionListener;
import connection.IKeyPressListener;

public interface IGameStateEngine extends Runnable, IConnectionListener{
	void registerKeyPressListener(IKeyPressListener listener); //Where to propagate keyEvents

	void keyPressed(KeyEvent e); //Notify gameEngine that key is pressed!

}
