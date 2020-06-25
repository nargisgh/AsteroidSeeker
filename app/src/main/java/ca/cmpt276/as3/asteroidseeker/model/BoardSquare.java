package ca.cmpt276.as3.asteroidseeker.model;

/*
* This is a BoardSquare Class that stores data for each square in the Asteroid Seeker Game such as
* whether the asteroid was found, whether it was
*/

public class BoardSquare {
    private boolean isAsteroid;
    private boolean isFound;
    private int asteroidsNearby;
    private int squareRow;
    private int squareColumn;

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

    public int getSquareColumn() {
        return squareColumn;
    }

    public void setToAsteroid(){
        this.isAsteroid = true;
    }

    public boolean getIsAsteroid() {
        return isAsteroid;
    }

    public boolean isFound() {
        return isFound;
    }

    public void setFound(boolean found) {
        isFound = found;
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
