import java.awt.datatransfer.Clipboard;
import java.awt.im.InputMethodHighlight;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;

import javax.security.auth.x500.X500Principal;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;



/**
 * 
 */

/**
 * @author LAP-7
 *
 */
public class TICTAC {


	public static void main(String[] args) { 

		ArrayList<Integer> slotchoiceArrayList= new ArrayList<Integer>();


		char [][] board = { 	{'-','*','-','*','-'},
				{' ','|',' ','|',' '},
				{' ','|',' ','|',' '},
				{' ','|',' ','|',' '},
				{'-','*','-','*','-'} 
		};

		printboard(board); // call the function to print the board.
		Scanner scan = new Scanner(System.in);

		boolean valid = false;
		char symbolchoice1;
		char symbolchoice2;
		boolean playstatus= true;

		do {  // player one choice of symbol
			System.out.println("player 1 please choose a symbol X or O :");
			symbolchoice1 = scan.next().charAt(0);
			if (symbolchoice1== 'x'|| symbolchoice1== 'X'||symbolchoice1== 'O'||symbolchoice1== 'o')
			{ 
				valid = true;
			} 
			else {
				valid=false;
				System.out.println("its not a valid symbol enter another one ");
			}
		} while (!valid);


		do { // player two choice of symbol
			System.out.println("player 2 please choose a symbol X or O :");
			symbolchoice2 = scan.next().charAt(0);
			do { // do while loop if player 2 choose symbol of player 1
				if (symbolchoice2==symbolchoice1) 
				{
					System.out.println("you enterd a symbol of player 1 plz choose another one:");
					symbolchoice2 = scan.next().charAt(0); 
				} 
			} while (symbolchoice2==symbolchoice1);
			if (symbolchoice2== 'x'|| symbolchoice2== 'X'||symbolchoice2== 'O'||symbolchoice2== 'o') 
			{
				valid=true;
			}
			else {
				valid=false;
				System.out.println("its not a valid symbol enter another one :");
			}

		} while (!valid);





		System.out.println("player 1 will be playing with  : "+ symbolchoice1);
		System.out.println("player 2 will be playing with  : "+ symbolchoice2);

		for (int i=0; i<=4;i++) {

			System.out.println("Player 1 enter your choice of slots :");
			int slotchoice= scan.nextInt();
			do {
				if (slotchoice>9 || slotchoice<1) {
					System.out.println("Enter a valid number ");
					slotchoice= scan.nextInt(); 
				}	
			} while (slotchoice>9 || slotchoice<1);
			Slotplace(board,slotchoice, symbolchoice1,slotchoiceArrayList);
			printboard(board); // Prints the result on the Board 

			if (i==4) 
			{
				playstatus=false;
				System.out.println("its a drow ! game over ");

			}

			if (playstatus) 
			{
				System.out.println(" Player 2 enter your choice of slots :");
				int slotchoice1= scan.nextInt();
				do {
					if (slotchoice1>9 || slotchoice1<1 ) {
						System.out.println("Enter a valid number ");
						slotchoice1= scan.nextInt(); 
					}
				} while (slotchoice1>9 || slotchoice1<1 ); 
				Slotplace(board,slotchoice1, symbolchoice2,slotchoiceArrayList);
				printboard(board);
			} 
		}
	}


	// function to print the board 
	public static void printboard(char [] [] board ) {

		// for each char array named row in the print board array and for each char in the row print colums every each line  
		for (char [] row : board ) 
		{
			for (char cloum : row ) 
			{

				System.out.print(cloum);

			} 	

			System.out.println();
		}
	}

	// function to pass the board and the slot choice for the user eaither player 1 or 2
	public static void Slotplace(char [][] board, int slotchoice, char user,ArrayList<Integer> slotchoiceArrayList)
	{
		Scanner scan = new Scanner(System.in);
		char symbol=' ';
		if (user == 'X' || user=='x')  
		{
			symbol='X';

		} 
		else if (user == 'O' || user=='o')
		{
			symbol='O';
		} 
		Boolean takenBoolean= false;

		do {

			takenBoolean= false;
			if (!slotchoiceArrayList.contains(slotchoice)){
				slotchoiceArrayList.add(slotchoice);
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
					System.out.println("ENTER A VALID NUMBER");
				}
			}
			else if(slotchoiceArrayList.contains(slotchoice)) {
				takenBoolean= true;
				System.out.println("Enter another slot number:");
				slotchoice= scan.nextInt(); 
			}


		} while (takenBoolean);



		// TO CHECK THE WINNER 

		// TO CHECK THE 

		for (int row =1; row <=3; row++) 
		{

			if (board[row][0] == symbol && board [row][2] == symbol && board [row][4]== symbol) 
			{
				System.out.println("WE HAVE A WINNER YAY :)");
				printboard(board);
				System.exit(0);

			} 
		}

		// to check the COLUMS
		for(int cloum=0;cloum<=4;cloum=cloum+2) { 

			if (board[1][cloum] == symbol && board [2][cloum] == symbol && board [3][cloum]== symbol) 
			{
				System.out.println("WE HAVE A WINNER YAY :)");
				printboard(board);
				System.exit(0);
			}
		}
		// DIAGNOLS
		if (board[1][0] == symbol && board[2][2] == symbol && board[3][4] == symbol) 
		{
			System.out.println("WE HAVE A WINNER YAY :)");
			printboard(board);
			System.exit(0);
		}
		if (board[3][0] == symbol && board[2][2] == symbol && board[1][4] == symbol) 
		{
			System.out.println("WE HAVE A WINNER YAY :)");
			printboard(board);
			System.exit(0);
		}
	}

}
