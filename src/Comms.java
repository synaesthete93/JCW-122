import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.net.ServerSocket;
import java.lang.Throwable;


public class Comms {
static ServerSocket serverSocket;
LinkedBlockingQueue<Message> serverMessages = new LinkedBlockingQueue<Message>();
LinkedBlockingQueue<Message> clientMessages = new LinkedBlockingQueue<Message>();
CommThread myThread;
static String ip;

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
		Comms.ip = Comms.ipInit();
		myThread = new CommThread(serverMessages);
		myThread.start();
	}
	
	public static Message sendClientMessage(Message message) {
		try (Socket newSocket = new Socket(Comms.ip,60000);
			final ObjectOutputStream sender = new ObjectOutputStream(newSocket.getOutputStream());
			final ObjectInputStream receiver = new ObjectInputStream(newSocket.getInputStream()) 
		){
			message.returnStream = sender;
			message.streamer = receiver;
			sender.writeObject(message);
			sender.flush();
			Thread.currentThread().sleep(1000);
			return ((Message) receiver.readObject());
		} catch (Exception e) {
			e.printStackTrace();
			return new SystemMessage("The message return has failed.");
		}
	}
	
	public static void respond(Message m, Message r) {
		try {
			ObjectOutputStream output = new ObjectOutputStream(m.response.getOutputStream());
			output.writeObject(r);
			output.flush();
			m.response.close();
			
		} catch (Exception e) {
			 e.printStackTrace();
		 }
	}
	
	public void shutdown() {
		myThread.interrupt();
	}
	
	public static String ipInit() {
		String ip;
		try {
			URL whatismyip = new URL("http://checkip.amazonaws.com");
			BufferedReader in = new BufferedReader(new InputStreamReader(
		                whatismyip.openStream()));
			ip = in.readLine();
		} catch (Exception e) {
			ip="";
			e.printStackTrace();
		}
		
		return ip;
	}
	
	public class QListener implements EventListener {
		
	}

}
