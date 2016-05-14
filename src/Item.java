import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.io.Serializable;

public class Item implements Serializable {
	private static final long serialVersionUID = 1L;
	public static SimpleDateFormat form = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
	public static String[] categories = {"Electronics", "Music", "Hardware", "Books", "Art", "Furniture", "Miscellaneous"};
	String title;
	String desc;
	String type;
	Date created;
	Date start;
	Date end;
	int reserve;
	int ID;
	ArrayList<Bid> bids = new ArrayList<Bid>();
	
	public Item() {
		this.title = "something";
		this.desc = "This is an item that does whatever items do.";
		this.type = "Miscellaneous";
		this.start = new Date();
		this.end = new Date(this.start.getTime()+90000000);
		this.reserve = 0;
		this.created = new Date();

	}
	
	public Item(String title, String desc, String type, Date start, Date end, int reserve) {
		this.title = title;
		this.desc = desc;
		this.type = type;
		this.start = start;
		this.end = end;
		this.reserve = reserve;
		this.created = new Date();
	}
	
	public Item(String title) {
		this();
		this.title = title;
	}
	
	@Override
	public String toString() {
		return this.title;
	}
}
