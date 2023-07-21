package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Bispo extends ChessPiece {
    public Bispo(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "B";
    }
}
