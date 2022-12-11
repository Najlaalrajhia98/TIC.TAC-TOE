import java.awt.datatransfer.Clipboard;
import java.util.Scanner;

import javax.swing.text.StyledEditorKit.ForegroundAction;

/**
 * 
 */

/**
 * @author LAP-7
 *
 */
public class TICTAC {
	
	
	public static void main(String[] args) { 
		
		char [][] board = { {'-','*','-','*','-'},
                                 {' ','|',' ','|',' '},
                                 {' ','|',' ','|',' '},
                                 {' ','|',' ','|',' '},
                                 {'-','*','-','*','-'} 
                                };
		 printboard(board); // call the function
		 Scanner scan = new Scanner(System.in);
		 while (true) {
			 System.out.println("Player 1 enter your choice of slots from 1 to 9 :");
			 int slotchoice= scan.nextInt();
			 System.out.println(slotchoice);
			 Slotplace(board,slotchoice, "player1");
			 printboard(board);
			 System.out.println(" Player 2 enter your choice of slots from 1 to 9 :");
			 int slotchoice1= scan.nextInt();
			 Slotplace(board,slotchoice1, "player2");
			 printboard(board);
		 }

	}
		
	// function to print the board 
	public static void printboard(char [] [] board ) {
		
    // for each char array named row in the print board array and for each char in the row print colums every each line  
                for (char [] row : board ) {
                         	for (char cloum : row ) {
                           	System.out.print(cloum);
    	
                      	} 	
                         	System.out.println();
                }
	}
	// function to pass the board and the slot choice for the user eaither player 1 or 2
	public static void Slotplace(char [][] board, int slotchoice, String user  ) {
	
	char symbol=' ';
	if (user.equals("player1")) {
		symbol='X';
		} else if (user.equals("player2")){
			symbol='O';
		}
	switch (slotchoice) {
			 case 1: 
				 board [1][0] =symbol;
				 break;
			 case 2: 
				 board [1][2] =symbol;
				 break;
			 case 3: 
				 board [1][4] =symbol;
				 break;
			 case 4: 
				 board [2][0] =symbol;
				 break;
			 case 5: 
				 board [2][2] =symbol;
				 break;
			 case 6: 
				 board [2][4] =symbol;
				 break;
			 case 7: 
				 board [3][0] =symbol;
				 break;
			 case 8: 
				 board [3][2] =symbol;
				 break;
			 case 9: 
				 board [3][4] =symbol;
				 break;
			 default :
			 System.out.println("wrong choice");
		
}
	}
	}
