/**
 * @Coder: Jarrod Grineau
 * 		   Final Exam Practical
 * @Class Name: SoftDrink
 * @Purpose: This class is responsible for storing information so that you can use an arraylist of softdrink objects for the GUI class
 * @Date: August 16, 2022
 */

	public class SoftDrink {
		// variables from the UML diagram
		private String name;
		private double price;
		private int quantity;
	
		//constructor
		public SoftDrink(String name, double price, int quantity) {
			this.name = name;
			this.price = price;
			this.quantity = quantity;
		}
	
		//Getters
		public String getName() {
			return name;
		}
		public double getPrice() {
			return price;
		}
		public int getQuantity() {
			return quantity;
		}
	
		//Setters
		public void setName(String name) {
			this.name = name;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
	
		/**
		 * @Coder: Jarrod Grineau
		 * 		   Final Exam Practical
		 * @Class Name: toString
		 * @Purpose: This toString class is 
		 * @Date: August 16, 2022
		 */
		@Override
		public String toString() {
			return String.format("%s $%.2f", getName(), getPrice());
		}
		
}


