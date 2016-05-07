import java.io.Serializable;

public class Message implements Serializable {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
String message;
	public Message() {
		this.message = "This is a message.";
	}

	public Message(String text) {
		this.message = text;
	}
	
	public void print() {
		System.out.println(message);
	}
}
