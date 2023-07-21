package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Cavalo extends ChessPiece {
    public Cavalo(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString(){
        return "C";
    }
}
