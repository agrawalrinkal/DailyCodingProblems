package impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private char[][] board; // stores the board status
    private List<Character> positiveDiagonal;
    private List<Character> negativeDiagonal;
    private int boardSize;
    private int emptyMoves;

    /**
     * Initializes all the paramters and the board
     * @param size:  size of the board
     */
    TicTacToe(int size) {
        this.boardSize = size;
        this.initializeGame();
    }

    public void initializeGame() {
        this.emptyMoves = this.boardSize * this.boardSize;
        this.board = new char[this.boardSize][this.boardSize];
        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                this.board[i][j] = '_';
            }
        }

        this.positiveDiagonal = new ArrayList<>();
        this.negativeDiagonal = new ArrayList<>();
    }

    /**
     * Makes a player move if valid else return false
     * adds the move to diagonal list of it falls on a diagonal
     * checks if player won, returns true if it does, else returns false
     * @param x : x coordinate of the move
     * @param y : y coordinate of the move
     * @return
     */
    public boolean makePlayerMove(int x, int y) {
        if(this.board[x][y] != '_') return false;
        this.board[x][y] = 'o';
        this.emptyMoves--;
        addToDiagonal(x, y, 'o');

        if(this.checkIfWon(x, y, 'o')) return true;
        return false;
    }

    /**
     * AI first checks if it can make a move which would make it won
     * Then it checks if it can block a move for the player
     * If it cannot win or block, it make a move at first empty square
     * @return
     */
    public boolean makeAIMove() {
        for(int i = 0; i < this.boardSize; i++) {
            for (int j = 0; j < this.boardSize; j++) {
                if (board[i][j] == '_') {

                    // check if AI can make a move and won
                    this.board[i][j] = 'x';
                    this.addToDiagonal(i, j, 'x');
                    this.emptyMoves--;
                    if (this.checkIfWon(i, j, 'x')) {
                        this.board[i][j] = 'x';
                        this.addToDiagonal(i, j, 'x');
                        return true;
                    }
                    this.board[i][j] = '_';
                    this.removeFromDiagonal(i, j, 'x');
                    this.emptyMoves++;
                }
            }
        }

        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                if(board[i][j] == '_') {

                    // check if AI can block a move for the player
                    this.board[i][j] = 'o';
                    this.addToDiagonal(i, j, 'o');
                    this.emptyMoves--;
                    if (this.checkIfWon(i, j, 'o')) {
                        this.board[i][j] = 'x';
                        this.addToDiagonal(i, j, 'x');
                        if (this.checkIfWon(i, j, 'x')) return true;
                        return false;
                    }
                    this.board[i][j] = '_';
                    this.removeFromDiagonal(i, j, 'o');
                    this.emptyMoves++;
                }
            }
        }

        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                if(board[i][j] == '_') {
                    this.board[i][j] = 'x';
                    this.emptyMoves--;
                    addToDiagonal(i, j, 'x');
                    if (this.checkIfWon(i, j, 'x')) return true;
                    return false;
                }
            }
        }
        return false;
    }

    /**
     * Adds the player sign in the diagonal given the position
     * @param i
     * @param j
     * @param sign
     */
    private void addToDiagonal(int i, int j, char sign) {
        if(i == j) this.positiveDiagonal.add(sign);
        if(i + j == this.boardSize - 1) this.negativeDiagonal.add(sign);
    }

    /**
     * Removes the player sign from the diagonals given the position
     * @param i
     * @param j
     * @param sign
     */
    private void removeFromDiagonal(int i, int j, char sign) {
        if(i == j) this.positiveDiagonal.remove(Character.valueOf(sign));
        if(i + j == this.boardSize - 1) this.negativeDiagonal.remove(Character.valueOf(sign));
    }

    /**
     * Prints the board
     */
    public void printBoard() {
        System.out.println();
        for(int i = 0; i < this.boardSize; i++) {
            for(int j = 0; j < this.boardSize; j++) {
                if(j == this.boardSize - 1) System.out.print(this.board[i][j]);
                else System.out.print(this.board[i][j] + "|");
            }
            System.out.println();
        }
    }

    /**
     * Checks if the player given the sign has wont he game
     * Check the row, column and both the diagonals
     * @param x
     * @param y
     * @param sign
     * @return
     */
    private boolean checkIfWon(int x, int y, char sign) {
        // check row
        for(int j = 0; j < this.boardSize; j++) {
            if(this.board[x][j] != sign) break;
            if(j == this.boardSize - 1) return true;
        }

        // check col
        for(int i = 0; i < this.boardSize; i++) {
            if(this.board[i][y] != sign) break;
            if(i == this.boardSize - 1) return true;
        }

        // check both diagonals
        if(sign == 'o') {
            return checkDiagonals('x');
        } else {
            return checkDiagonals('o');
        }
    }

    /**
     * Checks if move is valid given the position
     * @param x
     * @param y
     * @return
     */
    public boolean isValidMove(int x, int y) {
        return this.board[x][y] == '_';
    }

    /**
     * checks if the board is full or not
     *
     * @return
     */
    public boolean isBoardFull() {
        return this.emptyMoves <= 1;
    }

    /**
     * Checks the diagonals if the player has won given the sign
     * @param sign
     * @return
     */
    private boolean checkDiagonals(char sign) {
        if(this.positiveDiagonal.size() == this.boardSize && !this.positiveDiagonal.contains(sign)) return true;
        if(this.negativeDiagonal.size() == this.boardSize && !this.negativeDiagonal.contains(sign)) return true;
        return false;
    }


    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(3);
        Scanner scan = new Scanner(System.in);

        while(!game.isBoardFull()) {
            game.printBoard();
            System.out.println("Enter your move: ");
            int i = scan.nextInt();
            int j = scan.nextInt();
            if(i < 0 || j < 0 || i >= game.boardSize || j >= game.boardSize || !game.isValidMove(i, j)) {
                System.out.println("Please make a valid move");
                continue;
            }

            boolean didPlayerWon = game.makePlayerMove(i, j);
            if(didPlayerWon) {
                game.printBoard();
                System.out.println("YOU WON!!!");
                break;
            }
            boolean didAIWon = game.makeAIMove();
            if(didAIWon) {
                game.printBoard();
                System.out.println("AI WON!!!");
                break;
            }
            if(game.isBoardFull()) {
                game.printBoard();
                System.out.println("No One WON, Do you want to play again? (y/n)");
                String playAgain = scan.next();
                if(playAgain.equals("y")) game.initializeGame();
                else break;
            }
        }


    }

}
