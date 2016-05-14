import java.io.Serializable;
import java.util.ArrayList;

public class ItemListMessage extends Message implements Serializable {
	private static final long serialVersionUID = 1L;
	ArrayList<Item> load;
	
	public ItemListMessage() {
		// TODO Auto-generated constructor stub
	}

	public ItemListMessage(String text) {
		super(text);
		// TODO Auto-generated constructor stub
	}
	
	public ItemListMessage(ArrayList<Item> list, int comm) {
		this.command = comm;
		this.load = list;
	}

}
