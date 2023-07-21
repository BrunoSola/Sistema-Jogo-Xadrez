package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Peao extends ChessPiece {
    public Peao(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "P";
    }
}
