package views;
import javax.swing.DefaultListModel;
import models.Item;
import models.ItemViewCustomRender;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URI;
import java.util.Scanner;

/**
 * A dialog for tracking the price of an item.
 *
 * @author Yoonsik Cheon
 */
@SuppressWarnings("serial")
public class Main extends JFrame {
	//private Item item = new Item();

	/** Default dimension of the dialog. */
	private final static Dimension DEFAULT_SIZE = new Dimension(406, 390);

	/** Special panel to display the watched item. */
	private ItemView itemView;

	/** Message bar to display various messages. */
	private JLabel msgBar = new JLabel(" ");
	
	private	JList <Item> jlist;
	
	private JScrollPane scrollPane;
	
	private DefaultListModel<Item> listModel;

	/** Create a new dialog. */
	public Main() {
		this(DEFAULT_SIZE);
	}

	/** Create a new dialog of the given screen dimension. */
	public Main(Dimension dim) {
		super("Price Watcher");
		setSize(dim);

		configureUI();
		// setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
		// setResizable(false);
		showMessage("Welcome!");
	}


  
	/**
	 * Callback to be invoked when the view-page icon is clicked. Launch a (default)
	 * web browser by supplying the URL of the item.
	 */
	//private void viewPageClicked() {
		//try {
			//Desktop desktop = Desktop.getDesktop();
			//URI oURL = new URI(item.getURL());
			//desktop.browse(oURL);
		//} catch (Exception e) {
		//	e.printStackTrace();
		//}
		//showMessage("item.");
	//}

	/** Configure UI. */
	private void configureUI() {
		setLayout(new BorderLayout());

		createJList();
		add(scrollPane, BorderLayout.CENTER);
		msgBar.setBorder(BorderFactory.createEmptyBorder(10, 16, 10, 0));
		add(msgBar, BorderLayout.SOUTH);
	}


	/** Show briefly the given string in the message bar. */
	private void showMessage(String msg) {
		msgBar.setText(msg);
		new Thread(() -> {
			try {
				Thread.sleep(3 * 1000); // 3 seconds
			} catch (InterruptedException e) {
			}
			if (msg.equals(msgBar.getText())) {
				SwingUtilities.invokeLater(() -> msgBar.setText(" "));
			}
		}).start();
	}
	

	public void createJList() {
	listModel = new DefaultListModel<>();
	//Hard Coded Items to add to ListModel
	Item item1 = new Item("Beats", "ebay.com", "01/02/0121",0, 0, 0, 0);
	Item item2 = new Item("Laptop", "facebook.com", "06/01/1996", 0,0,0,0);

	listModel.addElement(item1);
	listModel.addElement(item2);
	
	
	//Jlist initialized
	jlist = new JList<>(listModel);
	jlist.setCellRenderer(new ItemViewCustomRender());
    scrollPane = (new JScrollPane(jlist));
	}

	public static void main(String[] args) {
		new Main();
	}

}

