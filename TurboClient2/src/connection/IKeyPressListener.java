package connection;

import dto.KeyPressData;

public interface IKeyPressListener {
	void sendKeyPress(KeyPressData keyPressData);
}
