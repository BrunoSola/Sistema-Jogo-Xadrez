package chess.piece;

import boardgame.Board;
import boardgame.Position;
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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        if (getColor() == Color.WHITE){
            positionAux.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() - 2, position.getColumn());
            Position positionAux2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2)) {
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
        }
        else {
            positionAux.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() + 2, position.getColumn());
            Position positionAux2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2)) {
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
        }
        return mat;
    }
}
