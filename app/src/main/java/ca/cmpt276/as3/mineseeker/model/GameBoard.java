package ca.cmpt276.as3.mineseeker.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameBoard implements Iterable<BoardSquare>{
    private List<BoardSquare> boardSquares = new ArrayList<>();
    private static GameBoard instance;
    private int numBoardRows;
    private int numBoardColumns;
    private int numBoardElements;

    private GameBoard(){
    }

    public static GameBoard getInstance(){
        if(instance == null){
            instance = new GameBoard();
            instance.numBoardElements = 0;
        }
        return instance;
    }
    public void addBoardSquare(BoardSquare square){
        boardSquares.add(square);
        numBoardElements++;
    }

    @Override
    public Iterator<BoardSquare> iterator() {
        return boardSquares.iterator();
    }

    public List<BoardSquare> getBoardSquares() {
        return boardSquares;
    }

    public BoardSquare getSpecificBoardSquare(int index){
        return this.boardSquares.get(index);
    }
}
