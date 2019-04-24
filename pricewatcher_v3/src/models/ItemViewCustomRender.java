package models;

import java.awt.Component;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import views.ItemView;
 
/**
 * Custom renderer to display a country's flag alongside its name
 *
 * @author wwww.codejava.net
 */
public class ItemViewCustomRender extends ItemView implements ListCellRenderer<Item> {
    @Override
    public Component getListCellRendererComponent(JList<? extends Item> list, Item item, int index,
        boolean isSelected, boolean cellHasFocus) {
         setItem(item); 
         
        return this;
    }
     
}