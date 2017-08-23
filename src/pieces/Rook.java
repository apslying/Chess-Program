package pieces;
import java.util.ArrayList;
import main.*;

public class Rook extends Piece{
	public Rook(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}
	
	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> rookMoves= new ArrayList<Move>(); 
		Rook rook= (Rook)(position.matrix)[rank][file]; 
		int newRank;
		int newFile;
		
		newRank=rank+1; //north (from whites/both? perspective)
		newFile=file;
		while(newRank<8){
			if (position.matrix[newRank][newFile]==null){
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
				newRank++;
			}
			else if (rook.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
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
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
				newRank--;
			}
			else if (rook.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
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
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
				newFile++;
				break;
			}
			
			else if (rook.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
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
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
				newFile--;
			}
			
			else if (rook.isWhite != position.matrix[newRank][newFile].isWhite) {
				Move move= new Move(rook, newRank, newFile);
				rookMoves.add(move);
				break;
			}
			else{
				break;
			}
		}
		
		return rookMoves;
	}

}
