package main;
import java.util.ArrayList;
import java.util.Scanner;

import pieces.*;
import tree.*;

public class MainProgram {
	
	public static ArrayList<Move> pastMoves= new ArrayList<Move>();
	
	public static void main(String[] args) {
		Position board;
		Piece[][] startingMatrix;
		
		Pawn[] whitePawns= new Pawn[8];
		Knight[] whiteKnights= new Knight[2];
		Bishop[] whiteBishops= new Bishop[2];
		Rook[] whiteRooks= new Rook[2];
		Pawn[] blackPawns= new Pawn[8];
		Knight[] blackKnights= new Knight[2];
		Bishop[] blackBishops= new Bishop[2];
		Rook[] blackRooks= new Rook[2];
		Queen whiteQueen= new Queen('Q', 0, 4, true);
		Queen blackQueen= new Queen('Q', 7, 4, false);
		King whiteKing= new King('K', 0, 3, true);
		King blackKing= new King('K', 7, 3, false);
		
		for (int file = 0; file < 8; file++) {
			Pawn newPawn= new Pawn('P', 1, file, true);
			whitePawns[file]=newPawn;
		}
		for (int file = 0; file < 8; file++) {
			Pawn newPawn= new Pawn('P', 6, file, false);
			blackPawns[file]=newPawn;
		}
		
		whiteKnights[0]= new Knight('N', 0, 1, true);
		whiteKnights[1]= new Knight('N', 0, 6, true);
		blackKnights[0]= new Knight('N', 7, 1, false);
		blackKnights[1]= new Knight('N', 7, 6, false);
		
		whiteBishops[0]= new Bishop('B', 0, 2, true);
		whiteBishops[1]= new Bishop('B', 0, 5, true);
		blackBishops[0]= new Bishop('B', 7, 2, false);
		blackBishops[1]= new Bishop('B', 7, 5, false);
		
		whiteRooks[0]= new Rook('R', 0, 0, true);
		whiteRooks[1]= new Rook('R', 0, 7, true);
		blackRooks[0]= new Rook('R', 7, 0, false);
		blackRooks[1]= new Rook('R', 7, 7, false);
		//end of creating pieces, now create board
		
		startingMatrix= new Piece[][]{
			{whiteRooks[0], whiteKnights[0], whiteBishops[0], whiteKing, whiteQueen, whiteBishops[1], whiteKnights[1], whiteRooks[1]},
			{whitePawns[0], whitePawns[1], whitePawns[2], whitePawns[3], whitePawns[4], whitePawns[5], whitePawns[6], whitePawns[7]},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{null, null, null, null, null, null, null, null},
			{blackPawns[0], blackPawns[1], blackPawns[2], blackPawns[3], blackPawns[4], blackPawns[5], blackPawns[6], blackPawns[7]},
			{blackRooks[0], blackKnights[0], blackBishops[0], blackKing, blackQueen, blackBishops[1], blackKnights[1], blackRooks[1]}};
					
		board = new Position(startingMatrix, true); 
		
		Scanner scanner= new Scanner(System.in);
		String userMove;
		
		//TODO: implement castling, then promotion, then en passant
		do {
			outputMove(board);
			System.out.println("Enter your move: ");
			userMove= scanner.nextLine();
			Move move= Move.stringToMove(board, userMove);
			pastMoves.add(move); //add to pastMoves

			board.makeMove(move);
			System.out.println(pastMoves);
		} while (userMove!="exit");
	}
	
	public static Move outputMove(Position board) {
		MoveNode rootNode= new MoveNode(null);
		System.out.println();
		MoveTree moveTree= new MoveTree(rootNode, board);
		moveTree.createChildren(rootNode); //level 1
		moveTree.createChildlessNodes(board); //level 2
		moveTree.createChildlessNodes(board); //level 3
		moveTree.createChildlessNodes(board); //level 4
//		moveTree.createChildlessNodes(board); //level 5
//		moveTree.createChildlessNodes(board); //level 6


		
		moveTree.evaluate(rootNode);
		board.makeMove(rootNode.bestOppMove);
		System.out.println("Computer's move: " + rootNode.bestOppMove);
		pastMoves.add(rootNode.bestOppMove); //add to pastMoves
		System.out.println(board);
		//moveTree.printChildrenInfo(rootNode);

		return rootNode.bestOppMove;
	}
}
