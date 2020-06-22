package ca.cmpt276.as3.mineseeker.model;

/*
* This is a BoardSquare Class that stores data for each square in the minesweeper game
*/

public class BoardSquare {
    private boolean isMine;
    private int minesNearby;
    private int squareRow;
    private int squareColumn;

    public BoardSquare(boolean isMine, int mineNearby) {
        this.isMine = isMine;
        this.minesNearby = mineNearby;
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
    public void setToMine(){
        this.isMine = true;
    }

    public boolean getIsMine() {
        return isMine;
    }

    public int getMineNearby() {
        return minesNearby;
    }

    public void setMinesNearby(int minesNearby) {
        this.minesNearby = minesNearby;
    }

    public void changeToMine() {
        isMine = true;
    }
}
