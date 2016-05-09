
public class Server {
	
	public static void main(String[] args) {
		Server testServer = new Server();
		Comms serverComms = new Comms();
		serverComms.runServer();
		Boolean active = true;
		while (active) {
			if (serverComms.hasMessage(testServer)) {
				Message receivedMessage = serverComms.receiveMessage(testServer);
				if (receivedMessage.message.equals("shutdown")) {
					serverComms.shutdown();
					active = false;
				}
				else receivedMessage.print();
			}
		}
	}
}
