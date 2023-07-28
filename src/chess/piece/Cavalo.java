package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.enums.Color;

public class Cavalo extends ChessPiece {
    public Cavalo(Board board, Color color) {
        super(board, color);
    }

    private boolean canMove(Position position){
        ChessPiece pieceAux = (ChessPiece) getBoard().piece(position);
        return pieceAux == null || pieceAux.getColor() != getColor();
    }

    @Override
    public String toString(){
        return "C";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        //Acima(2x) - Direita
        positionAux.setValues(position.getRow() - 2, position.getColumn() + 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Acima(2x) - Esquerda
        positionAux.setValues(position.getRow() - 2, position.getColumn() - 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Abaixo(2x) - Direita
        positionAux.setValues(position.getRow() + 2, position.getColumn() + 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Abaixo(2x) - Esquerda
        positionAux.setValues(position.getRow() + 2, position.getColumn() - 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Esquerda(2x) - Acima
        positionAux.setValues(position.getRow() - 1, position.getColumn() - 2);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Esquerda(2x) - Abaixo
        positionAux.setValues(position.getRow() + 1, position.getColumn() - 2);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Direita(2x) - Acima
        positionAux.setValues(position.getRow() - 1, position.getColumn() + 2);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Direita(2x) - Abaixo
        positionAux.setValues(position.getRow() + 1, position.getColumn() + 2);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        return mat;
    }
}
