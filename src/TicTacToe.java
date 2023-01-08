import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyStore.TrustedCertificateEntry;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;

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

        boolean playStatus = true;
        String DATA_FILE_PATH="resume/game_state.txt";
        boolean resume = false;


        // Check if the program was started with the "resume" argument
        if (args.length > 0 && args[0].equals("resume")) {
            // If the program was started with the "resume" argument, set the resume variable to true
            resume = true;
        }



        ArrayList<Integer> slotchoiceArrayList = new ArrayList<Integer>();

        char[][] board = { 
                { '-', '*', '-', '*', '-' }, 
                { ' ', '|', ' ', '|', ' ' }, 
                { ' ', '|', ' ', '|', ' ' },
                { ' ', '|', ' ', '|', ' ' }, 
                { '-', '*', '-', '*', '-' } 
        };

        Scanner scan = new Scanner(System.in);
        System.out.println("Would you like to start a new game or resume the previous one ? (new/resume)");
        String choice = scan.nextLine();
        char symbolChoice1 = 'x';
        char symbolChoice2 = 'o';
        if (choice.equals("resume")) {
            // If the player chooses to resume the game, load the saved game state from a file
            loadGame(board, slotchoiceArrayList, DATA_FILE_PATH, true);
            printBoard(board);
            playGame(playStatus, scan, board, symbolChoice1, symbolChoice2, slotchoiceArrayList, DATA_FILE_PATH);

        } 
        else {

            // Otherwise, start a new game

            printBoard(board);

            boolean valid = false;

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

                if (symbolChoice2 == 'x' || symbolChoice2 == 'X' || symbolChoice2 == 'O' || symbolChoice2 == 'o') 
                {
                    valid = true;
                } 
                else 
                {
                    valid = false;
                    System.out.println("its not a valid symbol enter another one :");
                }

            } while (!valid);

            System.out.println("player 1 will be playing with  : " + symbolChoice1);
            System.out.println("player 2 will be playing with  : " + symbolChoice2);

            System.out.println("playStatus before loop: " + playStatus);

            playGame(playStatus, scan, board, symbolChoice1, symbolChoice2, slotchoiceArrayList, DATA_FILE_PATH);


            boolean draw = true;  // Flag to indicate if the game is a draw
            for (int row = 1; row < 4; row++) 
            {
                for (int column = 0; column < 5; column += 2) 
                {
                    if (board[row][column] == ' ') 
                    {  // If an empty slot is found
                        draw = false;  // Set the flag to false
                    }
                }
            }
            if (draw) 
            {  // If the game is a draw
                playStatus = false;  // Set the playStatus to false to end the game
                System.out.println("It's a draw! Game over.");
            }

        } 
    }



    // function to print the board
    public static void playGame(boolean playStatus,Scanner scan,char [][] board,char symbolChoice1,char symbolChoice2,
            ArrayList<Integer> slotchoiceArrayList, String DATA_FILE_PATH) {
        while (playStatus) {
            boolean gameOver = false; 
            int turns = 0;  // variable to keep track of the number of turns
            int currentPlayer = 1;  // variable to keep track of the current player

            while (turns < 9 && !gameOver) 
            {
                playStatus=true;
                if (currentPlayer == 1) 
                {
                    System.out.println("Player 1 enter your choice of slots :");
                    int slotChoice = scan.nextInt();
                    turns++;
                    // currentPlayer = 2;

                    do {
                        if (slotChoice > 9 || slotChoice < 1) 
                        {
                            System.out.println("Enter a valid number ");
                            slotChoice = scan.nextInt();
                        }
                    } while (slotChoice > 9 || slotChoice < 1);

                    Slotplace(board, slotChoice, symbolChoice1, slotchoiceArrayList);
                    saveGame(board, slotchoiceArrayList, DATA_FILE_PATH,true);
                    printBoard(board);

                    if (checkWinner(board, symbolChoice1)) 

                    {  
                        playStatus=false;
                        System.out.println("Player " + symbolChoice1 + " has won the game! Game over.");
                        gameOver = true;
                        // System.out.println("Player " + symbolChoice1 + " has won the game! Game over.");
                        //break;
                    }
                    currentPlayer = 2;
                }
                // Player 2 turn 
                else 
                {
                    System.out.println(" Player 2 enter your choice of slots :");
                    int slotChoice1 = scan.nextInt();
                    turns++;


                    do 
                    {
                        if (slotChoice1 > 9 || slotChoice1 < 1) 
                        {
                            System.out.println("Enter a valid number ");
                            slotChoice1 = scan.nextInt();
                        }
                    } while (slotChoice1 > 9 || slotChoice1 < 1);

                    Slotplace(board, slotChoice1, symbolChoice2, slotchoiceArrayList);
                    saveGame(board, slotchoiceArrayList, DATA_FILE_PATH,true);
                    printBoard(board);

                    System.out.println(checkWinner(board, symbolChoice2)); 

                    if (checkWinner(board, symbolChoice2)) 
                    {
                        playStatus=false;
                        System.out.println("Player " + symbolChoice2 + " has won the game! Game over.");
                        gameOver = true;

                    }
                    currentPlayer = 1;

                }
            }
        }
    }
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
            symbol = player;

        } 
        else if (player == 'O' || player == 'o') 
        {
            symbol = player;
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

    }
    public static void saveGame(char[][] board, ArrayList<Integer> slotchoiceArrayList, String DATA_FILE_PATH,boolean playStatus) {
        try {
            playStatus = true;
            // Open the file for writing
            FileWriter writer = new FileWriter(DATA_FILE_PATH);

            // Write the board to the file
            for (int row = 0; row < board.length; row++) {
                for (int column = 0; column < board[row].length; column++) {
                    writer.write(board[row][column]);
                }
                writer.write('\n');
            }

            // Write the list of slot choices to the file
            for (Integer slot : slotchoiceArrayList) {
                writer.write(slot + " ");
            }
            writer.write('\n');

            // Close the file
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    // Function to load a saved game from a file
    public static void loadGame(char[][] board, ArrayList<Integer> slotchoiceArrayList , String DATA_FILE_PATH,boolean playStatus) {
        try {
            
            playStatus = true;
            File file = new File(DATA_FILE_PATH);
            Scanner fileScanner = new Scanner(file);

            // Read the board state from the file
            for (int row = 0; row < 5; row++) {
                String line = fileScanner.nextLine();
                for (int column = 0; column < 5; column++) {
                    board[row][column] = line.charAt(column);
                }
            }

            fileScanner.reset();  // Reset the scanner to the beginning of the file
           
            // Read the chosen slots from the file
            while (fileScanner.hasNextInt()) {
                int slot = fileScanner.nextInt();
                slotchoiceArrayList.add(slot);

            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: Could not find file " +DATA_FILE_PATH);
        }
    }

    public static boolean checkWinner(char[][] board, char symbol) {
        // Check for wins in rows


        if (board[1][0]== symbol && board[1][2]== symbol && board[1][4]== symbol || 
                board[2][0]== symbol && board[2][2]== symbol && board[2][4]== symbol ||
                board[3][0]== symbol && board[3][2]== symbol && board[3][4]== symbol ||
                board[1][0]== symbol && board[2][0]== symbol && board[3][0]== symbol ||
                board[1][2]== symbol && board[2][2]== symbol && board[3][2]== symbol ||
                board[1][4]== symbol && board[2][4]== symbol && board[3][4]== symbol ||
                board[1][0]== symbol && board[2][2]== symbol && board[3][4]== symbol ||
                board[1][4]== symbol && board[2][2]== symbol && board[3][0]== symbol) 
        {
            return true;
        }
        // If no wins are found, return false
        return false;
    }
}
