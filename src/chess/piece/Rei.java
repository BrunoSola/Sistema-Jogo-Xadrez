package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class Rei extends ChessPiece {

    private ChessMatch chessMatch;
    public Rei(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public String toString(){
        return "R";
    }

    private boolean canMove(Position position){
        ChessPiece pieceAux = (ChessPiece) getBoard().piece(position);
        return pieceAux == null || pieceAux.getColor() != getColor();
    }

    private boolean testRookCastling(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        return p != null && p instanceof Torre && p.getColor() == getColor() && p.getMoveCount() == 0;
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

        //Movimento Especial Roque(Castling)
        if (getMoveCount() == 0 && !chessMatch.getCheck()){
            //Movimento Especial Roque(Castling) - Lado do rei
            Position positionTorre1 = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(positionTorre1)){
                Position positionAux1 = new Position(position.getRow(), position.getColumn() + 1);
                Position positionAux2 = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(positionAux1) == null && getBoard().piece(positionAux2) == null){
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            //Movimento Especial Roque(Castling) - Lado da Dama(rainha)
            Position positionTorre2 = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(positionTorre2)){
                Position positionAux1 = new Position(position.getRow(), position.getColumn() - 1);
                Position positionAux2 = new Position(position.getRow(), position.getColumn() - 2);
                Position positionAux3 = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(positionAux1) == null && getBoard().piece(positionAux2) == null && getBoard().piece(positionAux3) == null){
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }
        return mat;
    }
}
