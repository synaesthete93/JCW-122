import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.Group;

import java.util.ArrayList;

public class GUI {
	public static void centralize(Component window) {
		Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - window.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - window.getHeight()) / 2);
	    window.setLocation(x, y);
	}
	
	public static abstract class Field {
		Component field;
		JLabel label;
		
		public JLabel getLabel() {
			return this.label;
		}
		
		public Component getField(){
			return this.field;
		}
	}
	
	public static class TextField extends Field {
		
		
		public  TextField(String labelText, String fieldText, Boolean password, String toolText) {
			this.label = new JLabel(labelText+":");
			if (password) field = new JPasswordField("Password");
			else field = new JTextField(fieldText);
			field.setSize(150, 20);
			((JTextField)field).addFocusListener(new TextClearer((JTextField)field));
			((JTextField)field).setToolTipText(toolText);
		}
		
		
		
		
	}
	
	public static class CatField extends Field {
		public CatField() {
			this.label = new JLabel("Category:");
			this.field = new JComboBox<String>(Item.categories);
		}
	}
	
	public static class FormWindow extends JFrame {
		ArrayList<Field> fields = new ArrayList<Field>();
		ArrayList<Group> vGroups = new ArrayList<Group>();
		JButton[] buttons = new JButton[4];
		JPanel buttonsA, buttonsB;
		GroupLayout layout;
		
		public FormWindow(String title,int x, int y) {
			this.setTitle(title);
			this.setSize(x, y);
			layout = new GroupLayout(this.getContentPane());
			this.setLayout(layout);
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			for (int i = 0;i<4;i++) {
				buttons[i] = new JButton();
				buttons[i].setVisible(false);
			}
			
			buttonsA = new JPanel();
			buttonsA.add(buttons[0]);
			buttonsA.add(buttons[1]);
			
			buttonsB = new JPanel();
			buttonsB.add(buttons[2]);
			buttonsB.add(buttons[3]);
		}
		
		public void addField(Field toAdd) {
			this.fields.add(toAdd);
			vGroups.add(layout.createParallelGroup(Alignment.CENTER));
			vGroups.get(vGroups.size()-1).addComponent(toAdd.label);
			vGroups.get(vGroups.size()-1).addComponent(toAdd.field);
			
		}
		
		public void setButton(int i, String text) {
			this.buttons[i-1].setText(text);
			this.buttons[i-1].setVisible(true);
		}
		
		public void initialize() {
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			
			
			//First column
			GroupLayout.ParallelGroup labelGroup = layout.createParallelGroup(Alignment.TRAILING);
			
			for (int i = 0;i<fields.size();i++) {
				labelGroup.addComponent(fields.get(i).getLabel());
			}
			
			//adding buttons on last row
			labelGroup.addComponent(buttonsA);
			
			
			
			//Second column
			GroupLayout.ParallelGroup fieldGroup = layout.createParallelGroup(Alignment.TRAILING);
			for (int i = 0;i<fields.size();i++) {
				fieldGroup.addComponent(fields.get(i).getField());
			}
			
			//adding buttons to last row
			fieldGroup.addComponent(buttonsB);
			
			
			// creating the horizontal axis
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			hGroup.addGroup(labelGroup);
			hGroup.addGroup(fieldGroup);
			layout.setHorizontalGroup(hGroup);
			
			
			
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			
			
			for (int i = 0;i<vGroups.size();i++) {
				vGroup.addGroup(vGroups.get(i));
			}
			
			GroupLayout.ParallelGroup bGroup = layout.createParallelGroup(Alignment.CENTER);
			
			bGroup.addComponent(buttonsA);
			
			bGroup.addComponent(buttonsB);
			vGroup.addGroup(bGroup);
			
			layout.setVerticalGroup(vGroup);
			
		}
		
		public void centralize() {
			GUI.centralize(this);
		}
		
	}
	
	private static class TextClearer implements FocusListener {
		JTextField target;
		
		private TextClearer(JTextField applyTo) {
			this.target = applyTo;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			target.setText("");
			
		}

		@Override
		public void focusLost(FocusEvent e) {
			
		}
		
	}

	public static class SearchPanel extends JPanel {
		GUI.TextSearchTab tab1;
		JPanel tab2;
		JPanel tab3;
		JComboBox category;
		JButton catBtn;
		JButton allBtn;
		JTabbedPane searcher;
		
		public SearchPanel() {
			this.setSize(600, 150);			
			this.tabInit();
			
		}
		
		public void tabInit() {
			searcher = new JTabbedPane();
			
			tab1 = new GUI.TextSearchTab();
			this.catInit();
			this.allInit();
			
			
			searcher.addTab("Text search", tab1);
			searcher.addTab("Search by category", tab2);
			searcher.addTab("Display all auctions", tab3);
			
			this.add(searcher);
		}
		
		public void allInit() {
			tab3 = new JPanel();
			allBtn = new JButton("Display all auctions");
			allBtn.setFont(new Font("large",1,20));
		
			tab3.add(allBtn);
			
			FlowLayout center = new FlowLayout();
			center.setVgap(27);
			tab3.setLayout(center);
		}
		public void catInit() {
			this.tab2 = new JPanel();
			
			
			tab2.add(new JLabel("Select your category:"));
			this.category = new JComboBox<String>(Item.categories);
			tab2.add(this.category);
			this.catBtn = new JButton("Search");
			tab2.add(catBtn);
			
			FlowLayout center = new FlowLayout();
			center.setVgap(33);
			tab2.setLayout(center);
			
		}
	
	}
	
	public static class TextSearchTab extends JPanel {
		JLabel label;
		JTextField searchField;
		JComboBox type;
		JButton btn;
		GroupLayout layout;
		
		public TextSearchTab() {
			this.label = new JLabel("Search by:");
			this.searchField = new JTextField(30);
			this.type = new JComboBox<String>(Message.searchTypes);
			this.initialize();
			this.setSize(600, 120);
		}
		
		
		private void initialize() {
			this.layout = new GroupLayout(this);
			layout.setAutoCreateGaps(true);
			layout.setAutoCreateContainerGaps(true);
			this.btn = new JButton("Search");
			
			GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup();
			GroupLayout.ParallelGroup h1 = layout.createParallelGroup(Alignment.CENTER);
			GroupLayout.ParallelGroup h2 = layout.createParallelGroup(Alignment.TRAILING);
			h1.addComponent(this.label);
			h1.addComponent(this.type);
			h2.addComponent(this.searchField);
			h2.addComponent(this.btn);
			
			hGroup.addGroup(h1);
			hGroup.addGroup(h2);
			layout.setHorizontalGroup(hGroup);
			
			
			GroupLayout.SequentialGroup vGroup = layout.createSequentialGroup();
			GroupLayout.ParallelGroup v1 = layout.createParallelGroup(Alignment.BASELINE);
			v1.addComponent(this.type);
			v1.addComponent(this.searchField);
			vGroup.addComponent(this.label);
			vGroup.addGroup(v1);
			vGroup.addComponent(this.btn);
			layout.setVerticalGroup(vGroup);
			
			this.setLayout(layout);
		}
	}

	
	public static class ItemList extends JList<Item> {
		
		public ItemList(Item[] lister) {
			super(lister);
			this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.setLayoutOrientation(JList.VERTICAL);
			JScrollPane listScroller = new JScrollPane(this);
			listScroller.setPreferredSize(new Dimension(200,80));
			this.setSize(200, 80);
		}
		
		public ItemList(ArrayList<Item> arList) {
			this((Item[]) arList.toArray());	
		}
	}
}
	




/*  GroupLayout loginLayout = new GroupLayout(loginFrame.getContentPane());
		loginFrame.setLayout(loginLayout);
		loginLayout.setAutoCreateGaps(true);
		loginLayout.setAutoCreateContainerGaps(true);
		
		
		loginLayout.setVerticalGroup(
				loginLayout.createSequentialGroup()
					.addGroup(loginLayout.createParallelGroup()
							.addComponent(usrTag)
							.addComponent(usr))
					.addGroup(loginLayout.createParallelGroup()
							.addComponent(pwdTag)
							.addComponent(pwd)));
		
		loginLayout.setHorizontalGroup(
				loginLayout.createSequentialGroup()
					.addGroup(loginLayout.createParallelGroup()
							.addComponent(usrTag)
							.addComponent(pwdTag))
					.addGroup(loginLayout.createParallelGroup()
							.addComponent(usr)
							.addComponent(pwd)));
		*/


/* 
	*/




