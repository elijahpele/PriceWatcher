package views;

import models.Item;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

/** A special panel to display the detail of an item. */
@SuppressWarnings("serial")
public class ItemView extends JPanel {
    private Item item = new Item();


    /** Interface to notify a click on the view page icon. */
    public interface ClickListener {

        /** Callback to be invoked when the view page icon is clicked. */
        void clicked();
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

        Font boldFont = new Font("Helevetica", Font.BOLD, 12);
        Font regFont = new Font("Helevetica", Font.ITALIC, 12);

        g.drawImage(getImage("internet.png"), 137, 19, this);
        y += 130;
        g.setFont(boldFont);
        
        
        
        
        
        g.drawString("Name:   ", x, y);
        y += 20;
        g.drawString("URL:      ", x, y);
        y += 20;

        g.drawString("Price:    ", x, y);
        y += 20;
        g.drawString("Change:   ", x, y);
        y += 20;
        g.drawString("Added:    ", x, y);
        y = 161;
        x = 90;
        g.setFont(regFont);
        g.drawString(item.getName(), x, y);
        y += 20;
        g.drawString(item.printURL(), x, y);
        y += 20;
        g.setColor(Color.BLUE);
        g.drawString(item.printCurrentPrice(), x, y);
        y+=20;
        g.setColor(Color.BLACK);
        if(item.getCurrentPrice() < item.getPreviousPrice()){
            g.setColor(Color.GREEN);
        }
        g.drawString(item.getPriceChange(),x,y);
        y+=20;
        g.setColor(Color.BLACK);
        g.drawString(item.getDateAdded()+item.getStartingPrice(),x,y);
        y+= 30;
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
}
