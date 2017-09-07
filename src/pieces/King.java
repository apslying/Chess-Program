package pieces;
import main.*;
import java.util.ArrayList;;
public class King extends Piece{

	public King(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}
	
	
	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> kingMoves= new ArrayList<Move>();
		King king= (King)(position.matrix)[rank][file]; 

		int[] numbers= {-1,0,1};
		for(int newFile:numbers){
			for(int newRank: numbers){
				if (newFile==0 && newRank==0){
					//do nothing. king can't move to its own square
				}
				else{
					try {
						if(position.matrix[rank+newRank][file+newFile]==null || king.isWhite != position.matrix[rank+newRank][file+newFile].isWhite){
							Move move= new Move(king, rank+newRank, file+newFile);
							kingMoves.add(move);
						}
					} catch (ArrayIndexOutOfBoundsException e) {
					}
				}
			}
		}
		
		if (canCastleKingside(position) == true) {
			Move move= new Move(king, 0, 1);
			kingMoves.add(move);
		}
		
		return kingMoves;
		
//		if (king.rank == 0 && king.file == 3 && position.matrix[0][0].name == 'R' && position.matrix[0][1] == null && position.matrix[0][2] == null) {
//			Move move= new Move(king, 0, 1);
//			kingMoves.add(move);
//		}
//		
//		else if (king.rank == 7 && king.file == 3 && position.matrix[7][0].name == 'R' && position.matrix[7][1] == null && position.matrix[7][2] == null) {
//			System.out.println("in!");
//			Move move= new Move(king, 7, 1);
//			kingMoves.add(move);
//		}
		
	}
	
	public static boolean canCastleKingside(Position position) {
		if (position.whiteToMove == true) {
			if (position.matrix[0][3].name == 'K' && position.matrix[0][0].name == 'R' && position.matrix[0][1] == null && position.matrix[0][2] == null) {
				return true;
			}
			else {
				return false;
			}
		}
		
		else {
			if (position.matrix[7][3].name == 'K' && position.matrix[7][0].name == 'R' && position.matrix[7][1] == null && position.matrix[7][2] == null) {
				return true;
			}
			else {
				return false;
			}
		}
	}

}
