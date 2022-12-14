import java.awt.datatransfer.Clipboard;
import java.awt.im.InputMethodHighlight;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.Scanner;



/**
 * 
 */

/**
 * @author LAP-7
 *
 */
public class TICTAC {


	public static void main(String[] args) { 

		// Array to save the symbol for each user 
		ArrayList<Character> sybmolArrayList =new ArrayList<Character>();
		ArrayList<Integer> slotchoiceArrayList= new ArrayList<Integer>();


		char [][] board = { {'-','*','-','*','-'},
				{' ','|',' ','|',' '},
				{' ','|',' ','|',' '},
				{' ','|',' ','|',' '},
				{'-','*','-','*','-'} 
		};

		printboard(board); // call the function
		Scanner scan = new Scanner(System.in);

		// allows the user to choose X or O
		System.out.println("player 1 please choose a symbol X or O :");
		char symbolchoice1 = scan.next().charAt(0);
		sybmolArrayList.add(symbolchoice1);


		System.out.println("player 2 please choose a symbol X or O :");
		char symbolchoice2 = scan.next().charAt(0);
		 do{
			 if (sybmolArrayList.contains(symbolchoice1)) {
		 }
			System.out.print("change your symbol you enterd a chosen symbol:");
			
			symbolchoice2 = scan.next().charAt(0);
		}while (sybmolArrayList.contains(symbolchoice2));
		

		System.out.println("player 1 will be playing with  : "+ symbolchoice1);
		System.out.println("player 2 will be playing with  : "+ symbolchoice2);

		for (int i=0; i<=4;i++) {
			// player 1 might choose x or o (capital or small letters)
			if (symbolchoice1=='X'|| symbolchoice1=='x'||symbolchoice1=='o'||symbolchoice1=='O'  )
				// USER X OR O WILL BE PLAYING 
				System.out.println("Player 1 enter your choice of slots :");
			int slotchoice= scan.nextInt();
			Slotplace(board,slotchoice, symbolchoice1,slotchoiceArrayList);

			printboard(board); // Prints the result on the Board 

			if (symbolchoice2=='O'|| symbolchoice2=='o'||symbolchoice2=='x'|| symbolchoice2=='X')
				System.out.println(" Player 2 enter your choice of slots :");
			int slotchoice1= scan.nextInt();
			Slotplace(board,slotchoice1, symbolchoice2,slotchoiceArrayList);

			printboard(board);
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
	public static void Slotplace(char [][] board, int slotchoice, char user,ArrayList<Integer> slotchoiceArrayList) {
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
				takenBoolean= false;
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
