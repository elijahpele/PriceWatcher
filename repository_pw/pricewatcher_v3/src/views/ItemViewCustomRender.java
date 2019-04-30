package views;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import models.Item;
 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class ItemViewCustomRender extends ItemView implements ListCellRenderer<Item> {
    
    
    public ItemViewCustomRender() {
    	setOpaque(true);
    }
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index,
        boolean isSelected, boolean cellHasFocus) {
         setItem(item); 
         if (isSelected) {
             setBackground(list.getSelectionBackground());
             setForeground(list.getSelectionForeground());
         } else {
             setBackground(list.getBackground());
             setForeground(list.getForeground());
         }
        return this;
    }
     
}