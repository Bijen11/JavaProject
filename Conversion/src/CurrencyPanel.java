import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.awt.*;
import javax.swing.*;

//Inheriting from parent class
public class CurrencyPanel extends JPanel {
	
	//initializing the private properties 
	private final static String[] list = {"Pound/Japanese yen (JPY)","Pound/Euro (EUR)","Pound/US Dollars(USD)","Pound/Australian Dollars (AUD)","Pound/Canadian Dollars (CAD)","Pound/South Korean Won (KRW)","Pound/Thai Baht (THB)","Pound/United Arab Emirates Dirham (AED)"};
	private JTextField textField;
	private JLabel label;
	private JLabel countNumber;
	private JComboBox<String> combo;
	private final String aboutmessage="Author:Neha Pandey "+ "\n Symbolno: 7227231"+ "\nPurpose:To convert different currency units" +"\u00a9 2020";
	private final String errorMessageEmptyField="Field is empty.";
	private final String numberFormatError="Field contains special charecters or alphabets.";
	private int Conversioncount = 0;
	double result;
	JButton clearButton,convertButton;
	File fileName;
	

//creating panels, button and checkbox
	JPanel subPanel1=new JPanel();
	JPanel subPanel2=new JPanel();
	JPanel minipanel1= new JPanel();
	JPanel minipanel2= new JPanel();
	JPanel minipanel3= new JPanel();
	JButton ResetButton;
	JCheckBox reverse;



