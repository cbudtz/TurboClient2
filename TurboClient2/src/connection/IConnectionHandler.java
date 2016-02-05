package connection;

import dto.KeyPressData;

public interface IConnectionHandler extends Runnable, IKeyPressListener{
	void registerConnectionListener(IConnectionListener listener);
}
