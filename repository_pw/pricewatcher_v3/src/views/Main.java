package views;
import models.Item;

import javax.swing.*;
import javax.swing.JButton;
import javax.swing.event.MenuEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URI;
import java.util.Scanner;
import javax.swing.JMenuItem;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

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
	
	private JToolBar toolBar;
	
	private JPopupMenu popUpMenu;
	
	
	//private JMenuBar menuBar;
	
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
		jlist.setVisibleRowCount(2);
		createMenu();
		createJPopupMenu();
		mouseListener();
		toolBar = createJToolBar("ToolBar");
		add(toolBar, BorderLayout.NORTH);
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
	Item item1 = new Item("Beats", "www.apple.com", "01/02/0121",0, 200.00, 200.00, 250.00);
	Item item2 = new Item("Laptop", "www.newegg.com", "06/01/1996", 0,999.99,999.99,899.99);
	listModel.addElement(item1);
	listModel.addElement(item2);
	jlist = new JList<>(listModel);
	jlist.setCellRenderer(new ItemViewCustomRender());
    scrollPane = (new JScrollPane(jlist));
	}
	
		    
	public void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu app, item;
		JMenuItem exit;
		JMenuItem checkPrices, addItem;
		app = new JMenu("App");
		item = new JMenu("Item");
		exit = new JMenuItem("Exit");
		checkPrices = new JMenuItem("Check Prices");
		addItem = new JMenuItem("Add item");
		app.setMnemonic(KeyEvent.VK_A);
		item.setMnemonic(KeyEvent.VK_I);;
		exit.setMnemonic(KeyEvent.VK_E);
		menuBar.add(app);
		menuBar.add(item);
		app.add(exit);
		item.add(checkPrices);
		item.add(addItem);
		exit.addActionListener((event) -> this.exitButtonClicked(event));
		checkPrices.addActionListener((event)->this.refreshButtonClicked(event));
		addItem.addActionListener((event) -> this.addButtonClicked(event));
		setJMenuBar(menuBar);	
	}
		    
    private void createJPopupMenu() {
    	JPopupMenu popMenu = new JPopupMenu() ;
    	JMenuItem remove, edit, refresh;
    	remove = new JMenuItem("Remove");
    	edit = new JMenuItem("Edit");
    	refresh = new JMenuItem("Refresh");
       	remove.addActionListener((event) -> this.deleteButtonClicked(event));
       	edit.addActionListener((event)-> this.editButtonClicked(event));
       	refresh.addActionListener((event)-> this.singleItemRefresh(event));
       	popMenu.add(remove);
       	popMenu.add(refresh);
       	popMenu.add(edit);
       	popUpMenu = popMenu;
    }
		    
    
    private JToolBar createJToolBar(String name) {
    	JToolBar tBar = new JToolBar(name);
    	JButton add, edit, delete, refresh;
    	add = new JButton("Add");
    	delete = new JButton("Remove");
    	edit = new JButton("Edit");
    	refresh = new JButton("Refresh");
    	add.addActionListener((event) -> this.addButtonClicked(event));
    	edit.addActionListener((event) -> this.editButtonClicked(event));
    	delete.addActionListener((event)-> this.deleteButtonClicked(event));
    	refresh.addActionListener((event)->this.refreshButtonClicked(event));
    	tBar.add(edit);
    	tBar.add(delete);
    	tBar.add(add);
    	tBar.add(refresh);
    	return tBar;
    			    }
    
	    
    private void addButtonClicked(ActionEvent event) {
    	String itemName;
    	String itemURL;
    	
    	itemName = JOptionPane.showInputDialog("Enter the item's name.");
    	itemURL = JOptionPane.showInputDialog("Enter the item's URL.");
        Item item3 = new Item(itemName, itemURL);
        listModel.addElement(item3);
    }
		    
    private void refreshButtonClicked(ActionEvent event) {
        for (int i = 0; i < listModel.getSize(); i++) {
            listModel.get(i).checkLivePrice();
            repaint();
            showMessage("Refreshed!");
        }
    }
    
    private void singleItemRefresh(ActionEvent event) {
    	listModel.get(jlist.getSelectedIndex()).checkLivePrice();
    	showMessage("Item updated!");
    	repaint();
    }
		    
    private void editButtonClicked(ActionEvent event) {
       String itemName;
       String itemURL;
       Item item4 = listModel.get(jlist.getSelectedIndex());
       
       itemName = JOptionPane.showInputDialog("Enter the item's new name.");
       itemURL = JOptionPane.showInputDialog("Enter the new item's URL.");
       item4.setName(itemName);
       item4.setURL(itemURL);   
       showMessage("Item updated!");
    }
    
    private void deleteButtonClicked(ActionEvent event) {
            listModel.remove(jlist.getSelectedIndex());
            repaint();
            showMessage("Item Deleted");
    }
    
    private void exitButtonClicked(ActionEvent event) {
    	showMessage("Goodbye!");
        setVisible(false);
        dispose();
        System.exit(0);
    }
	
    private void mouseListener() {
        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                if (SwingUtilities.isRightMouseButton(mouseEvent)) {
                    popUpMenu.show(jlist, mouseEvent.getX(), mouseEvent.getY());
                }
            }
        };
        jlist.addMouseListener(mouseListener);
    }
		
		

	public static void main(String[] args) {
		new Main();
	}

}

