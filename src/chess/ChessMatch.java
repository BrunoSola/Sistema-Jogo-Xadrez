package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.piece.*;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        this.board = new Board(8,8);
        initialSetup();
    }
    public ChessPiece[][] getPieces(){
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getColumns();j++){
                mat[i][j] = (ChessPiece) board.piece(i,j);
            }
        }
        return mat;
    }

    private void initialSetup(){
        board.placePiece(new Torre(board, Color.WHITE), new Position(0,0));
        board.placePiece(new Cavalo(board, Color.WHITE), new Position(0,1));
        board.placePiece(new Bispo(board, Color.WHITE), new Position(0,2));
        board.placePiece(new Rei(board, Color.WHITE), new Position(0,3));
        board.placePiece(new Dama(board, Color.WHITE), new Position(0,4));
        board.placePiece(new Bispo(board, Color.WHITE), new Position(0,5));
        board.placePiece(new Cavalo(board, Color.WHITE), new Position(0,6));
        board.placePiece(new Torre(board, Color.WHITE), new Position(0,7));
        for (int i = 1; i == 1;i++){
            for (int j = 0; j < board.getColumns();j++){
                board.placePiece(new Peao(board, Color.WHITE), new Position(i,j));
            }
        }
        for (int i = 6; i == 6;i++){
            for (int j = 0; j < board.getColumns();j++){
                board.placePiece(new Peao(board, Color.BLACK), new Position(i,j));
            }
        }
        board.placePiece(new Torre(board, Color.WHITE), new Position(7,0));
        board.placePiece(new Cavalo(board, Color.WHITE), new Position(7,1));
        board.placePiece(new Bispo(board, Color.WHITE), new Position(7,2));
        board.placePiece(new Rei(board, Color.WHITE), new Position(7,3));
        board.placePiece(new Dama(board, Color.WHITE), new Position(7,4));
        board.placePiece(new Bispo(board, Color.WHITE), new Position(7,5));
        board.placePiece(new Cavalo(board, Color.WHITE), new Position(7,6));
        board.placePiece(new Torre(board, Color.WHITE), new Position(7,7));
    }
}
