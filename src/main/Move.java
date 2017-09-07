package main;
import pieces.*;
public class Move {
	public Piece piece; //11
	public Piece capturedPiece; //11
	public int beginrank; //4
	public int beginfile; //4
	public int endrank; //4
	public int endfile; //4
	public int simpleEval; //evaluation of the position after making this move
		
	public Move(Piece piece, int beginrank, int beginfile, int endrank, int endfile) {
		this.piece = piece;
		this.beginrank = beginrank;
		this.beginfile = beginfile;
		this.endrank = endrank;
		this.endfile = endfile;
	}
	
	
	public Move(Piece piece, int endrank, int endfile) {
		this(piece, piece.rank, piece.file, endrank, endfile);
	}
	
	public Move(Position position, int beginrank, int beginfile, int endrank, int endfile) {
		this.piece=position.getPiece(beginrank, beginfile);
		this.beginrank = beginrank;
		this.beginfile = beginfile;
		this.endrank = endrank;
		this.endfile = endfile;
	}
	
	public void printFields(){
		System.out.println(piece);
		System.out.println(endrank);
		System.out.println(endfile);
	}
	
	public static Move stringToMove(Position position, String string) {
		int beginRank= string.charAt(1)-49;
		int beginFile= 104-string.charAt(0);
		int endRank= string.charAt(3)-49;
		int endFile= 104-string.charAt(2);
		

		return new Move( position,  beginRank,  beginFile,  endRank,  endFile);
	}
	
	/*@Override
	public String toString() {
		return "piece " + String.valueOf(this.piece) + "/begin " + String.valueOf(this.beginrank) + "," + String.valueOf(this.beginfile) + "/end " + String.valueOf(this.endrank) + "," + String.valueOf(this.endfile);
	}*/
	
	//decides what is printed in Test "expected..."?
	@Override
	public String toString() {
		return this.piece.name +"" + (char) (9-this.endfile+95) + (this.endrank+1);
	}
	
	@Override
	public boolean equals(Object o) {
	    if (!(o instanceof Move)) {
	        return false;
	    }

	    return this.piece == ((Move) o).piece
	        && this.endrank == ((Move) o).endrank
	        && this.endfile == ((Move) o).endfile;
	        
	}
	
	@Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + piece.hashCode();
        result = 31 * result + endrank;
        result = 31 * result + endfile;
        
        return result;
    }

	
}
