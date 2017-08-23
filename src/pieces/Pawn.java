package pieces;
import java.util.ArrayList;
import main.*;

public class Pawn extends Piece{
	
	public Pawn(char type, int rank, int file, boolean isWhite) {
		super(type, rank, file, isWhite);
	}

	public static ArrayList<Move> possibleMoves(Position position, int rank, int file){
		ArrayList<Move> pawnMoves= new ArrayList<Move>();
		Pawn pawn= (Pawn)(position.matrix)[rank][file]; 
		if(position.whiteToMove==true){
			if(position.matrix[rank+1][file]==null){
				Move move= new Move(pawn, rank+1, file);
				pawnMoves.add(move);
			}
			
			try {
				//capture
				if(position.matrix[rank+1][file+1]!=null && pawn.isWhite!=position.matrix[rank+1][file+1].isWhite){
					Move move= new Move(pawn, rank+1, file+1);
					pawnMoves.add(move);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
			
			try {
				//capture
				if(position.matrix[rank+1][file-1]!=null && pawn.isWhite!=position.matrix[rank+1][file-1].isWhite){
					Move move= new Move(pawn, rank+1, file-1);
					pawnMoves.add(move);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
			
			if(rank==1){
				if(position.matrix[rank+2][file]==null && position.matrix[rank+1][file]==null){
					Move aMove= new Move(pawn, rank+2, file);
					pawnMoves.add(aMove);
				}
			}

		}
		
		else if(position.whiteToMove==false){
			if(position.matrix[rank-1][file]==null){
				Move move= new Move(pawn, rank-1, file);
				pawnMoves.add(move);
			}
			
			try {
				//capture
				if(position.matrix[rank-1][file+1]!=null && pawn.isWhite!=position.matrix[rank-1][file+1].isWhite){
					Move move= new Move(pawn, rank-1, file+1);
					pawnMoves.add(move);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
			
			try {
				//capture
				if(position.matrix[rank-1][file-1]!=null && pawn.isWhite!=position.matrix[rank-1][file-1].isWhite){
					Move move= new Move(pawn, rank-1, file-1);
					pawnMoves.add(move);
				}
			} catch (ArrayIndexOutOfBoundsException e) {
				// TODO: handle exception
			}
			
			if(rank==6){
				if(position.matrix[rank-2][file]==null && position.matrix[rank-1][file]==null){
					Move aMove= new Move(pawn, rank-2, file);
					pawnMoves.add(aMove);
				}
			}

		}
		
		
		return pawnMoves;
	}
}
