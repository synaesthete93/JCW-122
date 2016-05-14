import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;

public class Client {
	public static Item[] testList = {new Item(),new Item("Shovel"),new Item("Dildo"),new Item("Tiger blood"),new Item("Irish potato"),new Item("Couch potato")};
	
	public static void main(String[] args) { 
		Message tester = new SystemMessage("Cunt");
		Comms.sendClientMessage(tester);
	}
	
	public void sendText(String text) {
		try {
			Message tester = new SystemMessage(text);
			Comms.sendClientMessage(tester);
			} catch (Exception e) {
				 e.printStackTrace();
			 }
		System.exit(0);
	}
	
	public void initializeLogin() {
		GUI.FormWindow loginPage = new GUI.FormWindow("Login", 300, 150);
		
		GUI.TextField usrField = new GUI.TextField("Username", "Enter your username here.", false, "This is the unique user ID you registered with");
		GUI.TextField pwdField = new GUI.TextField("Password", "", true, "This is the password you registered with");
		
		loginPage.addField(usrField);
		loginPage.addField(pwdField);
		loginPage.initialize();
		
		loginPage.setButton(4, "Login");
		loginPage.setButton(3, "Register");
		loginPage.setButton(2, "Cancel");
		
		loginPage.centralize();
		loginPage.setResizable(false);
		loginPage.setVisible(true);
	}
	
	public void initializeRegister() {
		GUI.FormWindow regPage = new GUI.FormWindow("Register new account", 400, 250);
		
		GUI.TextField usrRegField = new GUI.TextField("Username", "Please insert your desired username", false, "If the username already exists, you will be prompted to pick a different one.");
		GUI.TextField pwdRegField = new GUI.TextField("Password", "", true, "Enter your desired password");
		GUI.TextField pwd2Field = new GUI.TextField("Re-enter password", "", true, "The two passwords must match");
		GUI.TextField nameField = new GUI.TextField("First name", "Please enter your real first name", false, "Your first name is your given name, e.g. John, Lucy, etc.");
		GUI.TextField surnameField = new GUI.TextField("Last name", "Please enter your real last name", false, "Your last name is your family name");
		
		regPage.addField(nameField);
		regPage.addField(surnameField);
		regPage.addField(usrRegField);
		regPage.addField(pwdRegField);
		regPage.addField(pwd2Field);
		regPage.initialize();
		
		regPage.setButton(4, "Register");
		regPage.setButton(2, "Back");
		
		regPage.centralize();
		regPage.setResizable(false);
		regPage.setVisible(true);
	}
	
	public void initializeNewItem(){
		GUI.FormWindow newItem = new GUI.FormWindow("Submit a new item for sale", 400, 400);
		
		GUI.TextField titleField = new GUI.TextField("Item title", "", false, "Choose an appropriate title for the auction (usually the name of the item)");
		GUI.TextField descriptionField = new GUI.TextField("Item description", "Provide a short description of the item", false, "Try to be accurate when describing the item");
		GUI.Field typeField = new GUI.CatField();
		GUI.TextField startField = new GUI.TextField("Auction start time", "When do you want the auction to start?", false, "Required format: dd/MM/yy HH:mm:ss");
		GUI.TextField endField = new GUI.TextField("Auction end time", "When do you want the auction to end?", false, "Required format: dd/MM/yy HH:mm:ss");
		GUI.TextField reserveField = new GUI.TextField("Reserve price", "The lowest price you can agree to", false, "If the auction ends and there are no bids higher than this, the auction will fail.");
		
		
		newItem.addField(titleField);
		newItem.addField(descriptionField);
		newItem.addField(typeField);
		newItem.addField(startField);
		newItem.addField(endField);
		newItem.addField(reserveField);
		newItem.initialize();
		
		newItem.setButton(4, "Submit");
		newItem.setButton(2, "Cancel");
		
		newItem.setVisible(true);
		newItem.centralize();
	}

	public void initializeMain () {
		JFrame mainFrame = new JFrame("Auction House");
		mainFrame.setSize(450, 600);
		
		BorderLayout mainLayout = new BorderLayout();
		mainFrame.setLayout(mainLayout);
		
		GUI.SearchPanel searchPanel = new GUI.SearchPanel();
		mainFrame.add(searchPanel, BorderLayout.PAGE_START);
		
		
		JList<Item> myList = new GUI.ItemList(testList);
		mainFrame.add(myList, BorderLayout.CENTER);
		
		
		GUI.centralize(mainFrame);
		mainFrame.setVisible(true);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
