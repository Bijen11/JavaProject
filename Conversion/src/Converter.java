import java.awt.Component;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class Converter {
	public static void main(String[] args) {
    	//creating the frame object for the class Jframe
        JFrame frame = new JFrame("CurrencyConverter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit the applictaion
        
        //creating object for currencypanel to inherit property from jpanel
        CurrencyPanel panel = new CurrencyPanel();
        frame.setJMenuBar(panel.setupMenu());
       
        //using getContentpane method to add panel to frame
        frame.getContentPane().add(panel);
        //using pack method so that the GUI program's output will be in preferred size and layout
        frame.pack();
        
        frame.setVisible(true);//setting the visibility of the frame true to see the formatted GUI output
        
    }
}
	

