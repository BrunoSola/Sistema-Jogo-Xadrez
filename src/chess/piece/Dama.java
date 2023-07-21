package chess.piece;

import boardgame.Board;
import chess.ChessPiece;
import chess.enums.Color;

public class Dama extends ChessPiece {
    public Dama(Board board, Color color) {
        super(board, color);
    }
    @Override
    public String toString(){
        return "D";
    }
}
