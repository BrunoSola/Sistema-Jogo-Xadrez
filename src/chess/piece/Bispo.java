package chess.piece;

import boardgame.Board;
import boardgame.Position;
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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        //Esquerda - Acima
        positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() - 1, positionAux.getColumn() - 1);
        }
        //Esquerda - Abaixo
        positionAux.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() + 1, positionAux.getColumn() - 1);
        }
        //Direita - Acima
        positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() - 1, positionAux.getColumn() + 1);
        }
        //Direita - Abaixo
        positionAux.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setValues(positionAux.getRow() + 1, positionAux.getColumn() + 1);
        }
        return mat;
    }
}
