package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Torre extends ChessPiece {
    public Torre(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "T";
    }
}
