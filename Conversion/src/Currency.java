
import java.util.Scanner;
import java.awt.event.ActionListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.*;
import java.net.URI;

import javax.swing.*;


public class Currency extends JPanel
{
	private String[] name ={"Japanese yen (JPY)", "Euro (EUR)", "US Dollars (USD)", "Australian Dollars (AUD)",
	         "Canadian Dollars (CAD)", "South Korean Won (KRW)", "Thai Baht (THB)",
	         "United Arab Emirates Dirham (AED)"};;
	private double[] factor;
	private String[] symbol;
	private String e ="this is error file";

	// ActionListener loadMenuItemListener = new loadMenuItemListener();
	void loadFile() {       

        File file = new File("currency.txt");
       
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF8"));
           
            String line = in.readLine();
           
            while ( line != null ) {
                // Process 'line' (split up).
                String [] parts = line.split(",");
               
                // validate, store somewhere etc.
                line = in.readLine();    // read next line (if available)
            }
           
            in.close();
           
        } catch (Exception e) {
           
            // Something went wrong.
            String msg = e.getMessage();
           
            // show the message to the user!
        }

}


}
