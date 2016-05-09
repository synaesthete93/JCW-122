import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.lang.Throwable;


public class Comms {
static Socket clientSocket;
static ServerSocket serverSocket;
LinkedBlockingQueue<Message> serverMessages = new LinkedBlockingQueue<Message>();
LinkedBlockingQueue<Message> clientMessages = new LinkedBlockingQueue<Message>();
CommThread myThread;

	public static void sendMessage(Message thisOne, Object caller) {
		
	}
	
	public Message receiveMessage(Object caller) {
		if (caller instanceof Client) {
			return clientMessages.poll();
		} else if (caller instanceof Server) {
			return serverMessages.poll();
		} else return new SystemMessage("Method was called from an unknown class object.");
	}
	
	public Boolean hasMessage(Object caller) {
		if (caller instanceof Server) return ! serverMessages.isEmpty();
		else if (caller instanceof Client) return ! clientMessages.isEmpty();
		else return false;
	}
	
	public void runServer() {
		myThread = new CommThread(serverMessages);
		myThread.start();
	}
	
	public static void sendClientMessage(Message message) throws IOException {
		Socket newSocket = new Socket("2a02:c7d:5275:3200:9193:efbf:89ad:2a3",60000);
		final ObjectOutputStream sender = new ObjectOutputStream(newSocket.getOutputStream());
		sender.writeObject(message);
		sender.flush();
		sender.close();
		newSocket.close();
	}
	
	public void shutdown() {
		myThread.interrupt();
	}

}
