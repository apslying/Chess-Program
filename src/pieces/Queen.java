package pieces;

//import java.util.Iterator;
import java.util.ArrayList;

import main.*;

public class Queen extends Piece {

	public Queen(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}
	
	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> queenMoves= new ArrayList<Move>();
		Queen queen= (Queen)(position.matrix)[rank][file]; 
		int newRank;
		int newFile;
		
		//bishop
		newRank=rank+1; //northeast (from whites/both? perspective)
		newFile=file+1;
		while(newRank<8 && newRank>=0 && newFile<8 && newFile>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank++;
				newFile++;
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
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
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank--;
				newFile--;
				
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
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
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank++;
				newFile--;
				
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
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
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank--;
				newFile++;
				
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		//rook
		newRank=rank+1; //north (from whites/both? perspective)
		newFile=file;
		while(newRank<8){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank++;
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		newRank=rank-1;//south
		newFile=file;
		while(newRank>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newRank--;
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		newRank=rank;//east
		newFile=file+1;
		while(newFile<8){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newFile++;
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		newRank=rank;//west
		newFile=file-1;
		while(newFile>=0){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				newFile--;
			}
			else if (queen.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(queen, newRank, newFile);
				queenMoves.add(move);
				break;
			}
			else{
				break;
			}
		}

		return queenMoves;
	}
}
	/*public static LinkedList<Move> possibleMoves(Position position, int rank, int file){
		LinkedList<Move> queenMoves= Bishop.possibleMoves(position, rank, file); 
		queenMoves.addAll(Rook.possibleMoves(position, rank, file));
		
		Iterator<Move> queenIterator=queenMoves.iterator();
		while (queenIterator.hasNext()) {
			Move queenMove = queenIterator.next();
			queenMove.piece.pointValue=900;
		}
		return queenMoves;
	}*/

