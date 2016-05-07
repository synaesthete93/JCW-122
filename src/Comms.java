import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.lang.Throwable;


public class Comms {
static Socket clientSocket;
static ServerSocket serverSocket;
Queue<Message> taskList;

	public static void sendMessage(Message thisOne) {
		
	}
	
	public static void receiveMessage(Message thisOne) {
		
	}
	
	public static void main(String[] args) {
		
		try {
			final ExecutorService clientPool = Executors.newFixedThreadPool(10);
			serverSocket = new ServerSocket(60000);
			
			while (true) {
				clientSocket = serverSocket.accept();
				final ObjectInputStream receiver = new ObjectInputStream (clientSocket.getInputStream());
				try {
					((Message) receiver.readObject()).print();
					clientSocket.close();
					serverSocket.close();
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		
		}
	}
	
	public static void sendClientMessage(Message message) throws IOException {
		Socket newSocket = new Socket("2a02:c7d:5275:3200:bce8:455a:6ab5:7b5c",60000);
		final ObjectOutputStream sender = new ObjectOutputStream(newSocket.getOutputStream());
		sender.writeObject(message);
		sender.flush();
		sender.close();
		newSocket.close();
	}
	
}
