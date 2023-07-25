package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exceptions.ChessException;
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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targerPosition){
        Position source = sourcePosition.toPosition();
        Position target = targerPosition.toPosition();
        validateSourcePosition(source);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("Não tem peça na posição inicial.");
        }
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup(){
        placeNewPiece('a', 8, new Torre(board, Color.WHITE));
        placeNewPiece('b',8,new Cavalo(board, Color.WHITE));
        placeNewPiece('c',8,new Bispo(board, Color.WHITE));
        placeNewPiece('d',8,new Rei(board, Color.WHITE));
        placeNewPiece('e',8,new Dama(board, Color.WHITE));
        placeNewPiece('f',8,new Bispo(board, Color.WHITE));
        placeNewPiece('g',8,new Cavalo(board, Color.WHITE));
        placeNewPiece('h',8,new Torre(board, Color.WHITE));
        for (int i = 'a'; i <= 'h' ;i++){
            placeNewPiece((char)(i), 7, new Peao(board, Color.WHITE));
        }

        for (int i = 'a'; i <= 'h';i++){
            placeNewPiece((char)(i),2, new Peao(board, Color.BLACK));
        }
        placeNewPiece('a',1,new Torre(board, Color.BLACK));
        placeNewPiece('b',1,new Cavalo(board, Color.BLACK));
        placeNewPiece('c',1,new Bispo(board, Color.BLACK));
        placeNewPiece('d',1,new Rei(board, Color.BLACK));
        placeNewPiece('e',1,new Dama(board, Color.BLACK));
        placeNewPiece('f',1,new Bispo(board, Color.BLACK));
        placeNewPiece('g',1,new Cavalo(board, Color.BLACK));
        placeNewPiece('h',1,new Torre(board, Color.BLACK));          
    }
}
