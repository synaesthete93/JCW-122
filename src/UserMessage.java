import java.io.Serializable;

public class UserMessage extends Message implements Serializable {
	private static final long serialVersionUID = 1L;
	User load;
	
	public UserMessage() {
		// TODO Auto-generated constructor stub
	}

	public UserMessage(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	public UserMessage(User usr, int comm) {
		this.command = comm;
		this.load = usr;
	}
}
