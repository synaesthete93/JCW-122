import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;

public class CommThread extends Thread {
	LinkedBlockingQueue<Message> list;
	ServerSocket serverSocket;
	Boolean active = true;
	
	public CommThread(LinkedBlockingQueue<Message> list) {
		this.list = list;
	}
	
	@Override
	public void run() {
		try {
			serverSocket = new ServerSocket(60000);
			
				while (active) {
				try {
					Socket clientSocket = serverSocket.accept(); 
					ObjectInputStream receiver = new ObjectInputStream (clientSocket.getInputStream());
					Message newMessage = ((Message) receiver.readObject());
					newMessage.response = clientSocket;
					newMessage.returnStream = new ObjectOutputStream (clientSocket.getOutputStream());
					list.add(newMessage);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			serverSocket.close();
		} catch (Exception e) {
			if (e instanceof InterruptedException) {
				try {
					serverSocket.close();
				} catch (Exception k) {
					 k.printStackTrace();
				 }
				
				System.err.println("The server has shut down.");
				active = false;
			} else e.printStackTrace();
		}
		
		
	}
	


}
