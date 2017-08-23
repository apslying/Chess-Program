package pieces;
public class Piece {
	public char name;
	public int rank;
	public int file;
	public boolean isWhite;
	
	public Piece(char type, int rank, int file, boolean isWhite) {
		this.name = type;
		this.rank = rank;
		this.file = file;
		this.isWhite = isWhite;
	}
	
	public char getType() {
		return name;
	}

	public int getRank() {
		return rank;
	}

	public int getFile() {
		return file;
	}

	public boolean isWhite() {
		return isWhite;
	}

	public void setType(char type) {
		this.name = type;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void setFile(int file) {
		this.file = file;
	}

	public void setWhite(boolean isWhite) {
		this.isWhite = isWhite;
	}
	
	@Override
	public String toString() {
		return name + "" + rank + file;
	}

}
