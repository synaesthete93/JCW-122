import java.io.IOException;

public class Client {
	public static void main(String[] args) {
		try {
		Comms.sendClientMessage("fuck");
		} catch (IOException e) {
			System.out.println("Something went wrong with the client socket;");
		}
	}
}
