package views;
import javax.swing.*;
import java.awt.event.*;

public class MyMenuBar extends JPanel implements ActionListener, KeyListener {
	
	JMenuBar menuBar = new JMenuBar();

	JMenu app, item;
	JMenuItem exit;
	JMenuItem checkPrices, addItem;

	
	public MyMenuBar() {
	
	app = new JMenu("App");
	app.setMnemonic(KeyEvent.VK_A);
	menuBar.add(app);
	
	item = new JMenu("Item");
	item.setMnemonic(KeyEvent.VK_I);;
	menuBar.add(item);
	
	exit = new JMenuItem("Exit");
	exit.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	});
	
	app.add(exit);
	
	checkPrices = new JMenuItem("Check Prices");
	checkPrices.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			//
			//TO-DO
			//
		}
	});
	item.add(checkPrices);
	
	addItem = new JMenuItem("Add item");
	addItem.addActionListener(new ActionListener(){
		public void actionPerformed(ActionEvent e) {
			//
			//TO-DO
			//
			}
	});
	item.add(checkPrices);
	
	
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}

