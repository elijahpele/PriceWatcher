package models;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import views.ItemView;

import javax.swing.DefaultListModel;
  
/**
 * JList Custom Renderer Example
 *
 * @author wwww.codejava.net
 */
public class JListCustom extends JFrame {
 
    public JListCustom() {
    
    	//ItemList list = new ItemList();
    	
    	//Defualt list model created,
    	DefaultListModel<Item> listModel = new DefaultListModel<>();
    	
    	//List<Item> itemList = new ArrayList<Item>(list.getList());
    	
    	//Hard Coded Items to add to ListModel
    	Item item1 = new Item("Beats", "ebay.com", "01/02/0121",0, 0, 0, 0);
    	Item item2 = new Item("Laptop", "facebook.com", "06/01/1996", 0,0,0,0);
    
    	listModel.addElement(item1);
    	listModel.addElement(item2);
    	
    	//itemList.add(item1);
    	//itemList.add(item2);
    	//Item[] itemArray = new Item[itemList.size()];
    	
    	//Jlist initialized
    	JList <Item> jlist = new JList<>(listModel);
    	jlist.setCellRenderer(new ItemViewCustomRender());
    	//What is this?
        add(new JScrollPane(jlist));
 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("JList Renderer Example");
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
 
    
    
}
