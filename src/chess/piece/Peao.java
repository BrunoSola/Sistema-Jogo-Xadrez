package chess.piece;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.enums.Color;

public class Peao extends ChessPiece {

    private ChessMatch chessMatch;
    public Peao(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
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
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2) && getMoveCount() ==  0) {
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

            // Movimento Especial - en passant
            if (position.getRow() == 3){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() - 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() - 1][right.getColumn()] = true;
                }
            }
        }
        else {
            positionAux.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux)){
                mat[positionAux.getRow()][positionAux.getColumn()] = true;
            }
            positionAux.setValues(position.getRow() + 2, position.getColumn());
            Position positionAux2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(positionAux) && !getBoard().thereIsAPiece(positionAux) && getBoard().positionExists(positionAux2) && !getBoard().thereIsAPiece(positionAux2) && getMoveCount() == 0) {
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

            // Movimento Especial - en passant
            if (position.getRow() == 4){
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left) && isThereOpponentPiece(left) && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()){
                    mat[left.getRow() + 1][left.getColumn()] = true;
                }
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right) && isThereOpponentPiece(right) && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()){
                    mat[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }
        return mat;
    }
}
