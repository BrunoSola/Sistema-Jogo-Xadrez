package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exceptions.ChessException;
import chess.piece.*;

import java.util.ArrayList;
import java.util.List;

public class ChessMatch {

    private  int turn;
    private Color currentPlayer;
    private Board board;

    private List<Piece> piecesOnTheBoard = new ArrayList<>();
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        this.board = new Board(8,8);
        turn = 1;
        currentPlayer = Color.WHITE;
        initialSetup();
    }

    public int getTurn() {
        return turn;
    }

    public Color getCurrentPlayer() {
        return currentPlayer;
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

    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        Position position = sourcePosition.toPosition();
        validateSourcePosition(position);
        return board.piece(position).possibleMoves();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targerPosition){
        Position source = sourcePosition.toPosition();
        Position target = targerPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        nextTurn();
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target){
        ChessPiece p = (ChessPiece)board.removePiece(source);
        p.increaseMoveCount();
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);
        if (capturedPiece != null) {
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece) board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece == null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("N\u00E3o tem pe\u00E7a na posi\u00E7\u00E3o inicial.");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A pe\u00E7a escolhida n\u00E3o é sua.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("N\u00E3o é possível mover a pe\u00E7a escolhida.");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if (!board.piece(source).possibleMove(target)){
            throw new ChessException("A pe\u00E7a escolhida n\u00E3o pode mover para a posi\u00E7\u00E3o de destino.");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('a', 8, new Torre(board, Color.BLACK));
        placeNewPiece('b',8,new Cavalo(board, Color.BLACK));
        placeNewPiece('c',8,new Bispo(board, Color.BLACK));
        placeNewPiece('d',8,new Rei(board, Color.BLACK));
        placeNewPiece('e',8,new Dama(board, Color.BLACK));
        placeNewPiece('f',8,new Bispo(board, Color.BLACK));
        placeNewPiece('g',8,new Cavalo(board, Color.BLACK));
        placeNewPiece('h',8,new Torre(board, Color.BLACK));
        for (int i = 'a'; i <= 'h' ;i++){
            placeNewPiece((char)(i), 7, new Peao(board, Color.BLACK));
        }

        for (int i = 'a'; i <= 'h';i++){
            placeNewPiece((char)(i),2, new Peao(board, Color.WHITE));
        }
        placeNewPiece('a',1,new Torre(board, Color.WHITE));
        placeNewPiece('b',1,new Cavalo(board, Color.WHITE));
        placeNewPiece('c',1,new Bispo(board, Color.WHITE));
        placeNewPiece('d',1,new Rei(board, Color.WHITE));
        placeNewPiece('e',1,new Dama(board, Color.WHITE));
        placeNewPiece('f',1,new Bispo(board, Color.WHITE));
        placeNewPiece('g',1,new Cavalo(board, Color.WHITE));
        placeNewPiece('h',1,new Torre(board, Color.WHITE));
    }
}
