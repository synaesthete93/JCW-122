public class Client {
	public static void main(String[] args) {
		try {
		Message tester = new SystemMessage("crapper");
		Comms.sendClientMessage(tester);
		} catch (Exception e) {
			 e.printStackTrace();
		 }
		
		System.exit(0);
	}
}
