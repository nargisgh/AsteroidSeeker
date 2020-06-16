package ca.cmpt276.as3.mineseeker.model;

public class BoardSquare {
    private boolean isMine;
    private int mineNearby;

    public BoardSquare(boolean isMine, int mineNearby) {
        this.isMine = isMine;
        this.mineNearby = mineNearby;
    }

    public boolean isMine() {
        return isMine;
    }

    public int getMineNearby() {
        return mineNearby;
    }

    public void setMine(boolean mine) {
        isMine = mine;
    }

    public void setMineNearby(int mineNearby) {
        this.mineNearby = mineNearby;
    }
}
