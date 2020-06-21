package ca.cmpt276.as3.mineseeker.model;

/*
* This is a GameBoard class that stores BoardSquare Objects
*/

public class GameBoard {

    private static GameBoard instance;
    private BoardSquare[][] gameBoard;
    private int numBoardRows;
    private int numBoardColumns;

    private static int NumPlayed = 0;

    public int getNumPlayed() {
        return NumPlayed;
    }

    public void addNumPlayed(){
        NumPlayed++;
    }

    public GameBoard(){
        gameBoard = new BoardSquare[numBoardRows][numBoardColumns];
    }

    public static GameBoard getInstance(){
        if(instance == null){
            instance = new GameBoard();
        }
        return instance;
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

    public BoardSquare getSpecificSquare(int row, int column){
        return gameBoard[row][column];
    }

    public void setToMine(int row, int column){
        BoardSquare newMine = gameBoard[row][column];
        newMine.changeToMine();
    }

    public void countMinesNearby(int rows, int cols){
        int minesNearSquare = 0;
        BoardSquare currentSquare = gameBoard[rows][cols];
        for(int i = rows - 1; i < rows + 2; i++){
            for(int j = cols - 1; j < cols + 2; j++){
                if(checkForMine(i,j)){
                    minesNearSquare++;
                }
            }
        }
        currentSquare.setMinesNearby(minesNearSquare);
    }

    public boolean checkForMine(int row, int column){
        if(isBoardSquareInBounds(row, column)){
            BoardSquare boardSquare = gameBoard[row][column];
            if(boardSquare.getIsMine()){
                return true;
            } else{
                return false;
            }
        } else{
            return false;
        }
    }

    public boolean isBoardSquareInBounds(int row, int column){
        if(row < 0 || row >= numBoardColumns){
            return false;
        }else if (column < 0 || column >= numBoardColumns){
            return false;
        }else{
            return true;
        }
    }
}
