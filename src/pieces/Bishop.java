package pieces;
import java.util.ArrayList;

import main.*;

public class Bishop extends Piece{


	public Bishop(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}
	
	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> bishopMoves= new ArrayList<Move>();
		Bishop bishop= (Bishop)(position.matrix)[rank][file]; 

		
		int newRank=rank+1; //northeast (from whites/both? perspective)
		int newFile=file+1;
		while(newRank<8 && newRank>=0 && newFile<8 && newFile>=0){
			//move to empty sq or capture opposing piece
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				newRank++;
				newFile++;
			}
			else if (bishop.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				break;
			}
			
			else{
				break;
			}
		}
		
		newRank=rank-1; //southwest
		newFile=file-1;
		while(newRank<8 && newRank>=0 && newFile<8 && newFile>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				newRank--;
				newFile--;
				
			}
			else if (bishop.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		newRank=rank+1; //northwest
		newFile=file-1;
		
		while(newRank<8 && newRank>=0 && newFile<8 && newFile>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				newRank++;
				newFile--;
				
			}
			else if (bishop.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
	
		newRank=rank-1; //southeast
		newFile=file+1;
		
		while(newRank<8 && newRank>=0 && newFile<8 && newFile>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				newRank--;
				newFile++;
				
			}
			else if (bishop.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(bishop, newRank, newFile);
				bishopMoves.add(move);
				break;
			}
			else{
				break;
			}
		}

		return bishopMoves;
	}
	
}
