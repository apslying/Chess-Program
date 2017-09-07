package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import pieces.*;
public class Position implements Comparator<Move> {
	public Piece[][] matrix;
	public boolean whiteToMove; 
	
	
	
	public Position(Piece[][] matrix, boolean whiteToMove) {
		this.matrix = matrix;
		this.whiteToMove = whiteToMove;
	}
	

	/*public int[][] GetPosition(){
		return matrix;
	}*/
	
	@Override
	public String toString() {
		String boardString="";
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (this.matrix[i][j]==null) {
					boardString+= "OOO" + " ";
				} 
				else {
					boardString+= this.matrix[i][j] + " ";

				}
				
			}
			boardString+= "\n";
		}
		
		return boardString;
	}
	
	//fix below
	public void makeMove(Move move){
		//TODO: check that move is legal
		
		this.matrix[move.beginrank][move.beginfile]=null;
		move.capturedPiece=this.matrix[move.endrank][move.endfile]; //null if nothing captured
		this.matrix[move.endrank][move.endfile]=move.piece;

		this.whiteToMove=!this.whiteToMove;
		
		move.piece.rank=move.endrank;
		move.piece.file=move.endfile;
		
		//HACK: careful of this.makeMove(moveRook); being recursive
		if (move.piece.name== 'K' && move.beginrank== 0 && move.beginfile== 3 && move.endrank== 0 && move.endfile== 1){
			//castling kingside white
			Move moveRook = new Move(this.matrix[0][0], 0, 2);
			this.makeMove(moveRook);
			this.whiteToMove=!this.whiteToMove;
		}
		
		if (move.piece.name== 'K' && move.beginrank== 7 && move.beginfile== 3 && move.endrank== 7 && move.endfile== 1){
			//castling kingside black
			//TODO: undo castling !!!!
			Move moveRook = new Move(this.matrix[7][0], 7, 2);
			this.makeMove(moveRook);
			this.whiteToMove=!this.whiteToMove;
			
		}
		//return this;
		
		//en passant
		if (move.piece.name=='P' && move.beginrank==move.beginrank) {
			
		}
	}
	
	//TODO: piece that was captured but never moved will disappear (ex. 4 move mate, f7 disappears)
	public void undoMove(Move move) {
		this.matrix[move.beginrank][move.beginfile]=move.piece;
		this.matrix[move.endrank][move.endfile]=move.capturedPiece;
		this.whiteToMove=!this.whiteToMove;
		move.piece.rank=move.beginrank;
		move.piece.file=move.beginfile;
		//return this;
		
		//HACK!!!!! 02 to 00
		if (move.piece.name== 'K' && move.beginrank== 0 && move.beginfile== 3 && move.endrank== 0 && move.endfile== 1){
			//castling kingside white
			Move moveRook = new Move(this.matrix[0][2], 0, 0);
			this.makeMove(moveRook);
			this.whiteToMove=!this.whiteToMove;
		}
		
		if (move.piece.name== 'K' && move.beginrank== 7 && move.beginfile== 3 && move.endrank== 7 && move.endfile== 1){
			//castling kingside black
			Move moveRook = new Move(this.matrix[7][2], 7, 0);
			this.makeMove(moveRook);
			this.whiteToMove=!this.whiteToMove;
		}
	}
	
	public int countMaterial(){
		int material=0;
		for (int rank=0; rank<8; rank++){
			for (int file=0; file<8; file++){
				int pointValue=0;
				try {
					Piece piece= matrix[rank][file];
					switch (piece.name){
					case 0:
						break;
					case 'P':
						pointValue=100;
						break;
					case 'N':
						pointValue=300;
						break;
					case 'B':
						pointValue=301;
						break;
					case 'R':
						pointValue=500;
						break;
					case 'Q':
						pointValue=900;
						break;
					case 'K':
						pointValue=1000000;
						break;
					}
					
					if(!piece.isWhite)
						pointValue=-1*pointValue;
				} catch (NullPointerException e) {
				}
				material=material+pointValue;
			}
		}
		
		return material;
		
	}
	
	public int countMobility() {
		ArrayList<Move> opponentMoves= new ArrayList<Move>();
		ArrayList<Move> myMoves= new ArrayList<Move>();
	
		opponentMoves=this.listAllMoves();
		this.whiteToMove=!this.whiteToMove;
		myMoves=this.listAllMoves();
		this.whiteToMove=!this.whiteToMove;
		
		//can be one line
		if (!this.whiteToMove)
			return myMoves.size() - opponentMoves.size();
		else
			return opponentMoves.size() - myMoves.size();
	}
	
	public int evaluateSimple() {
		return countMaterial() + countMobility();
	}
	
	public Piece getPiece(int rank, int file) {
		return this.matrix[rank][file];
	}
	
	public ArrayList<Move> listAllMoves(){//must have a list of all moves, can't split up by square 
		ArrayList<Move> listOfMoves=new ArrayList<Move>();
		for (int rank=0; rank<8; rank++){
			for (int file=0; file<8; file++){
				try {
					Piece piece= this.matrix[rank][file];
					if(piece.isWhite==this.whiteToMove) {
						ArrayList<Move> currentPiecePossibleMoves = null;
						//System.out.println(piece);
						switch (piece.name){
						case 0:
							break;
						case 'P':
							currentPiecePossibleMoves= Pawn.possibleMoves(this, rank, file);
							break;
						case 'N':
							currentPiecePossibleMoves= Knight.possibleMoves(this, rank, file);
							break;
						case 'B':
							currentPiecePossibleMoves= Bishop.possibleMoves(this, rank, file);
							break;
						case 'R':
							currentPiecePossibleMoves= Rook.possibleMoves(this, rank, file);
							break;
						case 'Q':
							currentPiecePossibleMoves= Queen.possibleMoves(this, rank, file);
							break;
						case 'K':
							currentPiecePossibleMoves= King.possibleMoves(this, rank, file);
							break;
						}
						//System.out.println(currentPiecePossibleMoves);
						try {
							listOfMoves.addAll(currentPiecePossibleMoves);
						} catch (NullPointerException e) {
						}
					}
					
				} catch (NullPointerException e) {
					// TODO: handle exception
				}
				
			}

		}
		return listOfMoves;
	}
	
	public ArrayList<Move> addEvalToMoves(){
		ArrayList<Move> listOfMoves= listAllMoves();
		for (Move move : listOfMoves) {
			this.makeMove(move);
			move.simpleEval= this.evaluateSimple();
			this.undoMove(move);
		}
		return listOfMoves;
	}
	
	public ArrayList<Move> sortMoves() {
		ArrayList<Move> listOfMoves= addEvalToMoves();
		listOfMoves.sort(new Comparator<Move>() {

			@Override
			public int compare(Move m1, Move m2) {
				if (m1.simpleEval < m2.simpleEval) {
					return -1;
				}
				else if (m1.simpleEval > m2.simpleEval) {
					return 1;
				}
				else {
					return 0;
				}
			}
		});
		return listOfMoves;
	}
	
	public static Position fenToMatrix(String fen) {
		Piece[][] matrix= new Piece[8][8];
		String[] ranks= fen.split("/");
		Collections.reverse(Arrays.asList(ranks));
		for (int i = 0; i < ranks.length; i++) {
			//System.out.println(ranks[i]);
		}
		String current="";
		for (int i = 0; i < ranks.length ; i++) {
			String[] files= ranks[i].split("");
			Collections.reverse(Arrays.asList(files));
			int offset=0;
			for (int j = 0; j < files.length; j++) {
				//System.out.println(files[j]);
				current=files[j];
				//System.out.println(current + " offset:" + offset + " i: " + i + " j: " + j);
				switch (current) {
				case "P":
					matrix[i][j+offset]=new Pawn('P', i, j+offset, true);
					break;
				case "N":
					matrix[i][j+offset]=new Knight('N', i, j+offset, true);
					break;
				case "B":
					matrix[i][j+offset]=new Bishop('B', i, j+offset, true);
					break;
				case "R":
					matrix[i][j+offset]=new Rook('R', i, j+offset, true);
					break;
				case "Q":
					matrix[i][j+offset]=new Queen('Q', i, j+offset, true);
					break;
				case "K":
					matrix[i][j+offset]=new King('K', i, j+offset, true);
					break;
				case "p":
					matrix[i][j+offset]=new Pawn('P', i, j+offset, false);
					break;
				case "n":
					matrix[i][j+offset]=new Knight('N', i, j+offset, false);
					break;
				case "b":
					matrix[i][j+offset]=new Bishop('B', i, j+offset, false);
					break;
				case "r":
					matrix[i][j+offset]=new Rook('R', i, j+offset, false);
					break;
				case "q":
					matrix[i][j+offset]=new Queen('Q', i, j+offset, false);
					break;
				case "k":
					matrix[i][j+offset]=new King('K', i, j+offset, false);
					break;
					
				default:
					
					int num= Integer.valueOf(current);
					//System.out.println(num);
					for (int k = 0; k < num; k++) {
						matrix[i][j+k+offset]=null;
					}
					offset+=num-1;
					break;
				}
			}
		}
		return new Position(matrix,true);
		
	}

	//already implemented 
	@Override
	public int compare(Move o1, Move o2) {
		// TODO Auto-generated method stub
		return 0;
	}


}
