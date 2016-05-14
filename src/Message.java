import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

public abstract class Message implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String[] searchTypes = {"Name","Seller","ID","Date"};
String message;
int command;
Socket response;
ObjectInputStream streamer;
ObjectOutputStream returnStream;

public static final int ITEM_LIST = 1;
public static final int LOGIN = 2;
public static final int REGISTER = 3;
public static final int BID = 4;
public static final int SUBMIT_ITEM = 5;
public static final int UPDATE = 6;

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
