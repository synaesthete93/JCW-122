import java.io.IOException;

public class Client {
	public static void main(String[] args) {
		try {
		Message tester = new Message();
		Comms.sendClientMessage(tester);
		} catch (IOException e) {
			System.out.println("Something went wrong with the client socket;");
		}
		
		System.exit(0);
	}
}
