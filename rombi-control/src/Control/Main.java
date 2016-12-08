package Control;

import Sub.Rombi;

public class Main {
	public static void main(String[] args){
		System.out.println("ROMBI Control Program v0.1.0");
		System.out.println("Beginning Submarine Setup");
		
		//All that should be done here is to create a new instance of Rombi. The Rombi constructor should do the rest.
		Rombi rombi = new Rombi();
		System.out.println("Submarine Setup Complete. Beginning main control program.");

		ConsoleUI.run(rombi);
		
		System.exit(0);
	}
}
