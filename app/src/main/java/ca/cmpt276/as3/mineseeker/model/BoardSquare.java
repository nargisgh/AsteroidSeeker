package ca.cmpt276.as3.mineseeker.model;

/*
* This is a BoardSquare Class that stores data for each square in the minesweeper game
*/

public class BoardSquare {
    private boolean isAsteroid;
    private boolean isFound;
    private int asteroidsNearby;
    private int squareRow;
    private int squareColumn;

    public BoardSquare() {
        this.isAsteroid = false;
        this.asteroidsNearby = 0;
    }

    public BoardSquare(int squareRow, int squareColumn) {
        this.isAsteroid = false;
        this.isFound = false;
        this.asteroidsNearby = 0;
        this.squareRow = squareRow;
        this.squareColumn = squareColumn;
    }

    public int getSquareRow() {
        return squareRow;
    }

    public void setSquareRow(int squareRow) {
        this.squareRow = squareRow;
    }

    public int getSquareColumn() {
        return squareColumn;
    }

    public void setSquareColumn(int squareColumn) {
        this.squareColumn = squareColumn;
    }
    public void setToAsteroid(){
        this.isAsteroid = true;
    }

    public boolean getIsAsteroid() {
        return isAsteroid;
    }

    public int getAsteroidsNearby() {
        return asteroidsNearby;
    }

    public void setAsteroidsNearby(int asteroidsNearby) {
        this.asteroidsNearby = asteroidsNearby;
    }

    public void setAsteroid(boolean isAsteroid) {
        this.isAsteroid = isAsteroid;
    }
}
