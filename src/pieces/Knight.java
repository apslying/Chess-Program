package pieces;
import java.lang.Math;
import java.util.ArrayList;
import main.*;
public class Knight extends Piece{

	public Knight(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}
	
	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> knightMoves= new ArrayList<Move>();
		Knight knight= (Knight)(position.matrix)[rank][file]; 

		int[] numbers= {-2,-1,1,2};
		for(int newRank:numbers){
			for(int newFile: numbers){
				if (Math.abs(newRank)!=Math.abs(newFile)){
					//checks each of the 8 squares
					try {
						if(position.matrix[rank+newRank][file+newFile]==null || knight.isWhite != position.matrix[rank+newRank][file+newFile].isWhite){
							Move move= new Move(knight, rank+newRank, file+newFile);
							knightMoves.add(move);
						}						
					} 
					catch (ArrayIndexOutOfBoundsException e) {
					}
				}
				
			}
		}
		return knightMoves;
	}

}
