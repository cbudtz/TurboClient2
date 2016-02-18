package connection;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import dto.KeyPressData;

public class KeyPressSender implements Runnable {

	BlockingQueue<KeyPressData> sendQueue = new ArrayBlockingQueue<>(10);
	private Socket socket;
	private ObjectOutputStream outStream;

	public KeyPressSender(Socket socket) {
		this.socket = socket;
		try {
			outStream = new ObjectOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true){
			try { 
				KeyPressData keys = sendQueue.take();
				outStream.writeObject(keys);
				System.out.println("KeyPressSender->" + "Wrote:" + keys);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

	public void sendKeyPress(KeyPressData keyPressData) {
		try {
			sendQueue.put(keyPressData);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
