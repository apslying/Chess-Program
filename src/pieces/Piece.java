package pieces;
public class Piece {
	public char name; //2
	public int rank; //4
	public int file; //4
	public boolean isWhite; // 1 bit
	
	public Piece(char name, int rank, int file, boolean isWhite) {
		this.name = name;
		this.rank = rank;
		this.file = file;
		this.isWhite = isWhite;
	}
	
	public char getName() {
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
