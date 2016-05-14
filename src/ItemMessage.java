import java.io.Serializable;

public class ItemMessage extends Message implements Serializable {
	private static final long serialVersionUID = 1L;
	Item load;
	
	public ItemMessage() {
		// TODO Auto-generated constructor stub
	}

	public ItemMessage(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	public ItemMessage(Item load, int command) {
		this.load = load;
		this.command = command;
	}

}
