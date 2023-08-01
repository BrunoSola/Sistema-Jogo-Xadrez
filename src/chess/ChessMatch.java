package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.enums.Color;
import chess.exceptions.ChessException;
import chess.piece.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {

    private  int turn;
    private Color currentPlayer;
    private Board board;
    private boolean check;
    private boolean checkMate;
    private ChessPiece enPassantVulnerable;

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

    public boolean getCheck(){
        return check;
    }

    public boolean getCheckMate(){return checkMate;}

    public ChessPiece getEnPassantVulnerable(){return enPassantVulnerable;}

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

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        if (testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("Você não pode se colocar em CHECK!");
        }

        ChessPiece movedPiece = (ChessPiece)board.piece(target);

        check = (testCheck(opponent(currentPlayer))) ? true : false;

        if (testCheckMate(opponent(currentPlayer))){
            checkMate = true;
        }else {
            nextTurn();
        }

        // Movimento especial - en passant
        if (movedPiece instanceof Peao && (target.getRow() == source.getRow() - 2 || target.getRow() == source.getRow() + 2)){
            enPassantVulnerable = movedPiece;
        }
        else {
            enPassantVulnerable = null;
        }
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

        //Movimento Especial Roque(Castling) - Lado do rei
        if (p instanceof Rei && target.getColumn() == source.getColumn() + 2){
            Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
            Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceTorre);
            board.placePiece(rook, targetTorre);
            rook.increaseMoveCount();
        }

        //Movimento Especial Roque(Castling) - Lado da Dama(Rainha)
        if (p instanceof Rei && target.getColumn() == source.getColumn() - 2){
            Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
            Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
            ChessPiece rook = (ChessPiece)board.removePiece(sourceTorre);
            board.placePiece(rook, targetTorre);
            rook.increaseMoveCount();
        }

        //Movimento Especial - en passant
        if (p instanceof Peao){
            if (source.getColumn() != target.getColumn() && capturedPiece == null){
                Position positionPeao;
                if (p.getColor() == Color.WHITE){
                    positionPeao = new Position(target.getRow() + 1, target.getColumn());
                }
                else {
                    positionPeao = new Position(target.getRow() - 1, target.getColumn());
                }
                capturedPiece = board.removePiece(positionPeao);
                capturedPieces.add(capturedPiece);
                piecesOnTheBoard.remove(capturedPiece);
            }
        }
        return capturedPiece;
    }

    private void undoMove(Position source, Position target, Piece capturedPiece){
        ChessPiece p = (ChessPiece)board.removePiece(target);
        p.decreaseMoveCount();
        board.placePiece(p, source);

        if (capturedPiece != null){
            board.placePiece(capturedPiece, target);
            capturedPieces.remove(capturedPiece);
            piecesOnTheBoard.add(capturedPiece);
        }

        //Desfazer Movimento Especial Roque(Castling) - Lado do rei
        if (p instanceof Rei && target.getColumn() == source.getColumn() + 2){
            Position sourceTorre = new Position(source.getRow(), source.getColumn() + 3);
            Position targetTorre = new Position(source.getRow(), source.getColumn() + 1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetTorre);
            board.placePiece(rook, sourceTorre);
            rook.decreaseMoveCount();
        }

        //Desfazer Movimento Especial Roque(Castling) - Lado da Dama(Rainha)
        if (p instanceof Rei && target.getColumn() == source.getColumn() - 2){
            Position sourceTorre = new Position(source.getRow(), source.getColumn() - 4);
            Position targetTorre = new Position(source.getRow(), source.getColumn() - 1);
            ChessPiece rook = (ChessPiece)board.removePiece(targetTorre);
            board.placePiece(rook, sourceTorre);
            rook.decreaseMoveCount();
        }

        //Desfazer Movimento Especial - en passant
        if (p instanceof Peao){
            if (source.getColumn() != target.getColumn() && capturedPiece == enPassantVulnerable){
                ChessPiece peao = (ChessPiece)board.removePiece(target);
                Position positionPeao;
                if (p.getColor() == Color.WHITE){
                    positionPeao = new Position(3, target.getColumn());
                }
                else {
                    positionPeao = new Position(4, target.getColumn());
                }
                board.placePiece(peao, positionPeao);
            }
        }
    }

    private void validateSourcePosition(Position position){
        if (!board.thereIsAPiece(position)){
            throw new ChessException("Não tem peça na posição inicial.");
        }
        if (currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("A peça escolhida não é sua.");
        }
        if (!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("Não é possível mover a peça escolhida.");
        }
    }

    private void validateTargetPosition(Position source, Position target){
        if (!board.piece(source).possibleMove(target)){
            throw new ChessException("A peça escolhida não pode mover para a posição de destino.");
        }
    }

    private void nextTurn(){
        turn++;
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }

    private ChessPiece king(Color color){
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            if (p instanceof Rei){
                return (ChessPiece) p;
            }
        }
        throw new IllegalStateException("Não existe o rei da " + color + " na partida.");
    }

    private boolean testCheck(Color color){
        Position kingPosition = king(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        for (Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if (mat[kingPosition.getRow()][kingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }

    private boolean testCheckMate(Color color){
        if (!testCheck(color)){return false;}
        List<Piece> list = piecesOnTheBoard.stream().filter(x -> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for (Piece p : list){
            boolean[][] mat = p.possibleMoves();
            for (int i = 0; i < board.getRows(); i++){
                for (int j = 0; j < board.getColumns(); j++){
                    if (mat[i][j]){
                        Position source = ((ChessPiece)p).getChessPosition().toPosition();
                        Position target = new Position(i, j);
                        Piece capturedPiece = makeMove(source, target);
                        boolean testCheck = testCheck(color);
                        undoMove(source, target, capturedPiece);
                        if (!testCheck){
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }

    private void initialSetup(){
        placeNewPiece('a', 8, new Torre(board, Color.BLACK));
        placeNewPiece('b',8,new Cavalo(board, Color.BLACK));
        placeNewPiece('c',8,new Bispo(board, Color.BLACK));
        placeNewPiece('d',8,new Dama(board, Color.BLACK));
        placeNewPiece('e',8,new Rei(board, Color.BLACK, this));
        placeNewPiece('f',8,new Bispo(board, Color.BLACK));
        placeNewPiece('g',8,new Cavalo(board, Color.BLACK));
        placeNewPiece('h',8,new Torre(board, Color.BLACK));
        for (int i = 'a'; i <= 'h' ;i++){
            placeNewPiece((char)(i), 7, new Peao(board, Color.BLACK, this));
        }

        for (int i = 'a'; i <= 'h';i++){
            placeNewPiece((char)(i),2, new Peao(board, Color.WHITE,this));
        }
        placeNewPiece('a',1,new Torre(board, Color.WHITE));
        placeNewPiece('b',1,new Cavalo(board, Color.WHITE));
        placeNewPiece('c',1,new Bispo(board, Color.WHITE));
        placeNewPiece('d',1,new Dama(board, Color.WHITE));
        placeNewPiece('e',1,new Rei(board, Color.WHITE, this));
        placeNewPiece('f',1,new Bispo(board, Color.WHITE));
        placeNewPiece('g',1,new Cavalo(board, Color.WHITE));
        placeNewPiece('h',1,new Torre(board, Color.WHITE));

    }
}
