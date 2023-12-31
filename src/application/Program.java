package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;
import chess.exceptions.ChessException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ChessMatch chessMatch = new ChessMatch();
        List<ChessPiece> captured = new ArrayList<>();

        while (!chessMatch.getCheckMate()){
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Origem: ");
                ChessPosition source = UI.readChessPosition(in);
                boolean[][] possibleMoves = chessMatch.possibleMoves(source);
                UI.clearScreen();
                UI.printBoard(chessMatch.getPieces(), possibleMoves);
                System.out.println();
                System.out.print("Destino: ");
                ChessPosition target = UI.readChessPosition(in);
                System.out.println();
                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null){
                    captured.add(capturedPiece);
                }

                if (chessMatch.getPromoted() != null){
                    String type;
                    do {
                        System.out.print("Deseja promover para qual tipo de peça (B/C/D/T): ");
                        type = in.nextLine().toUpperCase();
                    }
                    while(!type.equals("B") && !type.equals("C") && !type.equals("D") && !type.equals("T"));{
                        chessMatch.replacePromotedPiece(type);
                    }
                }
            }catch (ChessException | InputMismatchException e){
                System.out.println(e.getMessage());
                in.nextLine();
            }
        }
        UI.clearScreen();
        UI.printMatch(chessMatch, captured);
    }
}
