package ca.cmpt276.as3.mineseeker.model;

/*
* This is a BoardSquare Class that stores data for each square in the minesweeper game
*/

public class BoardSquare {
    private boolean isAsteroid;
    private int asteroidsNearby;
    private int squareRow;
    private int squareColumn;

    public BoardSquare() {
        this.isAsteroid = false;
        this.asteroidsNearby = 0;
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
    public void setAsteroid(){
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

}
