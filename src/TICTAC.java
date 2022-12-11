import java.awt.datatransfer.Clipboard;
import java.util.Scanner;

/**
 * 
 */

/**
 * @author LAP-7
 *
 */
public class TICTAC {
	
static String[] board;
	/**
	 * @param args
	 */
	
	static void printboard() {
	System.out.println("|---|---|---|");
	System.out.println("| "+ board[0] + " | " + board[1] + " | " + board [2] + "| ");
	System.out.println("| "+ board[3]+ " | " + board[4] + " | " + board [5] + "| ");
	System.out.println("| "+ board[6]+ " | " + board[7]+ " | " + board [8] + "| ");
	System.out.println("|---|---|---|");

		}
	public static void main(String[] args) { 
		Scanner in= new Scanner(System.in);
		board = new String [9];
		for (int b=0; b<9; b++) {
			board[b]=String.valueOf(b+1);
		}
		System.out.println("Welcome to TIC.TAC-TOE GAME");
		System.out.println("-----------------------------");
		printboard();
		System.out.println("PLAYER X WILL PLAY FIRST PLZ CHOOSE A SLOT");
		int player1= in.nextInt();
		if (player1 ==1) {
			System.out.println("player 1 : 1");
			board[0]="X";
			printboard();
		} 
		
		else if (player1 ==2) {
			System.out.println("player 1 : 2");
			board[1]="X";
			printboard();
		}
	
			
		
		
		

}

}
