import java.awt.Color;
import java.awt.event.*;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;


@SuppressWarnings("serial") // annotation 
public class MainPanel extends JPanel {
	private JTextField textField;
	private JLabel label, count;
	private JComboBox<String> combo; 
	static int counter = 0, checked;
	static double result;
	private JButton convertButton, clear;
	private JCheckBox reverse;
	
	CurrencyListener listener = new Currency(); // creating object reference of class CurrencyListner.
	
	JMenuBar setupMenu() {
		JMenuBar menuBar = new JMenuBar(); // Instantiating menu bar
		
		
		// Instantiating menus.
		JMenu file = new JMenu("File");
		JMenu help = new JMenu("Help");
		
		//Adding menus to the menu bar 
		menuBar.add(file);
		file.setToolTipText("save, open or exit the program");
		menuBar.add(help);
		help.setToolTipText("Description about the application");
		
	
		
		//Adding menus items, icons and setting key combination
		JMenuItem neww = new JMenuItem("New");
		neww.addActionListener(new Newbtn());
		
		JMenuItem  open = new JMenuItem("Open File");
		open.setIcon(new ImageIcon("src/images/open.png"));
		open.addActionListener(new Openbtn());
		
		JMenuItem load = new JMenuItem("Load");
		load.setIcon(new ImageIcon("src/images/load.png"));
		load.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_MASK));
		load.addActionListener(new loadbtn());
		
		JMenuItem save = new JMenuItem("Save");
		save.setIcon(new ImageIcon("src/images/save.png"));
		save.addActionListener(new Savebtn());
		
		JMenuItem saveAs = new JMenuItem("Save As..");
		saveAs.setIcon(new ImageIcon("src/images/save as.png"));
		saveAs.addActionListener(new Saveasbtn());

		
		JMenuItem exit = new JMenuItem("Exit");
		exit.setIcon(new ImageIcon("src/images/exit.png"));
		exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		exit.addActionListener(new Exitbtn());
		
		JMenuItem about = new JMenuItem("About");
		about.setIcon(new ImageIcon("src/images/about.png"));
		about.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_MASK));
		about.addActionListener(new Aboutbtn());
		
		
		file.add(neww);
		file.add(open);
		file.add(save);
		file.add(saveAs);
		file.add(load);
		file.add(exit);
		help.add(about);
		
		load.doClick();
		
		return menuBar;
	}


	MainPanel() { //constructor class
		ActionListener listener = new ConvertListener(); 

		setLayout(null);
		
		combo = new JComboBox<String>(); // combo box instantiated 
		combo.setToolTipText("choose the type of conversion"); // setting tooltip text to combobox

		JLabel inputLabel = new JLabel("Enter value:");
		
		JLabel label2 = new JLabel("Converted Value: ");
		JLabel countval = new JLabel("No of Calculation: ");

		convertButton = new JButton("Convert");
		convertButton.addActionListener(listener); // convert values when pressed
		convertButton.setToolTipText("click to convert the entered value.");

		label = new JLabel("0");
		count = new JLabel("0");
		textField = new JTextField(5);
		textField.addKeyListener(new KeyAdapter() { // adding keyListener to the text field
			public void keyPressed(KeyEvent e) { // if Enter is pressed key event occure.
				if(e.getKeyCode()== KeyEvent.VK_ENTER) { // checking the given Event after pressing the defined key
					convertButton.doClick(); 
				}
			}
		});
		textField.setToolTipText("enter value for the conversion");
		
		clear = new JButton("Clear");
		clear.addActionListener(new Clearbtn()); // implementing the actionListner interface for clear button.
		clear.setToolTipText("click here to delete the conversion");
		
		reverse = new JCheckBox("Reverse");
		reverse.setToolTipText("Seclect to reverse the conversion.");
		
		add(combo);
		combo.setBounds(300, 100, 225,30); // defining position and size of the combo

		
		add(inputLabel);
		inputLabel.setBounds(210,50,200,40);
		

		add(textField);
		textField.setBounds(300,50,225,40);

		
		add(reverse);
		reverse.setBounds(530,60,75,30);
		
		
		add(convertButton);
		convertButton.setBounds(610,60,90,30);
		
		
		add(label2);
		label2.setBounds(300,180,200,40);
		
		add(label);
		label.setBounds(400,180,240,40);
		
		add(countval);
		countval.setBounds(300,200,200,40);
		
		add(count);
		count.setBounds(400,200,90,40);
				
		add(clear);
		clear.setBounds(460,190,64,40);
		

		setPreferredSize(new Dimension(900, 300)); //setting size of the frame
		setBackground(Color.lightGray);
	}

	private class ConvertListener implements ActionListener { /* implementing ActionListner interface 
																to the convertListener class */
		@Override
		public void actionPerformed(ActionEvent event) {
			String text = textField.getText().trim();  // store text getting from textField and trim it.

			if(text.isEmpty() == false) { 
				try {
					double value = Double.parseDouble(text); // convert inserted values into double.
					double factor = 0;
					double result = 0;
					
					String finalresult = "";

					int index = combo.getSelectedIndex();
					
					if(listener.getName().size()!=0) { // if size of arraylist is not z
						factor = listener.getPrice().get(index);
						String s = listener.getSymbol().get(index);
						
						if(reverse.isSelected()){
							result = value / factor;
							finalresult = "£" + String.format("%.2f", result);	
						}
						else {
							result = value * factor;
							finalresult = s + String.format("%.2f", result);
						}
					}
					
					label.setText(finalresult);
					counter ++;
					count.setText(String.valueOf(counter));
				}
				catch(NumberFormatException e) {
					JOptionPane.showMessageDialog(textField, "Give only integer values for the input.");
				}
			}
			else {
				JOptionPane.showMessageDialog(textField, "Error! \nPlease insert value First.");			
			}
		}
	}
	//implementing action listener to the buttons.
	private class Exitbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class Aboutbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(combo, "This Application automatically load the currency files from the directory into combo boxes.\n And convert pound to the given currency of different nation which are show in the combo boxes and vice-versa . \n\n Author: Surya Upadhyaya \n\n ID:  77261193 \n\n @2021 Copyright");
		}
	}
	private class Newbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(combo, "This function is under Construction");
		}
	}
	private class Openbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(combo, "This function is under Construction");
		}	
	}
	private class Savebtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(combo, "This function is under Construction");
		}
	}
	private class Saveasbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			JOptionPane.showMessageDialog(combo, "This function is under Construction");
		}
	}
	private class Clearbtn implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			textField.setText(null);
			label.setText("0");
			count.setText("0");
			counter = 0;
		}
	}
	private class loadbtn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent arg0) {
			File file;
			JFileChooser chooser;
			int status;
			BufferedReader br = null;
			if(combo.getItemCount() != 0) {
				chooser = new JFileChooser();
				status = chooser.showOpenDialog(null);
				
				if(status == JFileChooser.APPROVE_OPTION) {
					file = chooser.getSelectedFile();
					try {
						br = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
					}catch(Exception ex1) {}
				}
			}
			else {
				try {
					br = new BufferedReader(new InputStreamReader(new FileInputStream("src/firstCurrencyFile.txt"), "UTF-8"));
				}catch(Exception ex2) {}
			}
			
			listener.ClearArray(); // Clear values in arrays before storing again.
					
			
			Object[] line = br.lines().toArray(); 		/*reads the content on the file, line by 
														line and stores it in an array */ //-- deserialization--
			
			for(int i=0; i<line.length; i++) {
				try {
					String[] array = line[i].toString().split(","); //serialization 
					double num = Double.parseDouble(array[1]);
					
					if(array.length == 3) {
						listener.setName(array[0].trim());
						listener.setPrice(num);
						listener.setSymbol(array[2].trim());
						
						combo.removeAllItems();
							
						for(int j = 0; j<listener.getName().size();j++) {
							combo.addItem(listener.getName().get(j));
						}
					}
					else {
						listener.setErrata(array[i].toString());
					}
				}
				catch(Exception ex){
					listener.setErrata(line[i].toString());
				}	
			}
			
		int size = listener.getErrata().size();
		if(size>0) {
			String errors[] = new String[size];
			for(int i=0; i<size; i++) {
				errors[i] = listener.getErrata().get(i); 
			}
			JOptionPane.showMessageDialog(combo, errors );
		}
		}
	}
}
	


