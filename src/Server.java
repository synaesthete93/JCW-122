import java.util.ArrayList;

public class Server {

	ArrayList<Item> allItems = new ArrayList<Item>();
	ArrayList<User> allUsers = new ArrayList<User>();
	int lastItemID = 0;
	
	public static void main(String[] args) {
		Server testServer = new Server();
		Comms serverComms = new Comms();
		serverComms.runServer();
		Boolean active = true;
		while (active) {
			if (serverComms.hasMessage(testServer)) {
				Message receivedMessage = serverComms.receiveMessage(testServer);
				receivedMessage.print();
				
			}
		}
	}
}
