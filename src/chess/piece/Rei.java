package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Rei extends ChessPiece {
    public Rei(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "R";
    }
}
