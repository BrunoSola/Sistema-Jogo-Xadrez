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

        while (true){ // true temporario ate criar a logica de check mate.
            try {
                UI.clearScreen();
                UI.printMatch(chessMatch, captured);
                System.out.println();
                System.out.print("Source: ");
                ChessPosition source = UI.readChessPosition(in);
                System.out.println();
                System.out.print("Target: ");
                ChessPosition target = UI.readChessPosition(in);

                ChessPiece capturedPiece = chessMatch.performChessMove(source, target);
                if (capturedPiece != null){
                    captured.add(capturedPiece);
                }
            }catch (ChessException e){
                System.out.println(e.getMessage());
                in.nextLine();
            }
            catch (InputMismatchException e){
                System.out.println(e.getMessage());
                in.nextLine();
            }
        }


    }
}
