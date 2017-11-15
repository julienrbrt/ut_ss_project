package Executable;

import java.util.Scanner;

import Model.*;

public class Ringgz {
	
	Scanner in;
	Board board;
	boolean exit = false;
	Color c = Color.REDDD;
	
	public Ringgz() {
		board = new Board();	//setup board
		System.out.println(board.toString());
		
		in = new Scanner(System.in);
		while(!exit) {
			String input = in.nextLine();
			switch(input) {
			case "EXIT":
				System.out.println("Closing game");
				exit = true;
				break;
			case "PLACE":
				System.out.println("Give x");
				int x = in.nextInt();
				System.out.println("Give y");
				int y = in.nextInt();
				System.out.println("Give base boolean");
				boolean base = in.nextBoolean();
				System.out.println("Give ring size");
				int size = in.nextInt();
				if(x < board.getSize() && y < board.getSize() && size < 4) {
					board.addRing(x, y, base, size, c);
				}
				else {
					System.out.println("Invalid input");
				}
				System.out.println(board.toString());
				in.nextLine();
				break;
			case "CHANGE":
				System.out.println("Give new color number (0=Red, 1=Purple, 2=Green, 3=Yellow");
				int color = in.nextInt();
				switch(color) {
				case 0:
					c = Color.REDDD;
					System.out.println("Changed to Red");
					break;
				case 1:
					c = Color.PURPL;
					System.out.println("Changed to Purple");
					break;
				case 2:
					c = Color.GREEN;
					System.out.println("Changed to Green");
					break;
				case 3:
					c = Color.YELLO;
					System.out.println("Changed to Yellow");
					break;
				default:
					System.out.println("Not a valid input");
					break;
				}
				in.nextLine();
				break;
			default:
				System.out.println("Not a valid command");
				break;
			}
		}
		in.close();
	}

	public static void main(String[] args) {
		new Ringgz();
	}

}
