package chess.piece;

import boardgame.Board;
import boardgame.Position;
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

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position positionAux = new Position(0, 0);

        // Abaixo
        positionAux.setValues(position.getRow() - 1, position.getColumn());
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        // Acima
        positionAux.setValues(position.getRow() + 1, position.getColumn());
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setRow(positionAux.getRow() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        // Esquerda
        positionAux.setValues(position.getRow(), position.getColumn() - 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() - 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        // Direita
        positionAux.setValues(position.getRow(), position.getColumn() + 1);
        while (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
            positionAux.setColumn(positionAux.getColumn() + 1);
        }
        if (getBoard().positionExists(positionAux) && isThereOpponentPiece(positionAux)) {
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
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
