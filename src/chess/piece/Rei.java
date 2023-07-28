package chess.piece;

import boardgame.Board;
import boardgame.Position;
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

    private boolean canMove(Position position){
        ChessPiece pieceAux = (ChessPiece) getBoard().piece(position);
        return pieceAux == null || pieceAux.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        Position positionAux = new Position(0, 0);

        //Acima
        positionAux.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Abaixo
        positionAux.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Esquerda
        positionAux.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Direita
        positionAux.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }

        //Acima - Esquerda
        positionAux.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Abaixo - Esquerda
        positionAux.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Acima - Direita
        positionAux.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        //Abaixo - Direita
        positionAux.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(positionAux) && canMove(positionAux)){
            mat[positionAux.getRow()][positionAux.getColumn()] = true;
        }
        return mat;
    }
}
