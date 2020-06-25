package ca.cmpt276.as3.asteroidseeker.model;

/*
 * This is a Sinlgeton GameBoard class that stores BoardSquare Objects and allows the manipulation of the manipulation of
 * the Singleton GameBoard instance and BoardSquare objects
 */

import java.util.Random;


public class GameBoard {

    public static final int TEMP_ROW_NUM = 4;
    public static final int TEMP_COL_NUM = 5;
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
    public void removeNumPlayed(){
        NumPlayed = 0;
    }

    public GameBoard(){
        gameBoard = new BoardSquare[numBoardRows][numBoardColumns];
    }

    public int getNumBoardRows() {
        return numBoardRows;
    }

    public int getNumBoardColumns() {
        return numBoardColumns;
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

    public void presets(int numOfAsteroids, int numOfRows, int numOfCols){
        initializeGameBoard(numOfAsteroids, numOfRows, numOfCols);
        distributeAsteroids();
        setNearbyAsteroidCount();
    }

    public void initializeGameBoard(int asteroids, int rows, int cols){
        this.numOfAsteroids = asteroids;
        this.numBoardRows = rows;
        this.numBoardColumns = cols;
        gameBoard = new BoardSquare[rows][cols];
        for(int row = 0 ; row < numBoardRows; row++){
            for(int column = 0; column < numBoardColumns; column++){
                BoardSquare currentSquare = new BoardSquare(row, column);
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
                randomSquare.setToAsteroid();
                asteroidsToDistribute--;
            }
        }
    }

    //method retrieved from: https://stackoverflow.com/questions/363681/how-do-i-generate-random-integers-within-a-specific-range-in-java
    public int getRandomNumber(int max){
        Random rand = new Random();
        return rand.nextInt(max);
    }

    public void changeNearbyAsteroidCount(BoardSquare boardSquare){
        boardSquare.setAsteroid(false);
        boardSquare.setFound(true);
        setNearbyAsteroidCount();
    }

    public void setNearbyAsteroidCount(){
        BoardSquare currentSquare;
        for(int row = 0; row < numBoardRows; row++){
            for(int column = 0; column < numBoardColumns; column++){
                currentSquare = gameBoard[row][column];
                int asteroidsNearby = countAsteroids(currentSquare);
                currentSquare.setAsteroidsNearby(asteroidsNearby);
            }
        }
    }

    public int countAsteroids(BoardSquare boardSquare){
        int rowMines = countRowAsteroids(boardSquare);
        int columnMines = countColumnAsteroids(boardSquare);
        return (rowMines + columnMines);
    }

    public int countColumnAsteroids(BoardSquare boardSquare) {
        int numOfAsteroids = 0;
        int column = boardSquare.getSquareColumn();
        for(int row = 0; row < numBoardRows; row++){
            BoardSquare currentSquare = getSpecificSquare(row, column);
            if(row != boardSquare.getSquareRow()){
                if(currentSquare.getIsAsteroid()){
                    numOfAsteroids++;
                }
            }
        }
        return numOfAsteroids;
    }

    public int countRowAsteroids(BoardSquare boardSquare){
        int numOfAsteriods = 0;
        int row = boardSquare.getSquareRow();
        for(int column = 0; column < numBoardColumns; column++){
            BoardSquare currentSquare = getSpecificSquare(row, column);
            if(column != boardSquare.getSquareColumn()){
                if(currentSquare.getIsAsteroid()){
                    numOfAsteriods++;
                }
            }
        }
        return numOfAsteriods;
    }
}
