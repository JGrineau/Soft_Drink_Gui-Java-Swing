import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


/**
 * @Coder: Jarrod Grineau
 * 		   Final Exam Practical
 * @Class Name: DrinkMachineGui
 * @Purpose: This class is responsible for building the GUI
 * @Date: August 16, 2022
 */

@SuppressWarnings("serial")
public class J_G_DrinkMachineGUI extends JFrame {
	//Array list of soft drinks
	ArrayList<SoftDrink> softDrinks = new ArrayList<SoftDrink>();

	// Private variables for GUI
	private JRadioButton[] softDrinkRadioButton;
	
	private JTextField paymentAmount;
	private JTextField changeDue;
	private JTextField outputMessage;
	
	private JButton deliverButton;
	
	private int index; 
	private int numOutOfStock; 
	private boolean drinkAvailable;

	public J_G_DrinkMachineGUI(ArrayList<SoftDrink> list) {
		// Set title
		super("Jarrod Grineau's Soft Drink Vending Machine");
		setLayout(new BorderLayout());
		setSize(500, 400);
		
		this.softDrinks = new ArrayList<SoftDrink>(list);
		this.index = -1;
		this.numOutOfStock = 1;
		this.drinkAvailable = true;
		this.paymentAmount = new JTextField(15);
		
		// Initialize change due field
		this.changeDue = new JTextField(15);
		this.changeDue.setEditable(false);
		// Output message
		this.outputMessage = new JTextField();
		this.outputMessage.setEditable(false);
		this.deliverButton = new JButton("Deliver Selection");
		this.deliverButton.addActionListener(new DeliverButtonEvent());
	

		//need to add private panels to frame
		add(drinkPanel(), BorderLayout.NORTH);
		add(paymentPanel(), BorderLayout.CENTER);
		add(bottomPanel(), BorderLayout.SOUTH);
		
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	
	}



	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: drinkPanel() is a private JPanel method
	 * @Purpose: This class is responsible for lining up the drink panel layout
	 * @Date: August 16, 2022
	 */
	private JPanel drinkPanel() {
		// drinkPanel
		JPanel drinkPanel = new JPanel();
		drinkPanel.setLayout(new GridLayout(2, 3));
		// Get number of soft drinks from input on the main
		int numDrinks = this.softDrinks.size();
		this.softDrinkRadioButton = new JRadioButton[numDrinks];
		
		// Add all soft drinks as radio button to the panel
		for (int i = 0; i < this.softDrinks.size(); i++) {
			this.softDrinkRadioButton[i] = new JRadioButton(softDrinks.get(i).toString());
		
			// button press action listener
			this.softDrinkRadioButton[i].addActionListener(new DrinkPressedEvent());
		
			// Add and create a button group for the panel
			drinkPanel.add(this.softDrinkRadioButton[i]);
		}
		ButtonGroup buttonGroup = new ButtonGroup();
		
		
		for (int i = 0; i < this.softDrinkRadioButton.length; i++) {
			buttonGroup.add(this.softDrinkRadioButton[i]);
		}
		
		
		return drinkPanel;
	}

	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: paymentPanel() is a private JPanel method
	 * @Purpose: This class is responsible for outputting the payment panel on the JFrame
	 * @Date: August 16, 2022
	 */
	private JPanel paymentPanel() {
		// paymentPanel
		JPanel paymentPanel = new JPanel();
		paymentPanel.setLayout(new GridLayout(3, 1, 0, 2));
		paymentPanel.add(new JLabel("Enter Payment Amount:"));
		paymentPanel.add(this.paymentAmount);
		paymentPanel.add(new JLabel("Change Due:"));
		paymentPanel.add(this.changeDue);
		paymentPanel.add(new JLabel());
		
		// Return paymentPanel
		return paymentPanel;
	}

	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: bottomPanel() is a private JPanel method
	 * @Purpose: This class is responsible for putting the deliver button and output/error message on the GUI
	 * @Date: August 16, 2022
	 */
	private JPanel bottomPanel() {
		// bottomPanel
		JPanel outputPanel = new JPanel();
		outputPanel.setLayout(new GridLayout(2, 1, 0, 5));
		outputPanel.add(this.deliverButton);
		outputPanel.add(this.outputMessage);
		
		return outputPanel;
}

	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: DrinkPressedEvent is for the action of pressing the button
	 * @Purpose: This class is responsible for pressing the radio buttons.
	 * @Date: August 16, 2022
	 */
	//Inner class to handle soft drink selection
	private class DrinkPressedEvent implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent ae) {
			//Clear change due text field
			changeDue.setText("");
			String selectedDrink = ((JRadioButton) ae.getSource()).getText();
		
			for (int i = 0; i < softDrinks.size(); i++) {
				if (selectedDrink.compareTo(softDrinks.get(i).toString()) == 0) {
				index = i;
				break;
			}
}

	//Check if the selected drink is in stock
	checkDrinkStatus();
	}
	
		/**
		 * @Coder: Jarrod Grineau
		 * 		   Final Exam Practical
		 * @Class Name: checkDrinkStatus() is to check the drink status
		 * @Purpose: This class is responsible for checking the drink status
		 * @Date: August 16, 2022
		 */
	public void checkDrinkStatus() {
		//Check whether the soda machine is completely empty
		if (numOutOfStock == softDrinks.size()) {
		
		//Disable all radio buttons
		for (JRadioButton rb : softDrinkRadioButton)
			rb.setEnabled(false);
		
			//Print message
			outputMessage.setText("This soft drink vending machine is empty.");
		
			//Set drinkAvailable to false
			drinkAvailable = false;
			} else {
			//check for drink instock
		if (softDrinks.get(index).getQuantity() >0) {
			//Decrease the selected drink quantity by 1
			softDrinks.get(index).setQuantity(softDrinks.get(index).getQuantity() - 1);
			
			//Print message
			outputMessage.setText("Please enter the payment amount");
			
			//Set drinkAvailable to true
			drinkAvailable = true;
			} else {
			//Print message
			outputMessage.setText("This soft drink is out of stock. Choose another.");
			
			//Set drinkAvailable to false
			drinkAvailable = false;
			}
		}
		}
	}

	
	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: checkDrinkStatus() is to check the drink status
	 * @Purpose: This class is responsible for checking the drink status
	 * @Date: August 16, 2022
	 */
	


	private class DeliverButtonEvent implements ActionListener {
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//Check if drink is available
		if (drinkAvailable) {
		try {
		int payment = Integer.parseInt(paymentAmount.getText().trim());
		double price = softDrinks.get(index).getPrice();
		
		if (payment >= price) {
			paymentAmount.setText(null);
			changeDue.setText(String.format("$%.2f", (payment - price)));
			
			//Display message
			outputMessage.setText("Thank you for your soft drink purchase");
			
			if (softDrinks.get(index).getQuantity() <= 0) {
			//Increment numOutOfStock by 1
			numOutOfStock += 1;
		}
			} else {
		//Display message
		outputMessage.setText("Insufficient payment for the drink. Re-enter your payment.");
		}
	
			} catch (NumberFormatException nfe) {
			//Display message
			outputMessage.setText("Enter payment ONLY in WHOLE DOLLAR amounts ($1 or $2 coins). Re-enter your payment.");
			}
		}
	}
}
	
	/**
	 * @Coder: Jarrod Grineau
	 * 		   Final Exam Practical
	 * @Class Name: main() 
	 * @Purpose: This class is responsible outputting the drinks that are inputted usins buttons 
	 * @Date: August 16, 2022
	 */

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		//Create soft drink ArrayList and load with data
		ArrayList<SoftDrink> list = new ArrayList<SoftDrink>();
		list.add( new SoftDrink("Coke", 1.25, 1) );
		
		list.add( new SoftDrink("Sprite", 2.2, 1) );
		
		list.add( new SoftDrink("Fanta Orange", 1.2, 1) );
		
		list.add( new SoftDrink("Mountain Dew", 2.35, 1) );
		
		list.add( new SoftDrink("Root Beer", 1.35, 1) );
		
		list.add( new SoftDrink("Red Pop", 1.85, 1) );
		
		//call DrinkMachineGUI constructor and pass it the list
		
		J_G_DrinkMachineGUI frame = new J_G_DrinkMachineGUI(list);
	
	}//End of main method
	
	
}//End of Class
