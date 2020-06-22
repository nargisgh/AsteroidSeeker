package ca.cmpt276.as3.mineseeker.model;

/*
 * This is a GameBoard class that stores BoardSquare Objects
 */

import java.util.Random;

public class GameBoard {

    public static final int TEMP_ROW_NUM = 4;
    public static final int TEMP_COL_NUM = 5;
    public static final int TEMP_ASTEROID_COUNT = 10;
    private BoardSquare[][] gameBoard;
    private static GameBoard instance;
    private int numBoardRows = TEMP_ROW_NUM;
    private int numBoardColumns = TEMP_COL_NUM;
    private int numOfAsteroids = TEMP_ASTEROID_COUNT;

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

    public void presets(){
        initializeGameBoard();
        distributeAsteroids();
        setNearbyAsteroidCount();

    }

    public void initializeGameBoard(){
        gameBoard = new BoardSquare[numBoardRows][numBoardColumns];
        for(int row = 0 ; row < numBoardRows; row++){
            for(int column = 0; column < numBoardColumns; column++){
                BoardSquare currentSquare = new BoardSquare();
                gameBoard[row][column] = currentSquare;
            }
        }
    }

    public void distributeAsteroids(){
        int asteroidsToDistribute = numOfAsteroids;
        while(asteroidsToDistribute > 0){
            int randomRow = getRandomNumber(numBoardRows);
            int randomCol = getRandomNumber(numBoardColumns);
            BoardSquare randomSquare = gameBoard[randomRow][randomCol];
            if(!randomSquare.getIsAsteroid()){
                randomSquare.setAsteroid();
                asteroidsToDistribute--;
            }
        }
    }

    //method retrieved from: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    public int getRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void setNearbyAsteroidCount(){
        for(int row = 0; row < numBoardRows; row++){
            for(int column = 0; column < numBoardColumns; column++){
                BoardSquare currentSquare = gameBoard[row][column];
                int asteroidsNearby = countAsteroids(currentSquare);
                currentSquare.setAsteroidsNearby(asteroidsNearby);
            }
        }
    }

    public int countAsteroids(BoardSquare square){
        int squareColumn = square.getSquareColumn();
        int squareRow = square.getSquareRow();
        int rowMines = countRowAsteroids(square);
        int columnMines = countColumnAsteroids(square);
        if(square.getIsAsteroid()){
            //due to double counting
            rowMines--;
        }
        return rowMines + columnMines;
    }

    private int countColumnAsteroids(BoardSquare square) {
        int currentColumn = square.getSquareColumn();
        int countMines = 0;
        for(int currentRow = 0; currentRow<numBoardRows; currentRow++){
            BoardSquare currentSquare = getSpecificSquare(currentRow, currentColumn);
            if(currentSquare.getIsAsteroid()){
                countMines++;
            }
        }
        return countMines;
    }

    private int countRowAsteroids(BoardSquare square) {
        int currentRow = square.getSquareRow();
        int countMines = 0;
        for(int currentColumn = 0; currentColumn < numBoardColumns; currentColumn++){
            BoardSquare currentSquare = getSpecificSquare(currentRow, currentColumn);
            if(currentSquare.getIsAsteroid()){
                countMines++;
            }
        }
        return countMines;
    }
}
