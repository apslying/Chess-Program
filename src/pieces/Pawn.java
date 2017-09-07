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
		int change;
		int initialRow;
		int enPassantRow;
		if(position.whiteToMove==true){
			change=1;
			initialRow=1;
			enPassantRow=4;
		}
		else {
			change=-1;
			initialRow=6;
			enPassantRow=3;
		}
		
		//normal move
		if(position.matrix[rank+change][file]==null){
			Move move= new Move(pawn, rank+change, file);
			pawnMoves.add(move);
		}
		
		//capture
		try {
			if(position.matrix[rank+change][file+change]!=null && pawn.isWhite!=position.matrix[rank+change][file+change].isWhite){
				Move move= new Move(pawn, rank+change, file+change);
				pawnMoves.add(move);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
		//capture other direction
		try {
			if(position.matrix[rank+change][file-change]!=null && pawn.isWhite!=position.matrix[rank+change][file-change].isWhite){
				Move move= new Move(pawn, rank+change, file-change);
				pawnMoves.add(move);
			}
		} catch (ArrayIndexOutOfBoundsException e) {
		}
		
		//move two squares
		if(rank==initialRow){
			if(position.matrix[rank+(2*change)][file]==null && position.matrix[rank+(2*change)][file]==null){
				Move move= new Move(pawn, rank+(2*change), file);
				pawnMoves.add(move);
			}
		}
		
		//en passant
		try {
			Piece piece= MainProgram.pastMoves.get(-1).piece;
			if (rank==enPassantRow && piece.name=='P' && piece.rank==enPassantRow && piece.file==file-1) {
				Move move= new Move(pawn, rank, file-1);
				pawnMoves.add(move);
			}
			
			
		} catch (ArrayIndexOutOfBoundsException e) {
			// TODO: handle exception
		}
		return pawnMoves;

	}
}
