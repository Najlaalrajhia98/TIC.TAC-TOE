import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author LAP-7 
 * 
 *
 */
public class TicTacToe {

    /**
     * 
     *  TikTakToe game using X and O as players and checks the winner.
     * @param args:
     */
    public static void main(String[] args) {

        ArrayList<Integer> slotchoiceArrayList = new ArrayList<Integer>();

        char[][] board = { 
                { '-', '*', '-', '*', '-' }, 
                { ' ', '|', ' ', '|', ' ' }, 
                { ' ', '|', ' ', '|', ' ' },
                { ' ', '|', ' ', '|', ' ' }, 
                { '-', '*', '-', '*', '-' } 
        };

        printBoard(board); // call the function to print the board.
        Scanner scan = new Scanner(System.in);

        boolean valid = false;
        char symbolChoice1;
        char symbolChoice2;
        boolean playStatus = true;


        do {
            // player one choice of symbol
            System.out.println("player 1 please choose a symbol X or O :");
            symbolChoice1 = scan.next().charAt(0);

            if (symbolChoice1 == 'x' || symbolChoice1 == 'X' || symbolChoice1 == 'O' || symbolChoice1 == 'o') {
                valid = true;
            } else {
                valid = false;
                System.out.println("its not a valid symbol enter another one ");
            }
        } while (!valid);

        do { 
            // player two choice of symbol
            System.out.println("player 2 please choose a symbol X or O :");
            symbolChoice2 = scan.next().charAt(0);
            do { 
                // do while loop if player 2 choose symbol of player 1
                if (symbolChoice2 == symbolChoice1) {
                    System.out.println("you enterd a symbol of player 1 plz choose another one:");
                    symbolChoice2 = scan.next().charAt(0);
                }
            } while (symbolChoice2 == symbolChoice1);
            if (symbolChoice2 == 'x' || symbolChoice2 == 'X' || symbolChoice2 == 'O' || symbolChoice2 == 'o') {
                valid = true;
            } else {
                valid = false;
                System.out.println("its not a valid symbol enter another one :");
            }

        } while (!valid);

        System.out.println("player 1 will be playing with  : " + symbolChoice1);
        System.out.println("player 2 will be playing with  : " + symbolChoice2);

        for (int index = 0; index <= 4; index++) {

            System.out.println("Player 1 enter your choice of slots :");
            int slotChoice = scan.nextInt();
            do {
                if (slotChoice > 9 || slotChoice < 1) {
                    System.out.println("Enter a valid number ");
                    slotChoice = scan.nextInt();
                }
            } while (slotChoice > 9 || slotChoice < 1);
            Slotplace(board, slotChoice, symbolChoice1, slotchoiceArrayList);
            printBoard(board); 

            if (index == 4) {
                playStatus = false;
                System.out.println("its a drow ! game over ");

            }

            if (playStatus) {
                System.out.println(" Player 2 enter your choice of slots :");
                int slotChoice1 = scan.nextInt();
                do {
                    if (slotChoice1 > 9 || slotChoice1 < 1) {
                        System.out.println("Enter a valid number ");
                        slotChoice1 = scan.nextInt();
                    }
                } while (slotChoice1 > 9 || slotChoice1 < 1);
                Slotplace(board, slotChoice1, symbolChoice2, slotchoiceArrayList);
                printBoard(board);
            }
        }
    }

    // function to print the board
    public static void printBoard(char[][] board) {

        for (char[] row : board) {
            for (char cloum : row) {

                System.out.print(cloum);

            }

            System.out.println();
        }
    }


    public static void Slotplace(char[][] board, int slotchoice, char player, ArrayList<Integer> slotchoiceArrayList) {
        
        Boolean takenBoolean = false;
        Scanner scan = new Scanner(System.in);
        char symbol = ' ';
        
        if (player == 'X' || player == 'x') 
        {
            symbol = 'X';

        } 
        else if (player == 'O' || player == 'o') 
        {
            symbol = 'O';
        }

        do {

            takenBoolean = false;
            if (!slotchoiceArrayList.contains(slotchoice)) {
                slotchoiceArrayList.add(slotchoice);
                switch (slotchoice) {
                case 1:
                    board[1][0] = symbol;
                    break;
                case 2:
                    board[1][2] = symbol;

                    break;
                case 3:
                    board[1][4] = symbol;
                    break;
                case 4:
                    board[2][0] = symbol;
                    break;
                case 5:
                    board[2][2] = symbol;
                    break;
                case 6:
                    board[2][4] = symbol;
                    break;
                case 7:
                    board[3][0] = symbol;
                    break;
                case 8:
                    board[3][2] = symbol;
                    break;
                case 9:
                    board[3][4] = symbol;
                    break;
                default:
                    System.out.println("ENTER A VALID NUMBER");
                }
            } else if (slotchoiceArrayList.contains(slotchoice)) {
                takenBoolean = true;
                System.out.println("Enter another slot number:");
                slotchoice = scan.nextInt();
            }

        } while (takenBoolean);


        // To check the winner 
        if (board[1][0]== symbol && board[1][2]== symbol && board[1][4]== symbol || 
                board[2][0]== symbol && board[2][2]== symbol && board[2][4]== symbol ||
                board[3][0]== symbol && board[3][2]== symbol && board[3][4]== symbol ||
                board[1][0]== symbol && board[2][0]== symbol && board[3][0]== symbol ||
                board[1][2]== symbol && board[2][2]== symbol && board[3][2]== symbol ||
                board[1][4]== symbol && board[2][4]== symbol && board[3][4]== symbol ||
                board[1][0]== symbol && board[2][2]== symbol && board[3][4]== symbol ||
                board[1][4]== symbol && board[2][2]== symbol && board[3][0]== symbol) 
        {
            System.out.println("We have a winner Yay :) ");
            printBoard(board);
            System.exit(0);
        }

    }

}
