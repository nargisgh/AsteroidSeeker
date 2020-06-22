package ca.cmpt276.as3.mineseeker.model;

/*
 * This is a GameBoard class that stores BoardSquare Objects
 */

import java.util.Random;

public class GameBoard {

    private BoardSquare[][] gameBoard;
    private static GameBoard instance;
    private int numBoardRows;
    private int numBoardColumns;
    private int numOfAsteroids;

    private static int NumPlayed = 0;

    public static GameBoard getInstance(){
        if(instance == null){
            instance = new GameBoard();
        }
        return instance;
    }

    public int getNumPlayed() {
        return NumPlayed;
    }

    public void addNumPlayed(){
        NumPlayed++;
    }

    public GameBoard(){
        gameBoard = new BoardSquare[numBoardRows][numBoardColumns];
    }


    public BoardSquare[][] getGameBoard() {
        return this.gameBoard;
    }

    public void setNumBoardRows(int numBoardRows) {
        this.numBoardRows = numBoardRows;
    }

    public void setNumBoardColumns(int numBoardColumns) {
        this.numBoardColumns = numBoardColumns;
    }

    public void setNumOfAsteroids(int numOfAsteroids) {
        this.numOfAsteroids = numOfAsteroids;
    }

    public int getNumOfAsteroids(){
        return this.numOfAsteroids;
    }

    public BoardSquare getSpecificSquare(int row, int column){
        return gameBoard[row][column];
    }

    public void distributeMines(){
        int currentMines = numOfAsteroids;
        for(int currentRow = 0; currentRow < numBoardRows; currentRow++){
            for(int currentColumn = 0; currentColumn < numBoardColumns; currentColumn++){
                BoardSquare currentSquare = gameBoard[currentRow][currentColumn];
                if(generateMines(currentMines)){
                    currentSquare.setToMine();
                    currentMines--;
                }
            }
        }
    }

    public boolean generateMines(int numOfMines){
        boolean makeMine = false;
        int randomNumber = getRandomNumber(812408);
        if(numOfMines < (numBoardColumns * numBoardRows)/2){
            if((numOfMines % 4) == 0){
                makeMine = true;
            }
        }
        else{
            if((numOfMines % 2) == 0){
                makeMine = true;
            }
        }
        return makeMine;
    }

    //method retrieved from: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    public int getRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public int countMines(BoardSquare square){
        int squareColumn = square.getSquareColumn();
        int squareRow = square.getSquareRow();
        int rowMines = countRowMines(square);
        int columnMines = countColumnMines(square);
        if(square.getIsMine()){
            //due to double counting
            rowMines--;
        }
        return rowMines + columnMines;
    }

    private int countColumnMines(BoardSquare square) {
        int currentColumn = square.getSquareColumn();
        int countMines = 0;
        for(int currentRow = 0; currentRow<numBoardRows; currentRow++){
            BoardSquare currentSquare = getSpecificSquare(currentRow, currentColumn);
            if(currentSquare.getIsMine()){
                countMines++;
            }
        }
        return countMines;
    }

    private int countRowMines(BoardSquare square) {
        int currentRow = square.getSquareRow();
        int countMines = 0;
        for(int currentColumn = 0; currentColumn < numBoardColumns; currentColumn++){
            BoardSquare currentSquare = getSpecificSquare(currentRow, currentColumn);
            if(currentSquare.getIsMine()){
                countMines++;
            }
        }
        return countMines;
    }
}
