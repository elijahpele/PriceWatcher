package views;

import models.Item;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/** A special panel to display the detail of an item. */
@SuppressWarnings("serial")
public class ItemView extends JPanel implements JListRender<Item> {
	Item item = new Item();
	ArrayList<Item> defaultList = new ArrayList<Item>();
	private JList<Item> list; // Creating the Jlist

	/** Interface to notify a click on the view page icon. */
	public interface ClickListener {

		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}

	/**This method */
	public Component getJListRender(JList<? extends Item> list,int index, Item item, boolean isSelected, boolean hasFocus){
		
		return this;
		
	}
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/images/";

	/** View-page clicking listener. */
	private ClickListener listener;

	/** Create a new instance. */
	public ItemView(Item item) {
		this.item = item;
		setPreferredSize(new Dimension(150, 180));
		setBackground(Color.WHITE);
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
					listener.clicked();
				}
			}
		});
	}

	/** Set the view-page click listener. */
	public void setClickListener(ClickListener listener) {
		this.listener = listener;
	}

	/** Overridden here to display the details of the item. */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		int x = 30, y = 30;

		g.drawImage(getImage("internet.png"), 137, 19, this);
		y += 130;

	}

	/** Return true if the given screen coordinate is inside the viewPage icon. */
	private boolean isViewPageClicked(int x, int y) {

		return new Rectangle(180, 60, 180, 70).contains(x, y);
	}

	/** Return the image stored in the given file. */
	public Image getImage(String file) {
		try {
			URL url = new URL(getClass().getResource(IMAGE_DIR), file);
			return ImageIO.read(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