	JMenuBar setupMenu() {
		//imageicon class is used to get the image
		/* getting imagesicons for file and help menu option*/
		ImageIcon imagefile= new ImageIcon(new ImageIcon("image/file.jpg").getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));
		ImageIcon imagehelp= new ImageIcon(new ImageIcon("image/help.jpg").getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));
		ImageIcon imageload= new ImageIcon(new ImageIcon("image/load.jpg").getImage().getScaledInstance(23, 23, Image.SCALE_DEFAULT));

		/*cretaiong menu bar and adding file and help along with sub menus*/
		JMenuBar menuBar = new JMenuBar();//creating menu bar
		JMenu filemenu = new JMenu("File");//creating menu item file
		filemenu.setIcon(imagefile);//adding icon to file
		filemenu.setToolTipText("access to exit.");//setting tooltip to the file menu
		filemenu.setMnemonic('F');// Alt+F

		JMenu helpmenu = new JMenu("Help");//creating menu item help
		helpmenu.setIcon(imagehelp);//adding icon to help menu
		helpmenu.setMnemonic('H');// Alt+H
		helpmenu.setToolTipText("access to about.");//setting tooltip to the help menu
		
		JMenu loadmenu = new JMenu("Load");
		loadmenu.setIcon(imageload);
		loadmenu.setMnemonic('L');
		loadmenu.setToolTipText("loads currency file");
		
		
		//adding menu items to menu bar
		menuBar.add(filemenu);
		menuBar.add(helpmenu);
		menuBar.add(loadmenu);
		//end of adding to menu bar

		//sub menu exit
		JMenuItem Exititem = new JMenuItem("Exit");//creating sub menu exit
		Exititem.addActionListener(new exitMenuListener());//exit the program when clicked in exit
		Exititem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,ActionEvent.ALT_MASK));//adding mnemonic to sub menu
		Exititem.setMnemonic('E');//alloctaing the key letter for mnemonic
		filemenu.add(Exititem);//adding submenu exit to menu item file
		
		JMenuItem updateFile = new JMenuItem("Update File");//creating sub menu exit
		//updateFile.addActionListener(new exitMenuListener());//exit the program when clicked in exit
		updateFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,ActionEvent.ALT_MASK));//adding mnemonic to sub menu
		updateFile.setMnemonic('U');//alloctaing the key letter for mnemonic
		loadmenu.add(updateFile);//adding submenu exit to menu item file

		//sub menu about
		JMenuItem Aboutitem = new JMenuItem("About");//creating sub menu about
		Aboutitem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.ALT_MASK));//adding mnemonic to sub menu
		Aboutitem.setMnemonic('A');//allocating the key letter for mnemonic
		helpmenu.add(Aboutitem);//adding submenu about to menu item help
		//adding action listener to about to show the dialogue message cointain in aboutmessage
		Aboutitem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,aboutmessage,"converter",JOptionPane.INFORMATION_MESSAGE);
			}
		});
		// action listner for updatefile menu item 
				updateFile.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						JFileChooser fileChooser = new JFileChooser();
						 fileChooser.showDialog(null,"Select file");
			            fileChooser.setVisible(true);
			            fileName = fileChooser.getSelectedFile();
			          
					}
					});

		return menuBar;

	}
	
			
	//constructor of the class mainpanel
	CurrencyPanel() {
		//this.add(Currency());
		ActionListener listener = new ConvertListener();

		combo = new JComboBox<String>(list);
		combo.addActionListener(listener); //convert values when option changed
		combo.addItem("Invalid data");
		
		
		JLabel inputLabel = new JLabel("Enter value:");
		JButton convertButton = new JButton("Convert");
        convertButton.setToolTipText("convert the set value");
		convertButton.addActionListener(listener); // convert values when pressed
		ResetButton = new JButton("Reset");//
		ResetButton.setToolTipText("reset the enter value");
		JLabel countLabel =new JLabel("Conversion count :");
		countNumber =new JLabel("0");
		
		
		label = new JLabel("---");
		textField = new JTextField(5);
		textField.addActionListener(new ConvertListener());// to convert value without clicking convert i.e by clicking enter
		
		//for reverse button
		reverse= new JCheckBox("Reverse Conversion");
		reverse.setToolTipText("Reverse the given function");

		// managing layouts and adding component to layout
		setLayout(new GridLayout(2,1,0,5)); // setting grid layout for the main panel
		add(subPanel1);// adding sub panel to main panel
		add(subPanel2);// adding sub panel to main panel
		subPanel1.setLayout(new GridLayout(1,3));// setting grid layout to sub panel
		subPanel1.add(minipanel1);// adding mini panel to sub panel
		subPanel1.add(minipanel2);
		subPanel1.add(minipanel3);
		
		minipanel1.add(countLabel);// adding components
		minipanel1.add(countNumber);
		minipanel2.add(reverse);
		minipanel3.add(ResetButton);
		
		// adding components to sub panel
		subPanel2.add(combo);
		subPanel2.add(inputLabel);
		subPanel2.add(textField);
		subPanel2.add(label);
		subPanel2.add(convertButton);
		//end of managing layout

		setPreferredSize(new Dimension(800, 80));
		setBackground(Color.PINK);

		// ActionListener
		// ActionListener for clear button
		//this action Listener is called when clear button is pressed and clears all necessary fields and label.
		ResetButton.addActionListener(new ActionListener() { 
			@Override
			public void actionPerformed(ActionEvent e) {	
				textField.setText("");//clearing value from the text field after clicking clear
				countNumber.setText("0");// to set conversion count 0 after clearing
				Conversioncount=0;//to set the value zero and start from zero after clearing
				label.setText(" ");
			}
		});
		
//end of action for clear
//end of ActionListener

}
	private Component Currency() {
		// TODO Auto-generated method stub
		return null;
	}
	//actionlistener to exit from the GUI application
	private static class exitMenuListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
		private class ConvertListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent event) {
		
		try // try and catch block to catch NumberFormatException and other exception
		{
		String text = textField.getText().trim();// gets text from variable textField and stores in variable text

		
		
		 if (text.isEmpty() == true) { 
			 JOptionPane.showMessageDialog(null,errorMessageEmptyField,"Converter",JOptionPane.INFORMATION_MESSAGE);//if textfield is empty the message from errorEsageEmptyField is called
		 }
		
		else {
			double value = Double.parseDouble(text);
		
			// the factor applied during the conversion
			double factor = 0;
		
			// the offset applied during the conversion.
			double offset = 0;
			
			// Setup the correct factor/offset values depending on required conversion
			switch (combo.getSelectedIndex()) {
		
			case 0: 
				if(reverse.isSelected()) { 
					factor = 0.393701;// converts  to pound when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
				}
				else {
					factor = 137.52; // Pound/Japanese yen (JPY)
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"¥");
				}
			Conversioncount++;
			break;
			case 1: 
				if(reverse.isSelected()) {
					factor = 2.2046226218; // converts to pounds when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
				}
				else {
					factor =1.09; // Pound/Euro
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"€");
				}
		
			Conversioncount++;
			break;
			case 2: 
				if(reverse.isSelected()) { 
					factor = 57.29577951; // converts radians to degree when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
				}
				else {
					factor =1.29; // Pound/US Dollars (USD)
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"$");
					}
		
			Conversioncount++;
			break;
			case 3: 
				if(reverse.isSelected()) {
					factor =2.47; // converts hectares to acres when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
					}
				else {
					factor=1.78; //Pound/Australian Dollars (AUD) 
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"A$");
					}
		
			Conversioncount++;
			break;
			case 4: 
				if(reverse.isSelected()) {
					factor =0.6214; // converts kilometers to miles when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
					}
				else {
					factor =1.70; //Pound/Canadian Dollars (CAD) 
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"C$");
					}
		
			Conversioncount++;
			break;
			case 5: 
				if(reverse.isSelected()) {
					factor =1.0936133; // converts meters to yards when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
					}
				else {
					factor =1537.75; //Pound/South Korean Won (KRW) 
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"₩");
					}
		
			Conversioncount++;
			break;
			case 6: 
				if(reverse.isSelected()) { 
					factor =01; // converts fahrenheit to celsius when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
					}
				else {
					factor =40.52;  //Pound/Thai Baht (THB) 
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"฿");
					}
		
			Conversioncount++;
			break;
			case 7: 
				if(reverse.isSelected()) { 
					factor =9; // converts fahrenheit to celsius when reverse is checked
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"£");
					}
				else {
					factor =4.75;  //Pound/United Arab Emirates Dirham (AED)
					double result = (double) Math.round((factor * value + offset)*100)/100;
					label.setText(Double.toString(result)+"إ د");
					}
		
			Conversioncount++;
			break;
			}
			countNumber.setText(Integer.toString(Conversioncount));//to count how many time we have converted.
			
		
			}

			}catch (NumberFormatException ex) // this block of statement catches any other errors than NumberFormat
		    {
			JOptionPane.showMessageDialog(null,numberFormatError,"converter",JOptionPane.INFORMATION_MESSAGE);
	
			} 
			}

		}
		JComboBox<String> loadFile(File fileName) throws IOException {
			System.out.println("okayy" );
			try {
				BufferedReader fileDetails = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF8"));
				
					String details = fileDetails.readLine();
					while(details!= null) {
						String [] disjoin = details.split(",");
						if(disjoin.length == 3) {
							 JOptionPane.showMessageDialog(null, "Invalid number of data values!\n" +
	                                 "There should be 3 values(currency, currency conversion factory and currency " +
	                                 "symbol) in a line of the file!",
	                         "ERROR!", JOptionPane.ERROR_MESSAGE);
	                combo.addItem("Invalid data ");
							combo.addItem(disjoin[1]);
							
						}else {
							System.out.println("Invalid data were present in the file" );
							
						}
						
					}
				} catch (FileNotFoundException e) {
				
				e.printStackTrace();
			}
			return combo;
			
	
		
		}
}


